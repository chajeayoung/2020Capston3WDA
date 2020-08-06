package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.customSelect.CustomAuditionCon;
import com.vote.vote.db.dto.AuditionCon;
import com.vote.vote.db.dto.QAuditionCon;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAuditionConRepositoryImpl implements CustomAuditionConRepository {

    @PersistenceContext
    private EntityManager em;

    private QAuditionCon auditionCon = QAuditionCon.auditionCon;

    @Override
    public CustomAuditionCon getMyAuditionCon(int rId, Pageable page) {
        
        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
        booleanBuilder.and(auditionCon.rid.eq(rId));

        List<AuditionCon> audition = query.select(auditionCon).from(auditionCon).offset(page.getOffset()).limit(page.getPageSize()).where(booleanBuilder).fetch();  //fetch 반환값이 list다

        Long count = query.select(auditionCon).from(auditionCon).where(booleanBuilder).fetchCount();

        CustomAuditionCon customAuditionCon = new CustomAuditionCon();
        customAuditionCon.setAuditionCon(audition);
        customAuditionCon.setCount(count.intValue());

        return customAuditionCon;
    }
    
}