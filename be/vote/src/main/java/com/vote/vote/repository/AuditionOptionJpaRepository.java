package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.AuditionOption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditionOptionJpaRepository extends JpaRepository<AuditionOption, String>{

    public List<AuditionOption> findByAuditionIdOrderByNo(int aId);

}