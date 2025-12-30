package com.example.member.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberGetResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemberGetResponse(Long id, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
