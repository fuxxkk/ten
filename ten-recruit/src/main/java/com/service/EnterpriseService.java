package com.service;

import base.BaseService;
import com.entity.Enterprise;

import java.util.List;

public interface EnterpriseService extends BaseService<Enterprise> {

    List<Enterprise> findByIsHot();

}
