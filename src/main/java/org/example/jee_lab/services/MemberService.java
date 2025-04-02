package org.example.jee_lab.services;

import org.example.jee_lab.entities.Member;
import org.example.jee_lab.exceptions.ResourceNotFoundException;
import org.example.jee_lab.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements MemberServiceInterface {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long ID) {
        Optional<Member> potentialMember = memberRepository.findById(ID);
        if (potentialMember.isPresent()){
            return potentialMember.get();
        }
        throw new ResourceNotFoundException("Member", "ID", ID);
    }

    @Override
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMemberById(Long ID) {

    }
}
