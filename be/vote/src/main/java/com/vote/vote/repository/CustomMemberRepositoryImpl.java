package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.QMember;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    @PersistenceContext
    private EntityManager em;

    private QMember qm = QMember.member;

    private long count = 0;
    
    
    @Override
    public List<Member> findAll(Pageable pageable) {
        
        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.

        List<Member> member = query.select(qm).from(qm).offset(pageable.getOffset()).limit(pageable.getPageSize()).orderBy(qm.no.desc()).where(booleanBuilder).fetch();  //fetch 반환값이 list다

        count = query.select(qm).from(qm).where(booleanBuilder).fetchCount();

        return member;
    }

    @Override
    public long CountAll() {
        
        return count;
    }
    
}