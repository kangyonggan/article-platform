package com.kangyonggan.ap.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.ap.constants.ApplyStatus;
import com.kangyonggan.ap.service.ArticleService;
import com.kangyonggan.app.util.MarkdownUtil;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Response;
import com.kangyonggan.common.web.BaseController;
import com.kangyonggan.ap.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/1/18
 */
@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Response list() {
        Response response = Response.getSuccessResponse();
        Params params = getRequestParams();
        params.getQuery().put("applyStatus", ApplyStatus.COMPLETE.getCode());

        List<Article> articles = articleService.searchArticles(params);
        PageInfo pageInfo = new PageInfo<>(articles);

        response.put("pageInfo", pageInfo);
        return response;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id:[\\d]+}")
    public Response detail(@PathVariable("id") Long id) {
        Response response = Response.getSuccessResponse();
        Article article = articleService.findArticleById(id);
        if (article != null) {
            article.setContent(MarkdownUtil.markdownToHtml(article.getContent()));
            Article prevArticle = articleService.findPrevArticle(id);
            Article nextArticle = articleService.findNextArticle(id);

            response.put("prevArticle", prevArticle);
            response.put("nextArticle", nextArticle);
        }

        response.put("article", article);
        return response;
    }
}
