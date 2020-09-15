package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MemberDTO;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import facades.MemberFacade;
import javax.ws.rs.PathParam;

@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MemberFacade FACADE = MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMoviesNoDTO() {
        try {
            List<Member> members = FACADE.getAllMembers();
            return new Gson().toJson(members);
        } catch (Exception e) {
            return "Error: Not able to retrieve list of members";
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") String studentId) {
        try {
            MemberDTO memberDTO = new MemberDTO(FACADE.getMemberByStudentId(studentId));
            return new Gson().toJson(memberDTO);
        } catch (Exception e) {
            return "Error: Not able to find or retrieve member";
        }
    }
}
