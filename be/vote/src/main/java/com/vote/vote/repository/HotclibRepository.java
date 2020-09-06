package com.vote.vote.repository;


import java.util.List;

import com.vote.vote.db.dto.Hotclib;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotclibRepository extends JpaRepository<Hotclib, Integer>{
  public List<Hotclib> findAll();
  public Hotclib findById(int hotclibid);
  public Hotclib deleteById(int hotclibid);
  public List<Hotclib> findByHtitle(String keyword);
  public Hotclib findByHreplycount(int hreplycount);
  public List<Hotclib> findByProgramid(int programid, Pageable pageable);  
  public List<Hotclib> findByProgramidOrderByHviewcountDesc(int programid);
  public List<Hotclib> findByProgramidOrderByHdateDesc(int programid);
  public List<Hotclib> findAllByOrderByHviewcountDesc();
  public List<Hotclib> findAllByOrderByHdateDesc();
 // public Hotclib findByRid(int rid);

} 