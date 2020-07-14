package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.customSelect.CustomAuditionOption;

public interface CustomAuditionOptionRepository {
    
    public List<CustomAuditionOption> getOptionWithValue(int audiId,int conId);
}