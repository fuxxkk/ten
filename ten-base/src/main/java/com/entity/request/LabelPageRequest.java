package com.entity.request;

import base.PageRequest;
import lombok.Data;

@Data
public class LabelPageRequest extends PageRequest {

    String labelName;
}
