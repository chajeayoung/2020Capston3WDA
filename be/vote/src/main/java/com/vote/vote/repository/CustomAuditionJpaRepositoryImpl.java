package com.vote.vote.repository;

import java.util.List;

import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.customSelect.CustomAuditionOption;
import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.AuditionCon;
import com.vote.vote.db.dto.Company;
import com.vote.vote.db.dto.QAuditionCon;
import com.vote.vote.db.dto.QAuditionOption;
import com.vote.vote.db.dto.QAuditionOptionValue;
import com.vote.vote.db.dto.QCompany;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAuditionJpaRepositoryImpl implements CustomAuditionJpaRepository {

    @PersistenceContext
    private EntityManager em;
    
    private QAuditionCon am = QAuditionCon.auditionCon;


    

	@Override
	public List<AuditionCon> findByRid(List<Audition> audition,Pageable pageable) {
		
		 JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

	        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
	        
	        if(audition.size()==0) {
	        	return null;
	        }
	        
	        for (Audition audition2 : audition) {
				
	        	booleanBuilder.or(am.auditionid.eq(audition2.getAuditionid()));
	        	
			}
	        
	        
	        List<AuditionCon> auditionCon = query.select(am).from(am).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).fetch();  //fetch 반환값이 list다

//	        count = query.select(cm).from(cm).where(booleanBuilder).fetchCount();

	        return auditionCon;
	        
	} 
        
    
    
    
}