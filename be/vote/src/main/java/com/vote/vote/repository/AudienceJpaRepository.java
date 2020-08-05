package com.vote.vote.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.vote.vote.db.dto.Audience;

@Repository
public interface AudienceJpaRepository extends JpaRepository<Audience, Integer> {
    public Audience findById(int applyId);

    public Audience deleteById(int applyId);
    List<Audience> findByaTitleContaining(String aTitle);

    Page<Audience> findAllByrId(Pageable pageable, int rId);

    public List<Audience> findByrId(int rId);

    @Modifying
    @Transactional
    @Query("update Audience set a_title =:title, a_startdate=:startdate, a_enddate=:enddate, a_recruits=:recruits, a_limit=:limit, a_price=:price, img=:img, a_content=:content where apply_id=:applyid")
    void audienceUpdate(@Param("title") String title, 
            @Param("startdate") Date startdate,
            @Param("enddate") Date enddate, 
            @Param("recruits") int recruits,
            @Param("limit") int limit,
            @Param("price") int price, 
            @Param("img") String img, 
            @Param("content") String content,
            @Param("applyid") int applyid
            );

    // @Modifying
    // @Transactional
    // @Query("update B_apply set a_title =:title")
    // void popularUpdate(@Param("title") String aTitle);
    // @Query(value = "delete from Audience where apply_id = ?1", nativeQuery=true)
    // public void deleteByApplyId(@Param("applyId")int applyId);
}