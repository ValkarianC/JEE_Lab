package org.example.jee_lab.services;

import org.example.jee_lab.entities.Member;

import java.util.List;

public interface MemberServiceInterface {

    Member addMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(Long ID);
    Member updateMember(Member member);
    void deleteMemberById(Long ID);
}
