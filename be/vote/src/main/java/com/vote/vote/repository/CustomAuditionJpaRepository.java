package com.vote.vote.repository;



import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.AuditionCon;


public interface CustomAuditionJpaRepository{

	public List<AuditionCon> findByRid(List<Audition> audition,Pageable page);


}
