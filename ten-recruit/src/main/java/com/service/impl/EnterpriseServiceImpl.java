package com.service.impl;

import base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.Enterprise;
import com.mapper.EnterpriseMapper;
import com.service.EnterpriseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise, EnterpriseMapper> implements EnterpriseService {


    @Override
    public List<Enterprise> findByIsHot() {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("ishot", "1");
        return mapper.selectList(wrapper);
    }
}
