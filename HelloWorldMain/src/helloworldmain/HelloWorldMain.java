package helloworldmain;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Element;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class HelloWorldMain extends Application {

	private static final String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	private Pattern urlPat = null;
	
	private BookService bookService = new BookService();
	
	private List<String> getLinks(Pattern pattern, String src) {
		
		List<String> links = new ArrayList<String>();
		
		Matcher matcher = pattern.matcher(src);
		while(matcher.find()){
			links.add(matcher.group());
		}
		
		
		return links;
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
		
	}
	
	public void start(Stage primary) {
		
		urlPat = Pattern.compile(regex);
		
		primary.setTitle("Hello JFX");
		
		Group root = new Group();
		Scene scene = new Scene(root, 1200, 800);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		
//		StringBuilder urlBuilder = new StringBuilder();
//		urlBuilder.append("file:////");
//		urlBuilder.append("c:/dev/test/demo.html");
		
		WebView webView = new WebView();
		webView.setMinWidth(1000);
		webView.setMinHeight(800);
		
//		webView.getEngine().load(urlBuilder.toString());
		
		
		
		VBox vbox = new VBox();
//		VBox.setVgrow(webView, Priority.ALWAYS);
//		vbox.getChildren().add(webView);
		
		HBox hbox = new HBox();
		hbox.setMinWidth(200);
		hbox.setPadding(new Insets(5,10,5,10));
		hbox.setSpacing(5);
		
		
		
		Button btnSave = new Button();
		btnSave.setText("Save");
		
		Button btnOpen = new Button();
		btnOpen.setText("Open");
		//btn.setMinWidth(60);
		
		
		//gridPane.getChildren().addAll(webView,btn);
		hbox.getChildren().addAll(btnOpen,btnSave);
		
//		GridPane.setHalignment(hbox, HPos.CENTER);
//		GridPane.setHgrow(hbox, Priority.ALWAYS);
		gridPane.add(webView, 0, 0);
		gridPane.add(hbox, 1, 0);
		
		
				
	
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				Element element = webView.getEngine().getDocument().getElementById("urls");
				
				if(element!=null){
					String urls = element.getTextContent();
					List<String> links = getLinks(urlPat, urls);
					
					System.out.println(links.size());
					for(String link: links) {
						System.out.println(link);
					}
				}
				
			}
		});
		
		btnOpen.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				DirectoryChooser chooser = new DirectoryChooser();
				chooser.setTitle("Select Book Pages Directory");
						
				File selectDirectory = chooser.showDialog(primary);
				
				if(selectDirectory!=null) {
					
					bookService.setPath(selectDirectory.getAbsolutePath());
					try {
						bookService.action();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					StringBuilder urlBuilder = new StringBuilder();
					urlBuilder.append("file:////");
					urlBuilder.append(bookService.getHtmlPath());
					webView.getEngine().load(urlBuilder.toString());
				}
			}
		});
//		
		
		vbox.getChildren().add(gridPane);
		VBox.setVgrow(gridPane, Priority.ALWAYS);
//		root.getChildren().add(gridPane);
		root.getChildren().add(vbox);
		primary.setScene(scene);
		primary.show();
	}

}
