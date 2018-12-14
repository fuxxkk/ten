package com.service;

import base.BaseService;
import com.entity.Label;
import com.entity.request.LabelPageRequest;

import java.util.List;

public interface LabelService extends BaseService<Label> {

    List<Label> findPage(LabelPageRequest labelPageRequest);

}
