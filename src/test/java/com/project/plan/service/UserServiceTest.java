package com.project.plan.service;

import com.project.plan.domain.User;
import com.project.plan.repository.user.UserRepository;
import com.project.plan.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserServiceImpl memberService;

    @Autowired
    UserRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void join() throws Exception {
        //given
        User member = new User();
        member.setUsername("abc");
        member.setPassword("1234");
        member.setNickname("m1");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(saveId));
    }

    @Test
    @Rollback(value = false)
    public void update() throws Exception {
        //given
        User member = new User();
        member.setUsername("1");
        member.setPassword("12234");
        member.setNickname("m31");

        memberService.join(member);

        //when
        memberService.updateNickname(member.getId(), "임채영");

        //then
        assertEquals(member.getNickname(), "임채영");
    }
}
