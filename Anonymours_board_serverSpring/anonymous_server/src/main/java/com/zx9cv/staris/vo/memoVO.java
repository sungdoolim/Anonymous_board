package com.zx9cv.staris.vo;


public class memoVO {
	
private String title;
private String writer;
private String content;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getWriter() {
	return writer;
}
@Override
public String toString() {
	return "memoVO [title=" + title + ", writer=" + writer + ", content=" + content + "]";
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

	
}