package com.fikby.metrics;

import com.fikby.metrics.language.LanguageFileExt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetricsBuilder {
	
	private String allFilesList;
	private Integer totalLinesOfCode = 0;
	private Integer totalFiles = 0;
	private Integer totalCommentLines = 0;
	
	private ArrayList<String> fileNames = new ArrayList<String>();
	
	/* Getters and Setters */
	
	public Integer getTotalLinesOfCode() {
		return this.totalLinesOfCode;
	}
	
	public void setTotalLinesOfCode(Integer totalLinesOfCode) {
		this.totalLinesOfCode = totalLinesOfCode;
	}
	
	public Integer getTotalCommentLines() {
		return this.totalCommentLines;
	}
	
	public void setTotalCommentLines(Integer totalCommentLines) {
		this.totalCommentLines = totalCommentLines;
	}
	public Integer getTotalFiles() {
		return this.totalFiles;
	}
	
	public void setTotalFiles(Integer totalJavaFiles) {
		this.totalFiles = totalJavaFiles;
	}
	
	public ArrayList<String> getFileNames() {
		return this.fileNames;
	}
	
	public void setFileNames(ArrayList<String> fileNames) {
		this.fileNames = fileNames;
	}
	
	/* Member methods */
	
	/**
	 * Main calling method of this class
	 * @param args
	 * @return
	 * @throws IOException
	 */
	
	public String buildMetrics(String args[]) throws IOException {
		String fileOrDirectory = args[0];
		String projectLanguage = "java";

		if(args[1] != null) {
			projectLanguage = args[1];
		}
		File newFile = new File(fileOrDirectory);
		if(newFile.isDirectory()) {
			processFiles(newFile.listFiles(), projectLanguage);
		}
		else
			buildFileMetrics(fileOrDirectory);
		String fileList = allFilesList;
		return fileList;
	}
	
	/**
	 * Method that calculates the metrics for a given file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	
	public FileMetrics buildFileMetrics(String fileName) throws IOException {
		FileMetrics metrics = new FileMetrics();
		//System.out.println("File name: " + fileName);
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(fileName.trim());
			br = new BufferedReader(fr);
			boolean inCommentBlock = false;
			//String sCurrentLine;
			Integer linesOfCode = 0;
			Integer lines = 0;
			String line = "";
			Integer commentLines = 0;
			while ((line = br.readLine()) != null) {
				if(line.trim().startsWith("//") || inCommentBlock) {
					commentLines++;
				}
				else if(line.trim().startsWith("/*")) {
					inCommentBlock = true;
					commentLines++;
				}
				else {
					linesOfCode++;
				}
				if(line.trim().endsWith("*/")) {
					inCommentBlock = false;
				}
				if(!line.trim().isEmpty())
					lines++;
				//System.out.println(sCurrentLine);
			}
			metrics.setNumberOfLines(lines);
			metrics.setNumberOfCodeLines(linesOfCode);
			metrics.setCommentLines(commentLines);
			metrics.setFileName(fileName);
			//System.out.println("Total Lines: " + lines);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return metrics;
	}
	
	/**
	 * Method that processes 
	 * @param files
	 * @return
	 */
	
	public List<File> processFiles(File[] files, String projectLanguage) {
		
		List<File> fileList = new ArrayList<File>();

		LanguageFileExt langFileExt = new LanguageFileExt();
		String fileExt = langFileExt.getLanguageExtension(projectLanguage);
		
    for (File file : files) {
    	
        if (file.isDirectory()) {
            //System.out.println("Directory: " + file.getName());
            processFiles(file.listFiles(), projectLanguage); // Calls same method again.
        } else {
            //System.out.println("File: " + file.getAbsolutePath());
            if(file.getName().endsWith(".class"))
            	continue;
            try {
            	FileMetrics metrics = buildFileMetrics(file.getAbsolutePath());
            	fileNames.add(file.getAbsolutePath());
            	allFilesList += metrics.getFileName() + "\n";
            	if(file.getName().endsWith(fileExt)) {
            		totalFiles++;
            		totalLinesOfCode += metrics.getNumberOfLines();
            		totalCommentLines += metrics.getCommentLines();
            	}
            }
            catch(IOException ex) {
            	ex.printStackTrace();
            }
        }
    };
    
    return fileList;
	}
}
