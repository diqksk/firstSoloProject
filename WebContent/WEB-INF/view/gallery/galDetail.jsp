<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
ul {
	list-style: none;
}

.data:hover>td {
	background: #eee;
	cusor: pointer;
}

a:link {
	color: black;
}

a:visited {
	color: black;
}

#commentTable tr {
	background-color: #f8f8f8;
}

#commentWrite tr {
	background-color: #f8f8f8;
}

#commentTable td {
	padding: 10px 10px;
}

#commentWrite td {
	padding: 10px 10px;
}
img{
border:1px solid #e4e4e4;
min-width: 30%;
max-width: 70%;
}
</style>
<body style="min-width: 1640px;">
	<header
		style="background-image: url(/img/이카오니기리배경.jpg); cursor: pointer;"
		onclick="location.href='list'">
		<div style="text-align: center; height: 200px; padding: 60px 0 0 0;">
		</div>
	</header>

	<!-------------------------------------------aside----------------------------------------------->
	<aside class="aside"
		style="float: left; padding: 10px 0 10px 10px; margin: 20px 0 0 0">
		<div style="width: 200px">
			<nav>
				<br /> <br />
				<c:if test="${login_id!= null }">
					<form action="/admin/login" method="POST">
						<span style="margin-left: 30px;">'${login_id }'님 안녕하세요.</span><br />
						<span style="margin-left: 30px;">관리자 계정입니다.</span> <span
							style="margin-left: 30px;"> <input type="hidden"
							name="access" value="logout" /> <input type="submit"
							value="log out" style="border: 0" />
						</span>
					</form>
				</c:if>
				<ul>
					<li><p style="font-size: 20px; margin-top: 30px;">
							<a href="/board/list">추억</a>
						</p></li>
					<li><p style="font-size: 20px; margin-top: 30px;">
							<a href="list">그림게시판</a>
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
		<div style="padding: 40px; margin: 20px; border: 1px solid #eee; max-width: 1500px;" >
			<div>
				<table style="min-width: 1100px;">
					<tbody>
						<tr style="border-bottom: 1px solid #eee;">
							<td colspan="4" style="font-size: 20px; font-weight: bold; padding-bottom: 10px;">${n.title }</td>
						</tr>
						<tr>
							<td colspan="4"><span id="id"
								style="font-size: 13px; margin: 0 5px; font-weight: bold">${n.writer }</span>
								<span id="hit" style="font-size: 13px; margin: 0 5px;">조회
									<fmt:formatNumber type="number" pattern=",###"
										value="${n.hit }" />
							</span> <span id="date" style="font-size: 13px; margin: 0 5px;"><fmt:formatDate
										pattern="yy-MM-dd hh:mm:ss" value="${n.regdate}" /></span></td>
						</tr>
						<tr>
							<td colspan="4" style="padding: 30px 0 0 0">
								<img src="/img/${n.img }" />
								<br /><br />
								<p style="min-height: 100px;">
									${n.content } <input type="hidden" id="pagenum"
										value="${n.id }" />
								</p>
							</td>
						</tr>
<%-- 						<tr style="margin-bottom: 10px;">
							<th style="padding-left: 10px;">첨부파일</th>
							<td colspan="3" style="text-align: left; text-indent: 10px;">
								<c:forTokens var="imgName" items="${n.img }" delims="," varStatus="st">
									<c:set var="style" value="" />
									<a download href="/img/${imgName}" style="${style}">${fn:toUpperCase(imgName)}</a>
									<c:if test="${!st.last }">
										/
								</c:if>
								</c:forTokens>
							</td>
						</tr> --%>
					</tbody>
				</table>
			</div>
			<div style=" background-color:#fafafa; min-height: 50px; text-align: left; padding:20px; ">
				<a href="list" style="color:#FF5656; border:1px solid lightgray; padding:6px 14px;">목록</a>
			</div>
		</div>
	</main>
</body>
</html>