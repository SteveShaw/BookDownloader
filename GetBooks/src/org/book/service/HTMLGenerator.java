package org.book.service;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HTMLGenerator {

	private String path;
	
//	private StringBuilder stringBuilder = new StringBuilder();
	
	private List<String> divStringList = new ArrayList<String>();




	public HTMLGenerator(String path) {
		super();
		this.path = path;
	}

	public List<String> getDivStringList() {
		return divStringList;
	}

	public String getPath () {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		File file = new File(this.path);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		
		bufferedWriter.write("<html>");
		bufferedWriter.write("<meta charset=\"UTF-8\">");
		bufferedWriter.newLine();
		generateBody(bufferedWriter);
		bufferedWriter.close();
		
		Desktop.getDesktop().browse(file.toURI());
	}
}
