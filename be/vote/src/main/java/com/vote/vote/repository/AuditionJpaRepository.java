package com.vote.vote.repository;

import java.util.ArrayList;
import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vote.vote.db.dto.Audition;

@Repository
public interface AuditionJpaRepository  extends JpaRepository<Audition, String>{
	public ArrayList<Audition> findAll();
	public Audition findByAuditionid(int auditionid);
	public ArrayList<Audition> findByAtitle(String keyword);
	public List<Audition> findByRid(int rid);
	

}
