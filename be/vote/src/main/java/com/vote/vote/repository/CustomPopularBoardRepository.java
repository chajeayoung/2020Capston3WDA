package com.vote.vote.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import com.vote.vote.db.customSelect.CustomPopularBoard;

import com.vote.vote.db.dto.PopularBoard;

public interface CustomPopularBoardRepository {
    

    public List<PopularBoard> findAll(Pageable pageable);
    
    public CustomPopularBoard findById(int c,Pageable pageable, String hash);

    public long CountAll();
    
    public long CountById(int popularid);
    
    // public List<PopularBoard> getHashTag(int popularid, String hash);



}