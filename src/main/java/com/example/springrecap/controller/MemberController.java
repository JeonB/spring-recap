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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        if (memberService.isPresentMemberByUsername(member.getUsername()) && new BCryptPasswordEncoder().matches(member.getPassword(), memberService.getMemberByUsername(member.getUsername()).getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", member.getUsername());
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 접근입니다");
        }
    }
}
