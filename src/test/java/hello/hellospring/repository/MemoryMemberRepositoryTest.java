package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {                                       ///Spring TestCase

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach                           ///AfterEach란 테스트가 하나씩 끝날 때 마다 실행해주는 역할
    public void afterEach()
    {
    repository.clearStore();            ///TEST가 끝날때 마다 지워준다.
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findByID(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("이범기");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("이범기1");
        repository.save(member2);

        Member result = repository.findByName("이범기").get();
        assertThat(result).isEqualTo(member1);

        Member result1 = repository.findByName("이범기1").get();
        assertThat(result1).isEqualTo(member2);

    }


    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("이범기");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("이범기1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }



}
