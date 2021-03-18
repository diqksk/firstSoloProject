package com.taining.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVo {
	private int id;
	private String cwriter;
	private String ccontent;
	private Date cregdate;
	private int boardID;
}
