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

    /**
     * 保存文章
     *
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     *
     * @param id
     */
    void deleteArticle(Long id);

    /**
     * 文章审批
     *
     * @param ids
     * @param type
     * @param replyMsg
     */
    void replyArticles(String ids, String type, String replyMsg);
}
