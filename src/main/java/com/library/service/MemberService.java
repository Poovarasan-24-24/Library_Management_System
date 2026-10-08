package com.library.service;

import com.library.model.Member;
import java.util.List;

public interface MemberService {
    Member registerMember(Member member);
    Member getMemberById(String id);
    List<Member> getAllMembers();
}