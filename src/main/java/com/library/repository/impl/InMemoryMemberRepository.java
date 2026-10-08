package com.library.repository.impl;

import com.library.model.Member;
import com.library.repository.MemberRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMemberRepository implements MemberRepository {
    private final Map<String, Member> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        storage.put(member.getMemberId(), member);
    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(storage.get(memberId));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }
}