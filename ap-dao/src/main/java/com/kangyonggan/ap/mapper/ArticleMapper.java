package com.kangyonggan.ap.mapper;

import com.kangyonggan.common.mybatis.MyMapper;
import com.kangyonggan.ap.model.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangyonggan
 * @since 8/1/18
 */
@Mapper
public interface ArticleMapper extends MyMapper<Article> {
}