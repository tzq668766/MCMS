package com.mingsoft.cms.parser.impl;

import com.mingsoft.parser.IParser;
/**
 * 内容发布作者(单标签)
 * 文章作者标签
 * {ms:field.author/}
 * @author 成卫雄
 * QQ:330216230
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ArticleAuthorParser extends IParser {
	
	/**
	 * 文章作者标签
	 */
	private final static String ARTICLE_AUTHOR_FIELD="\\{ms:field.author/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ArticleAuthorParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(ARTICLE_AUTHOR_FIELD);
	}

}
