package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.GroupMemberDTO;
import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import facades.GroupMemberFacade;
import javax.ws.rs.PathParam;

@Path("groupmembers")
public class GroupMemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final GroupMemberFacade FACADE = GroupMemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembersNoDTO() {
        try {
            List<GroupMember> members = FACADE.getAllMembers();
            return new Gson().toJson(members);
        } catch (Exception e) {
            return "Error: Not able to retrieve list of members";
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberByID(@PathParam("id") String studentId) {
        try {
            GroupMemberDTO memberDTO = new GroupMemberDTO(FACADE.getMemberByStudentId(studentId));
            return new Gson().toJson(memberDTO);
        } catch (Exception e) {
            return "Error: Not able to find or retrieve member";
        }
    }
    
     @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateGroupMemberTable() {
        try {
            FACADE.populateDB();
            List<GroupMember> members = FACADE.getAllMembers();
            String jsonString = GSON.toJson("Added members to DB:\n" + members);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
}
