package com.kangyonggan.ap.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.ap.model.Article;
import com.kangyonggan.ap.service.ArticleService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Response;
import com.kangyonggan.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public PageInfo list() {
        Params params = getRequestParams();

        List<Article> articles = articleService.searchArticles(params);
        return new PageInfo<>(articles);
    }

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    @PostMapping
    public Response save(Article article) {
        articleService.saveArticle(article);
        return Response.getSuccessResponse();
    }

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("detail")
    public Article detail(@RequestParam("id") Long id) {
        return articleService.findArticleById(id);
    }

    /**
     * 下一篇文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "next")
    public Article next(@RequestParam("id") Long id) {
        return articleService.findNextArticle(id);
    }

    /**
     * 上一篇文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "prev")
    public Article prev(@RequestParam("id") Long id) {
        return articleService.findPrevArticle(id);
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @PutMapping
    public Response update(Article article) {
        articleService.updateArticle(article);
        return Response.getSuccessResponse();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Response delete(@RequestParam("id") Long id) {
        articleService.deleteArticle(id);
        return Response.getSuccessResponse();
    }

    /**
     * 文章审批
     *
     * @param type
     * @param replyMsg
     * @param ids
     * @return
     */
    @PutMapping("reply")
    public Response check(@RequestParam("type") String type, @RequestParam("replyMsg") String replyMsg,
                          @RequestParam("ids") String ids) {
        articleService.replyArticles(ids, type, replyMsg);
        return Response.getSuccessResponse();
    }

}
