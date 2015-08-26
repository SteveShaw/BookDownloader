package org.book.service;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HTMLGenerator {

	private String genFilePath;
		
//	private StringBuilder stringBuilder = new StringBuilder();
	
	private List<String> divStringList = new ArrayList<String>();




	public HTMLGenerator(String path) {
		super();
		this.genFilePath = path;
	}

	public List<String> getDivStringList() {
		return divStringList;
	}

	public String getPath () {
		return genFilePath;
	}

	public void setPath(String path) {
		this.genFilePath = path;
	}
	
	
	private void generateBody(BufferedWriter writer) throws IOException {
		writer.write("<body>");
		writer.newLine();
		for(String divString: this.getDivStringList()) {
			writer.write("<hr>");
			writer.write(divString);
			writer.newLine();
		}
		writer.write("</body>");
	}
	
	public void action() throws IOException {
		File file = new File(this.genFilePath);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		
		bufferedWriter.write("<html>");
		bufferedWriter.write("<meta charset=\"UTF-8\">");
		bufferedWriter.newLine();
		generateBody(bufferedWriter);
		bufferedWriter.close();
		
		Desktop.getDesktop().browse(file.toURI());
	}
	
	public void addBook(Book book) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<input type=\"checkbox\" name=\"choose\" style=\"width:30px;height:30px\">Choose This<br>");
		sb.append(book.getDivTitle());
		sb.append("<br>");
		sb.append(book.getDivIntr());
		sb.append("<br>");
		for(String url: book.getUrls()) {
			sb.append(url);
			sb.append("<br>");
		}
		sb.append("</div>");
		this.divStringList.add(sb.toString());
	}
}
