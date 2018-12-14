package com.service;

import base.BaseService;
import base.PageResult;
import com.entity.Problem;
import com.entity.request.ProblemPageRequest;

import java.util.List;

public interface ProblemService extends BaseService<Problem> {

    /**
     * 根据标签ID查询最新问题列表
     * @param id
     * @return
     */
    List<Problem> findByLableId(String id);


    /**
     * 根据标签ID查询热门问题列表
     */
    PageResult<Problem> findHotProblem(ProblemPageRequest problemPageRequest);

    /**
     * 根据标签ID查询等待回答列表
     */
    PageResult<Problem> findWaitProblemByLabelId(ProblemPageRequest problemPageRequest);
}
