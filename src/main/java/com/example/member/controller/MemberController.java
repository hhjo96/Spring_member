package com.example.member.controller;

import com.example.member.dto.*;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/memberss")
    public ResponseEntity<MemberCreateResponse> create(@RequestBody MemberCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }

    //전체조회
    @GetMapping("/members")
    public ResponseEntity<List<MemberGetResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberGetResponse> getOne(@PathVariable Long memoId) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findOne(memoId));
    }

    @PutMapping("/memos/{memberId}")
    public ResponseEntity<MemberUpdateResponse> update(@PathVariable Long memberId, @RequestBody MemberUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.update(memberId, request));
    }

    @DeleteMapping("/memos/{memoId}")
    public void delete(@PathVariable Long memoId){
        memberService.delete(memoId);
    }
}
