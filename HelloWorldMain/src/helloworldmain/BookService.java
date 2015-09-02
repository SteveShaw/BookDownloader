package helloworldmain;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.book.service.Book;
import org.book.service.HTMLGenerator;
import org.book.service.HTMLParser;

public class BookService {

	private String path;
	
	private String htmlPath;

	public void setPath(String path) {
		this.path = path;
	}

	public boolean action() throws IOException {
		Path path = Paths.get(this.path);
		
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
			return false;
		}
		
		Path dirBook = Paths.get(path+"\\generated");
		if(Files.notExists(dirBook)) {
			Files.createDirectory(dirBook);
		}
		
		htmlPath = path+"\\"+"generated\\book.html";
		
		HTMLGenerator generator = new HTMLGenerator(htmlPath);
		for(Book book: parser.getListBooks()) {
			generator.addBook(book);
		}
		
		generator.action();
	
		return true;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	
}
