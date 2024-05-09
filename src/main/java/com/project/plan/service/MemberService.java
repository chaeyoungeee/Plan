package com.project.plan.service;

import com.project.plan.domain.Member;
import com.project.plan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateUsername(member.getUsername());
        validateDuplicateNickname(member.getNickname());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateUsername(String name) {
        Member findMember = memberRepository.findByUsername(name);
        if (findMember == null) {
            throw new IllegalStateException("이미 존재하는 유저네임입니다.");
        }
    }

    private void validateDuplicateNickname(String name) {
        Member findMember = memberRepository.findByNickname(name);
        if (findMember == null) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    //닉네임으로 멤버 찾기
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    //전체 멤버 찾기
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //닉네임 변경하기
    @Transactional
    public void updateNickname(Long id, String nickname) {
        Member member = memberRepository.findById(id);
        if (member.getNickname() == nickname) {
            throw new IllegalStateException("현재 닉네임과 다른 닉네임을 입력해주세요.");
        }
        validateDuplicateNickname(nickname);

        member.setNickname(nickname);
    }
}
