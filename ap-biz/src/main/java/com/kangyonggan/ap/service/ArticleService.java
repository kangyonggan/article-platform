package com.kangyonggan.ap.service;

import com.kangyonggan.ap.model.Article;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/1/18
 */
public interface ArticleService {

    /**
     * 搜索我的文章
     *
     * @param params
     * @return
     */
    List<Article> searchArticles(Params params);

    /**
     * 查找文章
     *
     * @param id
     * @return
     */
    Article findArticleById(Long id);

    /**
     * 查找上一篇文章
     *
     * @param id
     * @return
     */
    Article findPrevArticle(Long id);

    /**
     * 查找下一篇文章
     *
     * @param id
     * @return
     */
    Article findNextArticle(Long id);
}
