package dto;

import entities.Member;

public class MemberDTO {

    private String name;
    private String studentId;

    public MemberDTO(Member member) {
        this.name = member.getName();
        this.studentId = member.getStudentId();
    }
}
