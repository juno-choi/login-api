package com.juno.eats.api.repository.member;

import com.juno.eats.api.domain.member.Member;
import com.juno.eats.api.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;



    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findByMemberId(String memberId){
        QMember member = QMember.member;
        JPAQueryFactory query = new JPAQueryFactory(em);

        return (Member)query.from(member).where(member.memberId.eq(memberId)).fetchOne();
    }
}

