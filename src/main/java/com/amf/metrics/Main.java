/*
 */
package com.amf.metrics;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.*;

/**
 *
 */

@SuppressWarnings("restriction")
public class Main extends Application {

	/**
	 * Main method from which the application is started
	 * @param args
	 */
	
	public static void main(String args[]) {
		//startMetrics(args);
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) {
		primaryStage.setTitle("Code Metrics");
		
		Pane root = new Pane();
		
		Button exitBtn = new Button();
    exitBtn.setText("Exit");
    exitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	Platform.exit();
        }
    });
		
    FileChooser fileChooser = new FileChooser();
    
	  //Exit button
		exitBtn.setLayoutX(420);
    exitBtn.setLayoutY(400);
    root.getChildren().add(exitBtn);
		
		primaryStage.setScene(new Scene(root, 500, 450));
	  primaryStage.show();
	}
	
	/**
	 * Method that starts the process of calculating metrics for a file or directory
	 * @param args
	 * @return
	 */
	
	public static String startMetrics(String args[]) {
		String result = "";
		MetricsBuilder builder = new MetricsBuilder();
		try {
			result = builder.buildMetrics(args);
			Integer totalLinesOfJava = builder.getTotalLinesOfJava();
			Integer totalJavaFiles = builder.getTotalJavaFiles();
			ArrayList<String> fileNames = builder.getFileNames();
			
			for(String fileName : fileNames) {
				System.out.println(fileName);
			}
			
			System.out.println("Total java files: " + totalJavaFiles + " containing " + totalLinesOfJava + " lines of Java code");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
 }
