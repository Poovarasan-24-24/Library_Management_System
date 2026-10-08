package com.library.service.impl;

import com.library.exception.MemberNotFoundException;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import com.library.service.MemberService;
import com.library.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member registerMember(Member member) {
        if (!ValidationUtils.isValidEmail(member.getEmail())) {
            throw new IllegalArgumentException("Invalid email format: " + member.getEmail());
        }

        memberRepository.save(member);
        return member;
    }

    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}