package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.ApplyResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyResultJpaRepository extends JpaRepository<ApplyResult, String> {
    public List<ApplyResult> findByApplyId(int aId);

    public List<ApplyResult> findByRno(int rno);

    public long countByRno(int rno);
}
