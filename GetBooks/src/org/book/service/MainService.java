package org.book.service;


import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainService {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		File input = new File("C:/Dev/Test/test.htm");
//		Document doc = Jsoup.parse(input,"UTF-8","");
//		
//		Elements books = doc.select("div.actions");
//		
//		for(Element book: books) {
//			Elements book_urls = book.select("a[href]");
//			if(book_urls.size()>0) {
//				Element book_url = book_urls.first();
//				System.out.println(book_url.attr("href"));
//			}
//		}
//		System.out.println(books.size());
		
		Path path = Paths.get("c:/dev/CDK");
		
		HTMLParser parser = new HTMLParser();
		try(DirectoryStream<Path> ds = Files.newDirectoryStream(path,"*.{htm,html}")) {
			for(Path file: ds) {
				parser.parse(file);
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
		
		if(parser.getListBooks().isEmpty()) {
			System.out.println("No book is found");
			return;
		}
		
		HTMLGenerator generator = new HTMLGenerator("c:/dev/test/demo.html");
		for(Book book: parser.getListBooks()) {
			generator.addBook(book);
		}
		
		generator.action();
		
//		File input = new File("C:/Dev/tjm.html");
//		
//		//Document doc = Jsoup.connect("http://avxhome.se/ebooks/programming_development/pages/2").get();
//		
//		Document doc = Jsoup.parse(input,"UTF-8","");
//
//		StringBuilder sb = new StringBuilder();
//		HTMLGenerator htmlGenerator = new HTMLGenerator("c:/dev/test/demo.html");
//		
//		Elements content = doc.select("div[class=\"text clear-both\"]");
//		
//		Element introduction = content.first();
//		sb.append("<button class=\"btnSelect\">Select</button>");
//		sb.append("<input type=\"checkbox\" name=\"choose\">Choose This<br>");
//		sb.append(introduction.toString());
//		//htmlGenerator.getDivStringList().add(introduction.toString());
//		
//		Elements urls = doc.select("div.text div.center");
//
//		StringBuilder sbURL = new StringBuilder();
//		
//		sbURL.append("<div class=\"book\">");
//
//		for(Element url: urls) {
//			
//			Elements hrefs = url.select("a[href]");
//			
//			
//			if(hrefs.size()>0) {
//				//htmlGenerator.getDivStringList().add(url.toString());
//				
//				
//				
//				for(Element href: hrefs) {
//					String download = href.attr("href");
//					if(download.startsWith("http")){
//						href.text(download);
//					sbURL.append(href.toString());
//					sbURL.append("<br>");
//					}
//				}
//				
//			}
//			
//			
//		}
//		
//		sbURL.append("</div>");
//		
//		sb.append(sbURL.toString());
//	
//		htmlGenerator.getDivStringList().add(sb.toString());
//		
//		
//		htmlGenerator.action();
//		
//		Files.walk(Paths.get("c:/dev/test")).forEach(filePath -> {
//			if(Files.isRegularFile(filePath)) {
//				
//			}
//		});
		
		
		
		
		
		
			

//		for(Element element: divs) {
//			Elements titles = element.select("div.title");
//			
//			for(Element title: titles) {
//				Elements urls = title.select("a[href]");
//				
//				if (urls.size()>0) {
//				
//					Element url = urls.first();
//					
//					if(url.hasAttr("href")) {
//						System.out.println(url.toString());
//					}
//				}
//				
//				
//			}
//			
//			
//			//Elements media = element.select("[src]");
//			
////			for(Element src: media) {
////				if(src.tagName().equals("img")) {
////					System.out.println(src.attr("abs:src"));
////				}
////			}
//			
//		}
		
		
//		htmlGenerator.getDivStringList().add(introduction.toString());
//		htmlGenerator.getDivStringList().add(url.toString());
//		htmlGenerator.action();
	}

}
