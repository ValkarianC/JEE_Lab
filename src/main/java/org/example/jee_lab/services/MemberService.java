package org.example.jee_lab.services;

import org.example.jee_lab.entities.Member;
import org.example.jee_lab.exceptions.IncorrectFormatException;
import org.example.jee_lab.exceptions.ResourceNotFoundException;
import org.example.jee_lab.repositories.AddressRepository;
import org.example.jee_lab.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MemberService implements MemberServiceInterface {

    private MemberRepository memberRepository;
    private AddressRepository addressRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, AddressRepository addressRepository){
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Member addMember(Member member) {
        checkAttributeFormatting(member);
        member.setAddress(addressRepository.findById(member.getAddress().getID()).get());
        return memberRepository.save(member);
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
        checkAttributeFormatting(member);
        member.setAddress(addressRepository.findById(member.getAddress().getID()).get());
        return memberRepository.save(member);
    }

    @Override
    public void deleteMemberById(Long ID) {
        Optional<Member> potentialMember = memberRepository.findById(ID);
        if (potentialMember.isPresent()){
            memberRepository.deleteById(ID);
        } else {
            throw new ResourceNotFoundException("Member", "ID", ID);
        }
    }

    private void checkAttributeFormatting(Member member){
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Pattern phonePattern = Pattern.compile("^[+0-9][0-9]{0,2}[0-9\\h-]{10,15}$");

        if (!emailPattern.matcher(member.getEmail()).matches()){
            throw new IncorrectFormatException("Member", "email", member.getEmail(), "basic@example.com");
        }
        if (!datePattern.matcher(member.getDateOfBirth().toString()).matches()){
            throw new IncorrectFormatException("Member", "date of birth", member.getDateOfBirth(), "YYYY-MM-DD");
        }
        if (!phonePattern.matcher(member.getPhone()).matches() || member.getPhone().endsWith(" ") || member.getPhone().endsWith("-")){
            throw new IncorrectFormatException("Member", "phone", member.getPhone(), "0800123456 || +4479 0011-2233");
        }
    }
}
