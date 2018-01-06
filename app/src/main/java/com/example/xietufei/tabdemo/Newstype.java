package com.example.xietufei.tabdemo;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the newstype database table.
 * 
 */

public class Newstype implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;

	private String info;

	private String name;

	private List<News> news;

	public Newstype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addNews(News news) {
		getNews().add(news);
		news.setNewstype(this);

		return news;
	}

	public News removeNews(News news) {
		getNews().remove(news);
		news.setNewstype(null);

		return news;
	}

}