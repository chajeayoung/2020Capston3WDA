package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.HashTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Integer>{
   


   // @Query(value = "select distinct(to_char(hashtag)) hashtag from hashtag  where popular_id=:popularid", nativeQuery = true)
   @Query(value = "select distinct(to_char(hashtag)) hashtag from hashtag  where popular_id=:popularid", nativeQuery = true)
   public List<HashTag> getHashTag(@Param ("popularid") int popularid);

   
   @Query(value = "select distinct(to_char(hashtag)) hashtag , nvl(hotclib_id,'0') hotclib_id from hashtag  where popular_id=:popularid", nativeQuery = true)
   public List<HashTag> getHashTag2(@Param ("popularid") int popularid);



}