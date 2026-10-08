package com.library.dto;

public class IssueBookRequest {
    private String isbn;
    private String memberId;

    public IssueBookRequest() {}

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
}