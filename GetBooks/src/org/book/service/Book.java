package org.book.service;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String divIntr;
	
	private String divTitle;
	
	private List<String> urls = new ArrayList<String>();

	public String getDivIntr() {
		return divIntr;
	}

	public void setDivIntr(String divIntr) {
		this.divIntr = divIntr;
	}

	public String getDivTitle() {
		return divTitle;
	}

	public void setDivTitle(String divTitle) {
		this.divTitle = divTitle;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void addUrl(String url) {
		this.urls.add(url);
	}
}
