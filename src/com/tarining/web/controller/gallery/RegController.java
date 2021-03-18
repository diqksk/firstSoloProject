package com.tarining.web.controller.gallery;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.taining.web.entity.BoardVo;
import com.taining.web.entity.GalleryVo;
import com.training.web.service.BoardService;
import com.training.web.service.GalleryService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
		) //filesizethreshold 크기 이상의 용량이면 disk 사용하도록함
@WebServlet("/gallery/reg")
public class RegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/gallery/galreg.jsp").forward(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		content=content.replace("\r\n", "<br>");
		String writer = request.getParameter("writer");
		String isOpen=request.getParameter("open");
		String password = request.getParameter("password");
		
		Collection<Part> parts = request.getParts(); //다중파일입력을 받기위한 콜렉션
		System.out.println("parts :>>>"+parts.size());
		StringBuilder builder = new StringBuilder();
		
		
		for(Part p : parts) { //파일 입출력
			if(!p.getName().equals("file")) continue;
			if(p.getSize() ==0) continue;
			Part filePart= p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis= filePart.getInputStream();
			
			
			String realPath=request.getServletContext().getRealPath("/img"); //업로드의 절대경로알려줌
			System.out.println(realPath);
			
			File path = new File(realPath); //실제 물리적인 경로가 존재한지 여부에 대해서 확인
			if(path.exists())
				path.mkdirs();
			
			
			String filePath = realPath + File.separator + fileName;  //file 이름과 경로설정
			FileOutputStream fos = new FileOutputStream(filePath); //output설정
			
			byte[] buf = new byte[1024]; //byte 버퍼 준비
			int size= 0; 
			while((size=fis.read(buf)) != -1) //다읽으면 정지
				fos.write(buf,0,size);
				
			fos.close();
			fis.close();
			
		}
		
		if(builder.length()!=0)
		builder.delete(builder.length()-1,builder.length());  //파일구분자 , 제거
		
		boolean pub = false;
		if(isOpen!=null)
			pub=true;
		
		
		GalleryVo vo = new GalleryVo(); 
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPub(pub);
		vo.setWriter(writer);
		vo.setImg(builder.toString());
		vo.setPassword(password);
		
		GalleryService service = new GalleryService();
		int result = service.insertGallery(vo);
		
		response.sendRedirect("list");
	}
}
