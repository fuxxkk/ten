package com.service;

import com.entity.Label;
import com.entity.request.LabelPageRequest;

import java.util.List;

public interface LabelService {

    List<Label> findPage(LabelPageRequest labelPageRequest);

}
