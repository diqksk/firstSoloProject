package com.taining.web.entity;


import java.util.Date;

import lombok.Data;

@Data
public class BoardVo {
	private int id;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hit;
	private String files;
	private boolean pub;
	
	public BoardVo() {
	}
	
	public BoardVo(int id, String title, String writer, String content, Date regdate, int hit, String files,
			boolean pub) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
		this.pub = pub;
	}
	
	public Boolean getPub() {
		return this.pub;
	}

	
	
}
