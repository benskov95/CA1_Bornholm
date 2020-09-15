package dto;

import entities.GroupMember;

public class GroupMemberDTO {

    private String name;
    private String studentId;

    public GroupMemberDTO(GroupMember member) {
        this.name = member.getName();
        this.studentId = member.getStudentId();
    }
}
