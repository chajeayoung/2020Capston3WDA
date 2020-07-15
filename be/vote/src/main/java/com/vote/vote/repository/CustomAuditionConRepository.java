package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.customSelect.CustomAuditionCon;
import com.vote.vote.db.dto.AuditionCon;

import org.springframework.data.domain.Pageable;

public interface CustomAuditionConRepository {
    public CustomAuditionCon getMyAuditionCon(int rId, Pageable page);
}