package com.zx9cv.staris.vo;


public class memoVO {
	
private String title;
private String writer;
private String content;
private String datetime;
private int id;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDatetime() {
	return datetime;
}
public void setDatetime(String datetime) {
	this.datetime = datetime;
}
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
	return "memoVO [title=" + title + ", writer=" + writer + ", content=" + content + ", datetime=" + datetime + ", id="
			+ id + "]";
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