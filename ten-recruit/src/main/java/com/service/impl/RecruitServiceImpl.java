package com.service.impl;

import base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.Recruit;
import com.mapper.RecruitMapper;
import com.service.RecruitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitServiceImpl extends BaseServiceImpl<Recruit, RecruitMapper> implements RecruitService {
    @Override
    public List<Recruit> findByState(String state) {

        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("state", state);
        wrapper.orderByDesc("createtime");
        Page<Recruit> page = new Page<>(0,4);
        IPage<Recruit> recruitIPage = mapper.selectPage(page, wrapper);
        return recruitIPage.getRecords();
    }

    @Override
    public List<Recruit> findNewest() {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.ne("state", "0");
        wrapper.orderByDesc("createtime");
        Page<Recruit> page = new Page<>(0,12);
        IPage<Recruit> recruitIPage = mapper.selectPage(page, wrapper);
        return recruitIPage.getRecords();
    }
}
