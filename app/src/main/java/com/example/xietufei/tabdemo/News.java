package com.example.xietufei.tabdemo;

import java.io.Serializable;



/**
 * The persistent class for the news database table.
 * 
 */

public class News implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;

	private String content;

	private String info;

	private String title;


	private Newstype newstype;

	public News() {
	}

	public int getId(int id) {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Newstype getNewstype() {
		return this.newstype;
	}

	public void setNewstype(Newstype newstype) {
		this.newstype = newstype;
	}

}