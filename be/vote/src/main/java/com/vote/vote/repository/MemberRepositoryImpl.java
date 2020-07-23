package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nimbusds.oauth2.sdk.id.Audience;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.dto.ADetail;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.QADetail;
import com.vote.vote.db.dto.QMember;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;
    private QMember member = QMember.member;
    private QADetail aDetail = QADetail.aDetail;

    // 응모인원 출력
    @Override
    public List<Member> getInfo(int applyId) {
        // TODO Auto-generated method stub
        JPAQueryFactory query = new JPAQueryFactory(em);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(aDetail.applyId.eq(applyId));
        List<Member> list = query.select(Projections.fields(Member.class, member.name, member.phone, member.no))
                .distinct().from(member).join(aDetail).on(member.no.eq(aDetail.rId)).where(booleanBuilder).fetch();

        return list;
    }

    // 추첨로직용 쿼리
    @Override
    public List<Member> getInfoNoDistincList(int applyId) {
        // TODO Auto-generated method stub
        JPAQueryFactory query = new JPAQueryFactory(em);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(aDetail.applyId.eq(applyId));
        List<Member> list = query.select(Projections.fields(Member.class, member.name, member.phone)).from(member)
                .join(aDetail).on(member.no.eq(aDetail.rId)).where(booleanBuilder).fetch();

        return list;
    }
}