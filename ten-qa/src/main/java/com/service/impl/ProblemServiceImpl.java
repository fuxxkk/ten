package com.service.impl;

import base.BaseServiceImpl;
import com.entity.Problem;
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
}
