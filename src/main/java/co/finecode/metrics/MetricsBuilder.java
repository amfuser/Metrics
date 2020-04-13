package co.finecode.metrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetricsBuilder {
	
	private String allFilesList;
	private Integer totalLinesOfJava = 0;
	private Integer totalJavaFiles = 0;
	private Integer totalCommentLines = 0;
	
	private ArrayList<String> fileNames = new ArrayList<String>();
	
	/* Getters and Setters */
	
	public Integer getTotalLinesOfJava() {
		return this.totalLinesOfJava;
	}
	
	public void setTotalLinesOfJava(Integer totalLinesOfJava) {
		this.totalLinesOfJava = totalLinesOfJava;
	}
	
	public Integer getTotalCommentLines() {
		return this.totalCommentLines;
	}
	
	public void setTotalCommentLines(Integer totalCommentLines) {
		this.totalCommentLines = totalCommentLines;
	}
	
	public Integer getTotalJavaFiles() {
		return this.totalJavaFiles;
	}
	
	public void setTotalJavaFiles(Integer totalJavaFiles) {
		this.totalJavaFiles = totalJavaFiles;
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
		File newFile = new File(fileOrDirectory);
		if(newFile.isDirectory()) {
			processFiles(newFile.listFiles());
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
			Integer linesOfJava = 0;
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
					linesOfJava++;
				}
				if(line.trim().endsWith("*/")) {
					inCommentBlock = false;
				}
				lines++;
				//System.out.println(sCurrentLine);
			}
			metrics.setNumberOfLines(lines);
			metrics.setNumberOfJavaLines(linesOfJava);
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
	
	public List<File> processFiles(File[] files) {
		
		List<File> fileList = new ArrayList<File>();
		
    for (File file : files) {
    	
        if (file.isDirectory()) {
            //System.out.println("Directory: " + file.getName());
            processFiles(file.listFiles()); // Calls same method again.
        } else {
            //System.out.println("File: " + file.getAbsolutePath());
            if(file.getName().endsWith(".class"))
            	continue;
            try {
            	FileMetrics metrics = buildFileMetrics(file.getAbsolutePath());
            	fileNames.add(file.getAbsolutePath());
            	allFilesList += metrics.getFileName() + "\n";
            	if(file.getName().endsWith(".java")) {
            		totalJavaFiles++;
            		totalLinesOfJava += metrics.getNumberOfLines();
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
