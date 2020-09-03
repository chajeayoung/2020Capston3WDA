package com.vote.vote.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vote.vote.db.dto.AuditionCon;
import org.springframework.data.domain.Sort;

@Repository
public interface AuditionConJpaRepository  extends JpaRepository<AuditionCon, String>{
	public ArrayList<AuditionCon> findAll();
	public AuditionCon findByFormid(int formid);
	public ArrayList<AuditionCon> findByConfirmAndAuditionid(String confirm,int auditionid);
	public ArrayList<AuditionCon> findByConfirm(String confirm);
	public AuditionCon findByAuditionid(Integer auditionid);
	public AuditionCon findByRid(int rid);
	public AuditionCon findByRidOrderByFormidDesc(int rid);
	public List<AuditionCon> findByProgramid(int programid, Pageable pageable);

}
