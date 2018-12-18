package com.service;

import base.BaseService;
import com.entity.Article;

public interface ArticleService extends BaseService<Article> {
    /**
     * 审核
     * @param id
     * @return
     */
    Boolean examine(String id);

    /**
     * 点赞
     * @param id
     * @return
     */
    Integer updateThumbup(String id);

    /**
     * 根据ID查询实体(使用redis缓存）
     * @param id
     * @return
     */
    Article findByIdCache(String id);
}
