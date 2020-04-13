package co.finecode.metrics;

public class FileMetrics {
	private String fileName;
	private Integer numberOfLines;
	private Integer commentLines;
	private Integer numberOfJavaLines;
	private Integer whiteSpaceLines;
	
	// Getters and Setters
	public String getFileName() {
    return fileName;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public Integer getNumberOfLines() {
    return numberOfLines;
  }
  
  public void setNumberOfLines(Integer numberOfLines) {
    this.numberOfLines = numberOfLines;
  }
  
  public Integer getNumberJavaOfLines() {
	    return numberOfJavaLines;
  }
	  
  public void setNumberOfJavaLines(Integer numberOfJavaLines) {
    this.numberOfJavaLines = numberOfJavaLines;
  }
	
  public Integer getCommentLines() {
    return commentLines;
  }
  
  public void setCommentLines(Integer commentLines) {
    this.commentLines = commentLines;
  }
  
  public Integer getWhiteSpaceLines() {
    return whiteSpaceLines;
  }
  
  public void setWhiteSpaceLines(Integer whiteSpaceLines) {
    this.whiteSpaceLines = whiteSpaceLines;
  }
}
