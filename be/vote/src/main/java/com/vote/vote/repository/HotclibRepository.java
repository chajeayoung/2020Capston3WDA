package com.vote.vote.repository;

import java.util.ArrayList;
import java.util.List;

import com.vote.vote.db.dto.Hotclib;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotclibRepository extends JpaRepository<Hotclib, Integer>{
  public List<Hotclib> findAll();
  public Hotclib findById(int hotclibid);
  public Hotclib deleteById(int hotclibid);
  public List<Hotclib> findByHtitle(String keyword);
  public Hotclib findByHreplycount(int hreplycount);
  public List<Hotclib> findByProgramid(int programid);  
 // public Hotclib findByRid(int rid);

} 