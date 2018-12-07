package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProblemMapper extends BaseMapper<Problem> {

    List<Problem> findByLableId(@Param("labelId") String labelId);
}
