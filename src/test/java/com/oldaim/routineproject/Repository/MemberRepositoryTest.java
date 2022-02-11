package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MemberRepositoryTest {

   private final MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    public void Member_저장_테스트(){

        Member member = Member.builder()
                .id(1L)
                .memberId("oldaim")
                .memberPassword("dgk0911")
                .build();

        memberRepository.save(member);
        Optional<Member> testMember = memberRepository.findById(1L);

        assertThat(testMember.get().getMemberId()).isEqualTo(member.getMemberId());

    }
}