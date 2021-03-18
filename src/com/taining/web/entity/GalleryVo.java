package com.taining.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class GalleryVo {
	private int id;
	private String title;
	private String writer;
	private String img;
	private String content;
	private Date regdate;
	private int hit;
	private boolean pub;
	private String password;
	
	public Boolean getPub() {
		return this.pub;
	}
	public GalleryVo(int id, String title, String writer, String img, String content, Date regdate, int hit,
			boolean pub, String password) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.img = img;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.pub = pub;
		this.password = password;
	}
	public GalleryVo() {
	}

	

}
