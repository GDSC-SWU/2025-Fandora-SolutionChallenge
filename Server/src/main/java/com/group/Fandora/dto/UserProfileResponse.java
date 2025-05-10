package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class UserProfileResponse {
    private final String name;

    public UserProfileResponse(String name) {
        this.name = name;
    }
}