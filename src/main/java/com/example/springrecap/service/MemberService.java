package com.example.springrecap.service;

import com.example.springrecap.entity.Member;
import com.example.springrecap.entity.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void saveMember(Member member){
        memberRepository.save(member);
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public Member getMember(Long id){
       return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다."));
    }
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다."));
    }
    public List<Member> getALLMembers(){
        return memberRepository.findAll();
    }
    public boolean isPresentMemberById(Long id){
        return memberRepository.existsById(id);
    }
    public boolean isPresentMemberByUsername(String username){
        return memberRepository.existsByUsername(username);
    }

    public void updateMember(Long id, Member updatedMember) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
//        member.setTitle(updatedMember.getTitle());
//        member.setPrice(updatedMember.getPrice());
        memberRepository.save(member);
    }
}
