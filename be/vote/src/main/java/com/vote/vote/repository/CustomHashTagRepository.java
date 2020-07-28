package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.customSelect.CustomHashTag;
import com.vote.vote.db.dto.HashTag;

public interface CustomHashTagRepository {
    

    public List<HashTag> findAll();

    public List<CustomHashTag> findByPopularId(int popularid);

    
    public long CountAll();

}