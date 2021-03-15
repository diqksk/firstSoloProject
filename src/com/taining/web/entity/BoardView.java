package com.taining.web.entity;

import java.util.Date;

public class BoardView extends BoardVo {
	private int cmtCount;
	public BoardView() {
		
	}
	public BoardView(int id, String title, String writer, String content, Date regdate, int hit, String files, boolean pub,
			int cmtCount) {
		super(id, title, writer, content, regdate, hit, files,pub);
		this.cmtCount=cmtCount;
	}
	public int getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

}
