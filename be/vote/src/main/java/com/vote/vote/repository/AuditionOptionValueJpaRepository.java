package com.vote.vote.repository;

import com.vote.vote.db.dto.AuditionOptionValue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditionOptionValueJpaRepository extends JpaRepository<AuditionOptionValue, String>{


    
}