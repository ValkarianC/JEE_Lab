package org.example.jee_lab.controllers;

import org.example.jee_lab.entities.Member;
import org.example.jee_lab.exceptions.ResourceNotMatchingException;
import org.example.jee_lab.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //Add
    @PostMapping("/addmember")
    @ResponseBody
    public ResponseEntity<Member> addNewMember(@RequestBody Member member){
        return new ResponseEntity<>(memberService.addMember(member), HttpStatus.CREATED);
    }
    //GetAll
    @GetMapping("/members")
    @ResponseBody
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
    //GetByID
    @GetMapping("/member/{id}")
    @ResponseBody
    public Member getMemberById(@PathVariable("id")Long ID){
        return memberService.getMemberById(ID);
    }
    //UpdateByID
    @PutMapping("/updatemember/{id}")
    @ResponseBody
    public ResponseEntity<Member> updateMemberById(@PathVariable("id")Long ID, @RequestBody Member member){
        Member memberCheck = memberService.getMemberById(ID);
        if (memberCheck.getID() == member.getID()){
            return new ResponseEntity<>(memberService.updateMember(member), HttpStatus.ACCEPTED);
        } else {
            throw new ResourceNotMatchingException("Member", "ID", memberCheck.getID(), member.getID());
        }
    }
    //DeleteByID
    @DeleteMapping("/deletemember/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMemberById(@PathVariable("id")Long ID){
        memberService.deleteMemberById(ID);
        return new ResponseEntity<>("Member deleted: ID: "+ID, HttpStatus.GONE);
    }

    //Show page w/ Thymeleaf
    @GetMapping("/deletemember")
    public String showMemberDeletePage(Model model){
        model.addAttribute("members", memberService.getAllMembers());
        return "deletemembers";
    }

    //Used by Thymeleaf to delete member
    @PostMapping("/deletememberbyid")
    public String deleteChosenMember(@RequestParam("id")Long ID){
        memberService.deleteMemberById(ID);
        return "redirect:/admin/deletemember";
    }
}
