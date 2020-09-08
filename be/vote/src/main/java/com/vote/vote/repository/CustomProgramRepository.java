package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Program;

import org.springframework.data.domain.Pageable;

public interface CustomProgramRepository {
    

    public Program findByPK(String programName);

    public List<Program> findAll(Pageable page);

}