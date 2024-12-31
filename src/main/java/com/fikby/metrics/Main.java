/*
 */
package com.fikby.metrics;

import java.util.ArrayList;

//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//import javafx.stage.*;

/**
 *
 */

@SuppressWarnings("restriction")
public class Main { //extends Application {

	/**
	 * Main method from which the application is started
	 * @param args
	 */
	
	public static void main(String args[]) {
		startMetrics(args);
		//launch(args);
	}
	
//	@Override
//    public void start(Stage primaryStage) {
//		primaryStage.setTitle("Code Metrics");
//		
//		Pane root = new Pane();
//		
//		Button chooseBtn = new Button();
//		chooseBtn.setText("Choose");
//		chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//        	DirectoryChooser chooser = new DirectoryChooser();
//          chooser.setTitle("JavaFX Projects");
//          File defaultDirectory = new File("/Users/afranke");
//          chooser.setInitialDirectory(defaultDirectory);
//          File fileOrDirectory = chooser.showDialog(primaryStage);
//          System.out.println("Selected Directory: " + fileOrDirectory);
//          //startMetrics(fileOrDirectory.getName());
//        }
//    });
//		
//		
//		Button exitBtn = new Button();
//    exitBtn.setText("Exit");
//    exitBtn.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//        	Platform.exit();
//        }
//    });
//		 
//    // Directory choose button
//    chooseBtn.setLayoutX(420);
//    chooseBtn.setLayoutY(100);
//    root.getChildren().add(chooseBtn);
//    
//	  // Exit button
//		exitBtn.setLayoutX(420);
//    exitBtn.setLayoutY(400);
//    root.getChildren().add(exitBtn);
//		
//		primaryStage.setScene(new Scene(root, 500, 450));
//	  primaryStage.show();
//	}
	
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
			Integer totalLinesOfCode = builder.getTotalLinesOfCode();
			Integer totalFiles = builder.getTotalFiles();
			Integer totalCommentLines = builder.getTotalCommentLines();
			ArrayList<String> fileNames = builder.getFileNames();
			
			for(String fileName : fileNames) {
				System.out.println(fileName);
			}

			if(args[1] != null) {
				System.out.println("Total java files: " + totalFiles + " containing " + totalLinesOfCode +
						" lines of " + args[1] + " code");
				System.out.println("Total comment lines: " + totalCommentLines);
			}
			else {
				System.out.println("Total java files: " + totalFiles + " containing " + totalLinesOfCode +
						" lines of java code");
				System.out.println("Total comment lines: " + totalCommentLines);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
 }
