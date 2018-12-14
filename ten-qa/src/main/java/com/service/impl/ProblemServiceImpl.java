package com.service.impl;

import base.BaseServiceImpl;
import base.PageResult;
import com.entity.Problem;
import com.entity.request.ProblemPageRequest;
import com.mapper.ProblemMapper;
import com.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl extends BaseServiceImpl<Problem, ProblemMapper> implements ProblemService {


    @Override
    public List<Problem> findByLableId(String id) {
        return mapper.findByLableId(id);
    }

    @Override
    public PageResult<Problem> findHotProblem(ProblemPageRequest problemPageRequest) {
        problemPageRequest.setPage(problemPageRequest.getPage()-1);
        List<Problem> hotProblem = mapper.findHotProblem(problemPageRequest);
        PageResult<Problem> result = new PageResult<>();
        Integer total = mapper.selectCount(null);
        result.setRows(hotProblem);
        result.setTotal(total.longValue());
        return result;
    }

    @Override
    public PageResult<Problem> findWaitProblemByLabelId(ProblemPageRequest problemPageRequest) {
        problemPageRequest.setPage(problemPageRequest.getPage()-1);
        List<Problem> problemList = mapper.findWaitProblemByLabelId(problemPageRequest);
        Integer total = mapper.selectCount(null);
        PageResult<Problem> result = new PageResult<Problem>(total.longValue(),problemList);
        return result;
    }
}
