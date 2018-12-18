package com.service.impl;

import base.BaseServiceImpl;
import com.entity.Article;
import com.mapper.ArticleMapper;
import com.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, ArticleMapper> implements ArticleService {

    @Autowired
    private RedisTemplate redisTemplate ;

    @Override
    public Boolean examine(String id) {
        Article article = new Article();
        article.setId(id);
        article.setState(1);
        return update(article);
    }

    @Override
    public Integer updateThumbup(String id) {
        Article article = selectOne(id);
        article.setThumbup(article.getThumbup()+1);
        update(article);
        return article.getThumbup();
    }

    @Override
    public Article findByIdCache(String id) {
        return null;
    }
}
