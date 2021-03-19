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


	<main
		style="float: left; padding: 10px 10px 10px 0; margin: 20px 0 0 0">
		<div style="padding: 40px; margin: 20px; border: 1px solid #eee">
			<h2 style="margin: 0">추억게시판...</h2>
			<h5 style="margin-bottom: 30px;">자유롭게 소통하는 게시판..</h5>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 60px; text-align: center;">번호</th>
							<th style="width: 900px; text-align: center;">제목</th>
							<th style="width: 130px; text-align: center;">작성자</th>
							<th style="width: 100px; text-align: center;">작성일</th>
							<th style="width: 60px; text-align: center;">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="n" items="${list}">
							<tr class="data">
								<td>${n.id }</td>
								<td><a
									href="detail?id=${n.id }&p=${(empty param.p)?1:param.p}">${n.title }</a>
									<span style="color: #FF5656">[${n.cmtCount }]</span></td>
								<td>${n.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${n.regdate }" /></td>
								<td>${n.hit }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<c:set var="page" value="${(empty param.p)?1:param.p}" />
			<c:set var="startNum" value="${page-(page-1)%5}" />
			<c:set var="lastNum"
				value="${fn:substringBefore(Math.ceil(count/10),'.')}" />


			<div style="float: right; padding: 0 15px 0 0;">
				<!--현재 페이지 -->
				<span style="color: orange">${(empty param.p)?1:param.p }</span> /
				${lastNum } pages
			</div>

			<div>
				<a href="reg"
					style="color: #FF5656; border: 1px solid lightgray; padding: 6px 14px;">글쓰기</a>
			</div>

			<nav aria-label="Page navigation" style="text-align: center;">
				<ul class="pagination">
					<li><c:if test="${startNum-1>1}">
							<a href="?p=${startNum-1}&t=&q=">Prev</a>
						</c:if> <c:if test="${startNum-1<=1}">
							<span aria-hidden="true" onclick="alert('이전 페이지가 없습니다.');">Prev</span>
						</c:if></li>

					<c:forEach begin="0" end="4" var="i">
						<c:if test="${(startNum+i) <= lastNum }">
							<li><a style="color:${(page==(startNum+i))?'#FF5656':''}"
								href="?p=${startNum+i}&f=${param.f }&q=${param.q}">${startNum+i}</a>
							</li>
						</c:if>
					</c:forEach>

					<li><c:if test="${startNum+4<lastNum}">

							<a href="?p=${startNum+5}&t=&q=">Next</a>
						</c:if> <c:if test="${startNum+4>=lastNum}">
							<span aria-hidden="true" onclick="alert('다음 페이지가 없습니다.');">Next</span>
						</c:if></li>
				</ul>
			</nav>
			<form>
				<div style="text-align: center; margin-top: 34px;">
					<div
						style="background-color: #fafafa; height: 75px; padding: 20px 0 20px 0;">
						<label>검색분류</label> <select name="f"
							style="border: 1px solid #e4e4e4;">
							<option ${(param.f=="title")?"selected":"" } value="title">제목</option>
							<option ${(param.f=="writer")?"selected":"" } value="writer">작성자</option>
						</select> <input type="text" name="q" value="${param.q }"
							style="border: 1px solid #e4e4e4;" /> <input type="submit"
							value="검색" />
					</div>
				</div>
			</form>
		</div>
	</main>
</body>
</html>