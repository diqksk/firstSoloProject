<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>IKA and ONIGIRI</title>
</head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
ul{
list-style:none;
}
.data:hover>td{
	background:#eee;
	cusor:pointer;
}
a:link{
	color:black;
}
a:visited{
	color:black;
}

#commentTable tr {
background-color: #f8f8f8;

}
#commentWrite tr {
background-color: #f8f8f8;

}
#commentTable td{
padding:10px 10px;
}
#commentWrite td{
padding:10px 10px;
}
</style>
<body style="min-width: 1640px;">
	<header style="background-image: url(/img/이카오니기리배경.jpg); cursor: pointer;" onclick="location.href='list'">
		<div style="text-align: center; height: 200px; padding: 60px 0 0 0;">
		</div>
	</header>

	<!-------------------------------------------aside----------------------------------------------->
	<aside class="aside" style="float: left; padding: 10px 0 10px 10px; margin: 20px 0 0 0">
		<div style="width: 200px">
		<nav>
				<br />
				<br />
				<c:if test="${login_id!= null }">
					<form action="/admin/login" method="POST">
					<span style="margin-left:30px;">'${login_id }'님 안녕하세요.</span><br />
					<span style="margin-left:30px;">관리자 계정입니다.</span>
					<span style="margin-left:30px;">
						<input type="hidden" name="access" value="logout" />
						<input type="submit" value="log out" style="border:0"/>
					</span>
					</form>
				</c:if>
					<ul>
					<li><p style="font-size: 20px; margin-top: 30px;">
							<a href="/board/list">추억</a>
						</p></li>
					<li><p style="font-size: 20px; margin-top: 30px;">
							<a href="/gallery/list">그림게시판</a>
						</p></li>
					<c:if test="${login_id== null }">
						<li>
							<p style="font-size: 20px; margin-top: 30px;">
								<a href="/admin/login">관리자로그인</a>
							</p>
						</li>
					</c:if>
					<c:if test="${login_id != null }">
					<li>
						<p style="font-size: 20px; margin-top: 30px;">
							<a href="/admin/board/list">관리자 게시판</a>
						</p>
					</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</aside>
	<!-- --------------------------- main --------------------------------------- -->


	<main style="float: left; padding: 10px 10px 10px 0; margin: 20px 0 0 0">
		<div style="padding: 40px; margin: 20px; border: 1px solid #eee">
			<div>
				<table style="min-width: 870px;">
					<tbody>
						<tr style="border-bottom: 1px solid #eee;">
							<!-- <th style="width:">제목</th> -->
							<td colspan="4"
								style="font-size: 20px; font-weight: bold; padding-bottom: 10px;">${n.title }</td>
						</tr>
						<tr>
							<!-- <th>작성일</th> -->
							<td colspan="4"><span id="id"
								style="font-size: 13px; margin: 0 5px; font-weight: bold">${n.writer }</span>
								<span id="hit" style="font-size: 13px; margin: 0 5px;">조회
									<fmt:formatNumber type="number" pattern=",###"
										value="${n.hit }" />
							</span> <span id="date" style="font-size: 13px; margin: 0 5px;"><fmt:formatDate
										pattern="yy-MM-dd hh:mm:ss" value="${n.regdate}" /></span></td>
						</tr>
						<%-- 				<tr>
							<th>작성자</th>
							<td>${n.writer }</td>
							<th>조회수</th>
							<td><fmt:formatNumber type="number" pattern=",###"
									value="${n.hit }" /></td>
						</tr> --%>
						<tr>
							<td colspan="4" style="padding: 30px 0 60px 0">
								<p style="min-height: 200px;">
									${n.content } <input type="hidden" id="pagenum"
										value="${n.id }" />
								</p>
							</td>
						</tr>
						<tr style="margin-bottom: 10px;">
							<th style="padding-left: 10px;">첨부파일</th>
							<td colspan="3" style="text-align: left; text-indent: 10px;">
								<c:forTokens var="fileName" items="${n.files }" delims=","
									varStatus="st">
									<c:set var="style" value="" />
									<c:if test="${fn:endsWith(fileName,'.zip') }">
										<c:set var="style" value="font-weight: bold; color:red;" />
									</c:if>
									<a download href="/upload/${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
									<c:if test="${!st.last }">
										/
								</c:if>
								</c:forTokens>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div>
				<a class="btn btn-list" href="list">목록</a> <a class="btn btn-list"
					href="update?id=${n.id }&p=${p}">수정</a> <a class="btn btn-list"
					href="delete?id=${n.id }&p=${p}">삭제</a>

			</div>

			<div class="margin-top">
				<table class="table border-top-default">
					<tbody>

						<tr>
							<th style="width: 10%">다음글</th>
							<td colspan="3" style="text-align: left"><a
								href="detail?id=${nn.id }&p=${p}">${nn.title }</a></td>
						</tr>
						<tr>
							<th style="width: 10%">이전글</th>
							<td colspan="3" style="text-align: left"><a
								href="detail?id=${pn.id }&p=${p}">${pn.title }</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="container"
				style="text-align: left; margin: 0; padding: 0;">
				<form>
					<table style="text-align: left; min-width: 1170px;">
						<thead>
							<tr>
								<th style="width: 100px;"></th>
								<th></th>
								<th style="width: 200px;"></th>
							</tr>
						</thead>
						<tbody id="commentTable">
							<!-----------댓글이 들어오는 부분-------------->
						</tbody>
						<tbody id="commentWrite">
							<tr>
								<td><input type="text" name="writer" id="writer" value="익명" style="width: 100px; border:1px solid #efefef " /></td>
								 <td colspan="2"><input type="text" name="content" id="content" placeholder="댓글 작성." style="width: 100%; border:1px solid #efefef" /></td>
							</tr>
							<tr>
								<td colspan="2"></td>
								<td style="text-align: right;"><button type="button" onclick="writerFunction(); return false;" style="background-color: orange; color: white; border: 1px solid white">작성</button></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</main>
</body>
<script defer type="text/javascript" src="/js/guestDetail.js">
</script>
</html>