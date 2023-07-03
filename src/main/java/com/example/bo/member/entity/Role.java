package com.example.bo.member.entity;

import lombok.Getter;

@Getter
public enum Role {
    MEMBER("MEMBER"),
    ADMIN("ADMIN"),
    DEPTADMIN("DEPTADMIN"),
    DEPTSUBADMIN("DEPTSUBADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
