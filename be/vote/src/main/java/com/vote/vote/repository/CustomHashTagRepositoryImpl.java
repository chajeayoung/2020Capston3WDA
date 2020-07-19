package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.customSelect.CustomHashTag;
import com.vote.vote.db.dto.HashTag;
import com.vote.vote.db.dto.QHashTag;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomHashTagRepositoryImpl implements CustomHashTagRepository {

    @PersistenceContext
    private EntityManager em;

    private QHashTag qm = QHashTag.hashTag;

    private long count = 0;

    @Override
    public List<HashTag> findAll() {

        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // 여기다가 조건절을 단다.

        List<HashTag> HashTag = query.select(qm).from(qm).where(booleanBuilder).fetch(); // fetch 반환값이 list다

        return HashTag;
    }

    @Override
    public long CountAll() {

        return count;
    }

    @Override
    public List<String> findByPopularId(int popularid) {

        String sql = 
        "select distinct(TO_CHAR(DBMS_LOB.SUBSTR(hashtag, 4000))) hashtag from hashtag  where popular_id="+popularid;

        Query nativeQuery  = em.createNativeQuery(sql);
        // .setParameter("mId", managerId);
        // .getResultList();
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        String  result = jpaResultMapper.toString();

        System.out.println(result);

        return null;

    }
    
}