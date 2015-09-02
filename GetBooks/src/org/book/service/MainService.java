package org.book.service;


import java.io.IOException;
import java.nio.file.DirectoryStream;
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
		
	}

}
