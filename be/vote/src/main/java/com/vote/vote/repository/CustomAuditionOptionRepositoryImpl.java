package com.vote.vote.repository;

import java.util.List;

import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vote.vote.db.customSelect.CustomAuditionOption;
import com.vote.vote.db.dto.QAuditionOption;
import com.vote.vote.db.dto.QAuditionOptionValue;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAuditionOptionRepositoryImpl implements CustomAuditionOptionRepository {

    @PersistenceContext
    private EntityManager em;

    // private QAuditionOption option = QAuditionOption.auditionOption;

    // private QAuditionOptionValue value = QAuditionOptionValue.auditionOptionValue;

    @Override
    public List<CustomAuditionOption> getOptionWithValue(int audiId, int conId) {
        
        // String sql = 
        // " select o.no optionNo, o.audition_id auditionId, o.name name,TO_CHAR(DBMS_LOB.SUBSTR(v.value, 4000)) value, v.audition_con  conId"
        // +" from audition_option o, audition_option_value v "
        // +" where o.no = v.option_no(+) "
        // +" and v.audition_con = "+conId;

        String sql = 
        " select o.no optionNo, o.audition_id auditionId, o.name ,  NVL(TO_CHAR(DBMS_LOB.SUBSTR( v.value, 4000)), 'X') value , NVL(v.audition_con, "+conId+" ) conId " 
        +" from  "
        +" (select *  "
        +" from audition_option  " 
        +" where audition_id = "+audiId+" ) o "
        +" , (select * from audition_option_value a where audition_id = "+audiId+" and a.audition_con = "+conId+" ) v "
        +" where o.no = v.option_no(+) ";



        Query nativeQuery  = em.createNativeQuery(sql);
        // .setParameter("mId", managerId);
        // .getResultList();
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<CustomAuditionOption>  result = jpaResultMapper.list(nativeQuery, CustomAuditionOption.class);

        return result;
    }
        
    
    
    
}