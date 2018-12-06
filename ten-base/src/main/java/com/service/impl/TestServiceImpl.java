package com.service.impl;

import base.BaseServiceImpl;
import com.entity.Label;
import com.mapper.LabelMapper;
import com.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends BaseServiceImpl<Label,LabelMapper> implements TestService {

    @Override
    public Label findOne(String id) {
        return this.mapper.selectById(id);
    }
}
