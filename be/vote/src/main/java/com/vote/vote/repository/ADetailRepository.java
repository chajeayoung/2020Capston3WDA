package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.ADetaiId;
import com.vote.vote.db.dto.ADetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.web3j.abi.datatypes.Int;

@Repository
public interface ADetailRepository extends JpaRepository<ADetail, Long> {
    // Long countByADetaiId(ADetaiId aDetaiId);
    // Long countByApplyIdAndRId(int applyId, int rId);
    // Long countByApplyIdAndRId(int applyId, int rId);
    // @Query("SELECT b FROM Board b WHERE b.title like %?1% and b.bno > 0 ORDER BY b.bno desc")

    public List<ADetail> findByApplyId(int applyId);
    @Query(value = "select COUNT(*) from a_detail where apply_id = ?1 and r_id = ?2", nativeQuery=true)
    public Long countByApplyIdAndRId(@Param("applyId")int applyId, @Param("rId")int rId);

    @Query(value = "delete from a_detail where apply_id = ?1", nativeQuery=true)
    public void deleteByApplyId(@Param("applyId")int applyId);


}