package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.dto.Company;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.QProgram;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProgramRepositoryImpl implements CustomProgramRepository {

    @PersistenceContext
    private EntityManager em;

    private QProgram pm = QProgram.program;

    private long count = 0;


	@Override
	public Program findByPK(String p) {
		JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
        
        booleanBuilder.and(pm.name.eq(p));

        Program program = query.select(pm).from(pm).where(booleanBuilder).fetchOne();  //fetch 반환값이 list다

        
        return program;
	}
    
    @Override
    public List<Program> findAll(Pageable pageable) {
        
        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.

        List<Program> programList = query.select(pm).from(pm).offset(pageable.getOffset()).limit(pageable.getPageSize()).orderBy(pm.id.desc()).where(booleanBuilder).fetch();  //fetch 반환값이 list다

        count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();

        return programList;
    }

    
}