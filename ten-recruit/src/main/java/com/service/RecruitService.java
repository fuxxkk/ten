package com.service;

import base.BaseService;
import com.entity.Recruit;

import java.util.List;

public interface RecruitService extends BaseService<Recruit> {

    /**
     * 根据状态查询
     * @param state
     * @return
     */
    List<Recruit> findByState(String state);

    /**
     * 查找最新的
     * @return
     */
    List<Recruit> findNewest();
}
