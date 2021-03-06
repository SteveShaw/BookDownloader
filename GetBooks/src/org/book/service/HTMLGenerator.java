package org.book.service;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HTMLGenerator {

	private String genFilePath;
		
//	private StringBuilder stringBuilder = new StringBuilder();
	
	private List<String> divStringList = new ArrayList<String>();
	
	private int id = 1;


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
		bufferedWriter.write("<div>");
		bufferedWriter.newLine();
		bufferedWriter.write("<h2>Selected Books</h2>");
		bufferedWriter.newLine();
		bufferedWriter.write("<p id=\"urls\"></p>");
		bufferedWriter.newLine();
		bufferedWriter.write("</div>");
		bufferedWriter.newLine();
		bufferedWriter.write("<script src=\"utility.js\"></script>");
		bufferedWriter.newLine();
		generateBody(bufferedWriter);
		bufferedWriter.close();
		
		//Desktop.getDesktop().browse(file.toURI());
	}
	
	public void addBook(Book book) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append(String.format("<input class=\"%05d\" onclick=\"onCheck(this)\" type=\"checkbox\" name=\"choose\" style=\"width:30px;height:30px\">Choose This<br>", this.id));
		sb.append(book.getDivTitle());
		sb.append("<br>");
		sb.append(book.getDivIntr());
		sb.append("<br>");
		sb.append(String.format("<div class=\"%05d\">", this.id++));
		for(String url: book.getUrls()) {
			sb.append(url);
			sb.append("<br>");
		}
		sb.append("</div>");
		sb.append("</div>");
		this.divStringList.add(sb.toString());
	}
}
