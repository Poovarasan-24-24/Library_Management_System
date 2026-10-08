package com.library.model;

import com.library.model.enums.MemberRole;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private MemberRole role;
    private boolean active;

    public Member() {}

    public Member(String memberId, String name, String email, MemberRole role) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = true;
    }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public MemberRole getRole() { return role; }
    public void setRole(MemberRole role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return String.format("Member [ID=%s, Name='%s', Email='%s', Role=%s, Active=%b]",
                memberId, name, email, role, active);
    }
}