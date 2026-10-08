package com.library.repository;

import com.library.model.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(String memberId);
    List<Member> findAll();
}