package com.entity.request;

import base.PageRequest;
import lombok.Data;

@Data
public class ProblemPageRequest extends PageRequest {

    private String labelId;
}
