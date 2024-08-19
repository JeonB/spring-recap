package com.example.springrecap.controller;

import com.example.springrecap.entity.Member;
import com.example.springrecap.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getALLMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/info/{id}")
    public String infoPage(@PathVariable Long id, Model model) throws Exception {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "info";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute Member member){
        var result = new BCryptPasswordEncoder().encode(member.getPassword());
        member.setPassword(result);
        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping("/add-member")
    String write(Model model){
        model.addAttribute("member", new Member());
        return "signup";
    }

    @GetMapping("/sign-in")
    String signIn(){
        return "signin";
    }

    @GetMapping("/sign-out")
    String signOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping("/sign-in-member")
    @ResponseBody
    public ResponseEntity<String> signInMember(@RequestBody Member member, HttpServletRequest request) {
        Member foundMember = memberService.getMemberByUsername(member.getUsername());
        if (foundMember != null && new BCryptPasswordEncoder().matches(member.getPassword(), foundMember.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", foundMember.getUsername());
            session.setAttribute("id", foundMember.getId());
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 접근입니다");
        }
    }

    @PutMapping("/update-member/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Member updatedMember) {
        if(memberService.isPresentMemberById(id)){
            memberService.updateMember(id, updatedMember);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/edit-member/{id}")
    public String editPage(@PathVariable Long id, Model model) throws Exception {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "signup";
    }

    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(memberService.isPresentMemberById(id)){
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
