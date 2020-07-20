package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.FilteredClause;
import com.querydsl.core.dml.StoreClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.vote.vote.db.dto.PopularBoard;
import com.vote.vote.db.dto.QPopularBoard;
import com.vote.vote.db.customSelect.CustomPopularBoard;
import com.vote.vote.db.dto.Vote;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomPopularBoardRepositoryImpl implements CustomPopularBoardRepository{

    @PersistenceContext
    private EntityManager em;

    private QPopularBoard pm = QPopularBoard.popularBoard;

    private QHashTag hm = QHashTag.hashTag;

    private long count = 0;
    
    
    @Override
    public List<PopularBoard> findAll(Pageable pageable) {
        
        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.

        List<PopularBoard> PopularBoard = query.select(pm).from(pm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).fetch();  //fetch 반환값이 list다

        count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();

        return PopularBoard;
    }
    
    @Override
    public CustomPopularBoard findById(int c,Pageable pageable, String hash) {
        

        if(!hash.equals("")){
            // String sql = 
            // "  select * from popular_board where  "
            // +" p_id in (select p_id from hashtag where to_char(hashtag) = "+hash+" AND popular_id = "+c+");";
            
     
            
            // Query nativeQuery  = em.createNativeQuery(sql);
            // // .setParameter("mId", managerId);
            // // .getResultList();
            // JpaResultMapper jpaResultMapper = new JpaResultMapper();
            // List<PopularBoard>  result = jpaResultMapper.list(nativeQuery, PopularBoard.class);


            JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

            BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.

            BooleanBuilder booleanBuilder2 = new BooleanBuilder(); //여기다가 조건절을 단다.
            booleanBuilder2.and(hm.hashtag.eq(hash));
            booleanBuilder2.and(hm.popularid.eq(c));

            booleanBuilder.and(pm.id.eq(query.select(hm.pid).from(hm).where(booleanBuilder2).fetch()));
            

            List<PopularBoard> PopularBoard = query.select(pm).from(pm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).orderBy(pm.date.desc()).fetch();  //fetch 반환값이 list다

            Long count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();
            CustomPopularBoard customPopularResult = new CustomPopularBoard();

            customPopularResult.setPopularBoardList(result);
            customPopularResult.setCount(count.intValue());
            
            return customPopularResult;
        }
       

        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
        
        booleanBuilder.and(pm.popularid.eq(c));

        List<PopularBoard> PopularBoard = query.select(pm).from(pm).where(booleanBuilder).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).orderBy(pm.date.desc()).fetch();  //fetch 반환값이 list다

        Long count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();

        CustomPopularBoard originPopularResult = new CustomPopularboard();
        
        
        originPopularResult.setPopularBoardList(result);
        originPopularResult.setCount(count.intValue());

        return originPopularResult;

    }
    

    @Override
    public long CountAll() {
    	
        	
        return count;
    }
    

    
    @Override
    public long CountById(int c) {
    	
    
    	   
           JPAQueryFactory query = new JPAQueryFactory(em);


           BooleanBuilder booleanBuilder = new BooleanBuilder();

           booleanBuilder.and(pm.popularid.eq(c));


           List<PopularBoard> boardList =  query.select(pm).from(pm).where(booleanBuilder).fetch();
           
//           System.out.println(" 개수 :"+boardList);

          return boardList.size();

    }

    @Override
    public List<PopularBoard> getHashTag(int popularid, String hash){
    	
        	   
           JPAQueryFactory query = new JPAQueryFactory(em);

           BooleanBuilder booleanBuilder = new BooleanBuilder();

           booleanBuilder.and(pm.id.in(query.select(pm).from(pm).where(booleanBuilder).fetch()));

           List<PopularBoard> hashList =  query.select(pm).from(pm).where(booleanBuilder).fetch();
           


          return hashList;

    }


    
}