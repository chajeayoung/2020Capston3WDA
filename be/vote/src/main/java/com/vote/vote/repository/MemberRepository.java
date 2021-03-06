package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.dto.ADetail;
import com.vote.vote.db.dto.Member;

public interface MemberRepository {
   public List<Member> getInfo(int applyId);
   List<Member> getInfoNoDistincList(int applyId);
}