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
    private RedisTemplate redisTemplate;

    private final String REDIXPRRFIX = "article_";

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
        article.setThumbup(article.getThumbup() + 1);
        update(article);
        return article.getThumbup();
    }

    @Override
    public Article findByIdCache(String id) {
        String key = REDIXPRRFIX + id;
        Article article = (Article) redisTemplate.opsForValue().get(key);
        if (article == null) {
            article = mapper.selectById(id);
            redisTemplate.opsForValue().set(key,article);
        }
        return article;
    }

    @Override
    public Boolean update(Article article) {
        redisTemplate.delete(REDIXPRRFIX + article.getId());
        return super.update(article);
    }

    @Override
    public Boolean delete(String id) {
        redisTemplate.delete(REDIXPRRFIX + id);
        return super.delete(id);
    }
}
