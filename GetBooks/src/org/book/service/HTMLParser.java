package org.book.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

	private List<Book> listBooks = new ArrayList<Book>();
	
	private static final String keyIntro = "div[class=\"text clear-both\"]";
	
	private static final String keyText = "div[class=\"text\"]";
	
	private static final String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	
	private Pattern urlPat = null;

	
	public HTMLParser() {
		super();
		urlPat = Pattern.compile(regex);
	}


	public void parse(Path input) throws IOException {
		
		Book book = new Book();
		
		Document doc = Jsoup.parse(input.toFile(), "UTF-8","");
		
		Element title = doc.select("div.title").first();
		
		if(title!=null) {
			book.setDivTitle(title.toString());
		}
		
		Element introduction = doc.select(keyIntro).first();
		
		if(introduction!=null) {
			book.setDivIntr(introduction.toString());
		}
		
		
		//get download urls
		Elements texts = doc.select(keyText);

		for(Element text: texts) {
			
			Elements hrefs = text.select("a[href]");
			
			if(hrefs!=null){
				
				for(Element href: hrefs) {

					String url = href.attr("href");
					if(urlPat.matcher(url).matches()){
						System.out.println(url);
						href.text(url);
						book.addUrl(href.toString());
					}
				}

			}
		}
		
		listBooks.add(book);

	}


	public List<Book> getListBooks() {
		return listBooks;
	}

}
