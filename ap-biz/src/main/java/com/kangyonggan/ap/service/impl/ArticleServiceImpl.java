package com.kangyonggan.ap.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.ap.constants.ApplyStatus;
import com.kangyonggan.ap.model.Article;
import com.kangyonggan.ap.service.ArticleService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Status;
import com.kangyonggan.extra.core.annotation.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 8/1/18
 */
@Service
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Override
    public List<Article> searchArticles(Params params) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();

        String createdUsername = params.getQuery().getString("createdUsername");
        if (StringUtils.isNotEmpty(createdUsername)) {
            criteria.andEqualTo("createdUsername", createdUsername);
        }
        String applyStatus = params.getQuery().getString("applyStatus");
        if (StringUtils.isNotEmpty(applyStatus)) {
            criteria.andEqualTo("applyStatus", applyStatus);
        }
        String title = params.getQuery().getString("title");
        if (StringUtils.isNotEmpty(title)) {
            criteria.andLike("title", StringUtil.toLike(title));
        }
        Date startDate = params.getQuery().getDate("startDate");
        if (startDate != null) {
            criteria.andGreaterThanOrEqualTo("createdTime", startDate);
        }
        Date endDate = params.getQuery().getDate("endDate");
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("createdTime", endDate);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("id desc");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public Article findArticleById(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setApplyStatus(ApplyStatus.COMPLETE.getCode());
        article.setStatus(Status.ENABLE.getCode());
        return myMapper.selectOne(article);
    }


    @Override
    @Log
    public Article findPrevArticle(Long id) {
        Example example = new Example(Article.class);

        example.createCriteria().andEqualTo("status", Status.ENABLE.getCode()).andLessThan("id", id);
        example.setOrderByClause("id desc");
        example.selectProperties("id", "title");

        PageHelper.startPage(1, 1);
        List<Article> articles = myMapper.selectByExample(example);
        if (articles.isEmpty()) {
            return null;
        }

        return articles.get(0);
    }

    @Override
    @Log
    public Article findNextArticle(Long id) {
        Example example = new Example(Article.class);

        example.createCriteria().andEqualTo("status", Status.ENABLE.getCode()).andGreaterThan("id", id);
        example.setOrderByClause("id asc");
        example.selectProperties("id", "title");

        PageHelper.startPage(1, 1);
        List<Article> articles = myMapper.selectByExample(example);
        if (articles.isEmpty()) {
            return null;
        }

        return articles.get(0);
    }

    @Override
    @Log
    public void saveArticle(Article article) {
        myMapper.insertSelective(article);
    }

    @Override
    @Log
    public void updateArticle(Article article) {
        myMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @Log
    public void deleteArticle(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    public void replyArticles(String ids, String type, String replyMsg) {
        Article article = new Article();
        article.setReplyMsg(replyMsg);
        article.setApplyStatus(type);

        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("applyStatus", ApplyStatus.APPLY.getCode())
                .andIn("id", Arrays.asList(ids.split(",")));

        myMapper.updateByExampleSelective(article, example);

    }

}
