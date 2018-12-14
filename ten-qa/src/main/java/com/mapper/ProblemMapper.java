package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Problem;
import com.entity.request.ProblemPageRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProblemMapper extends BaseMapper<Problem> {

    List<Problem> findByLableId(@Param("labelId") String labelId);

    List<Problem> findHotProblem(@Param("problemRequest") ProblemPageRequest problemRequest);

    List<Problem> findWaitProblemByLabelId(@Param("problemRequest") ProblemPageRequest problemRequest);
}
