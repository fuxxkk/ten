package com.service;

import base.BaseService;
import com.entity.Problem;

import java.util.List;

public interface ProblemService extends BaseService<Problem> {

    /**
     * 根据标签ID查询最新问题列表
     * @param id
     * @return
     */
    List<Problem> findByLableId(String id);
}
