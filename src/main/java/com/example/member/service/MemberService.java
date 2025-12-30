package com.example.member.service;

import com.example.member.dto.*;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //c
    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request){
        Member member = new Member(request.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberCreateResponse(savedMember.getId(), savedMember.getName(), savedMember.getCreatedAt(), savedMember.getModifiedAt());
    }

    //r
    @Transactional(readOnly = true)
    public List<MemberGetResponse> findAll(){
        List<Member> members = memberRepository.findAll();

        return members.stream().map(member -> new MemberGetResponse(member.getId(), member.getName(), member.getCreatedAt(), member.getModifiedAt())).toList();
    }
    //r1
    @Transactional(readOnly = true)
    public MemberGetResponse findOne(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new IllegalStateException("Member Not Found"));

        return new MemberGetResponse(member.getId(), member.getName(), member.getCreatedAt(), member.getModifiedAt());
    }

    //u
    @Transactional
    public MemberUpdateResponse update(Long memberId, MemberUpdateRequest request){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new IllegalStateException("Member Not Found"));
        member.update(request.getName());
        return new MemberUpdateResponse(member.getId(), member.getName(), member.getCreatedAt(), member.getModifiedAt());
    }

    //d
    @Transactional
    public void delete(Long memberId){
        boolean existence = memberRepository.existsById(memberId);
        if(!existence) {
            throw new IllegalStateException("Member Not Found");
        }
        memberRepository.deleteById(memberId);
    }
}
