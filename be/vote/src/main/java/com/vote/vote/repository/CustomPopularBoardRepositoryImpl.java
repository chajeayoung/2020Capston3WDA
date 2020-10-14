package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.customSelect.CustomPopularBoard;
import com.vote.vote.db.dto.PopularBoard;
import com.vote.vote.db.dto.QHashTag;
import com.vote.vote.db.dto.QMember;
import com.vote.vote.db.dto.QPopularBoard;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.lang.Nullable;

@Repository
public class CustomPopularBoardRepositoryImpl implements CustomPopularBoardRepository{

    @PersistenceContext
    private EntityManager em;

    private QPopularBoard pm = QPopularBoard.popularBoard;

    private QHashTag hm = QHashTag.hashTag;

    private QMember mm = QMember.member;

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
    public CustomPopularBoard findById(int c,Pageable pageable,@Nullable int option, String text) { 
        
        System.out.println("option :"+option);
        System.out.println("text :"+text);

        //option 0=default 1=제목 2=제목,내용 3=해쉬 4=작성자
        if(option!=0){          
            
            JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

            BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
    
            BooleanBuilder booleanBuilder2 = new BooleanBuilder(); //여기다가 조건절을 단다.

    

            switch(option){
            case 1: //제목
            booleanBuilder.and(pm.popularid.eq(c));
            booleanBuilder.and(pm.title.contains(text));
            break;


            case 2: //제목+내용
            booleanBuilder.and(pm.popularid.eq(c));
            booleanBuilder2.or(pm.title.contains(text));
            booleanBuilder2.or(pm.content.contains(text));

            booleanBuilder.and(pm.id.in(query.select(pm.id).from(pm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder2).fetch()));
            //booleanBuilder.and(pm.popularid.eq(c));
            break;

            case 3: //해쉬태그
            booleanBuilder.and(pm.popularid.eq(c));
            booleanBuilder2.and(hm.hashtag.contains(text));
            booleanBuilder2.and(hm.popularid.eq(c));
            booleanBuilder.and(pm.id.in(query.select(hm.pid).from(hm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder2).fetch()));
            break;

            case 4: //작성자
            booleanBuilder.and(pm.popularid.eq(c));
            booleanBuilder2.or(mm.nickname.contains(text));
            booleanBuilder2.or(mm.name.contains(text));
           // booleanBuilder2.and(mm.no.in(query.select(mm.no).from(mm).where(booleanBuilder3).fetch()));
            booleanBuilder.and(pm.rid.in(query.select(mm.no).from(mm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder2).fetch()));

            // booleanBuilder.and(pm.popularid.eq(c));
            // booleanBuilder.and(pm.title.eq(text));
            break;
            }




           
            // booleanBuilder2.and(hm.hashtag.eq(hash));
            // booleanBuilder2.and(hm.popularid.eq(c));

            // booleanBuilder.and(pm.id.in(query.select(hm.pid).from(hm).where(booleanBuilder2).fetch()));
            

            List<PopularBoard> PopularBoard = query.select(pm).from(pm).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).orderBy(pm.date.desc()).fetch();  //fetch 반환값이 list다

            Long count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();
            CustomPopularBoard customPopularResult = new CustomPopularBoard();

            customPopularResult.setPopularBoard(PopularBoard);
            customPopularResult.setCount(count.intValue());
            
            return customPopularResult;
        }
       

        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //여기다가 조건절을 단다.
        
        booleanBuilder.and(pm.popularid.eq(c));

        List<PopularBoard> PopularBoard = query.select(pm).from(pm).where(booleanBuilder).offset(pageable.getOffset()).limit(pageable.getPageSize()).where(booleanBuilder).orderBy(pm.date.desc()).fetch();  //fetch 반환값이 list다

        Long count = query.select(pm).from(pm).where(booleanBuilder).fetchCount();

        CustomPopularBoard originPopularResult = new CustomPopularBoard();
        
        
        originPopularResult.setPopularBoard(PopularBoard);
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

    // @Override
    // public List<PopularBoard> getHashTag(int popularid, String hash){
    	
        	   
    //        JPAQueryFactory query = new JPAQueryFactory(em);

    //        BooleanBuilder booleanBuilder = new BooleanBuilder();

    //        booleanBuilder.and(pm.id.in(query.select(pm).from(pm).where(booleanBuilder).fetch()));

    //        List<PopularBoard> hashList =  query.select(pm).from(pm).where(booleanBuilder).fetch();
           


    //       return hashList;

    // }


    
}