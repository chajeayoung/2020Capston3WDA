package com.vote.vote.repository;

import java.util.List;

import com.vote.vote.db.customSelect.CustomOrderState;
import com.vote.vote.db.customSelect.CustomOrderStatePop;
import com.vote.vote.db.customSelect.CustomOrderStatePopAge;
import com.vote.vote.db.customSelect.CustomOrderStatePopGender;

public interface CustomOrderReopsitoy {
    List<CustomOrderState> getOrderStateByManagerId(int managerId);

    List<CustomOrderStatePop> getOrderStatePopByProgramId(int pId);


    List<CustomOrderStatePopGender> getOrderStatePopGenderByProgramId(int pId);


    List<CustomOrderStatePopAge> getOrderStatePopPopAgeByProgramId(int pId);
}