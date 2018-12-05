package com.service.impl;

import base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.Label;
import com.entity.request.LabelPageRequest;
import com.mapper.LabelMapper;
import com.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl extends BaseServiceImpl<LabelMapper> implements LabelService {


    @Override
    public List<Label> findPage(LabelPageRequest labelPageRequest) {
        Page<Label> labelPage = new Page<>(labelPageRequest.getPage(), labelPageRequest.getPageSize());
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.like("labelname", labelPageRequest.getLabelName());
        IPage<Label> selectPage = mapper.selectPage(labelPage, wrapper);
        return selectPage.getRecords();
    }
}
