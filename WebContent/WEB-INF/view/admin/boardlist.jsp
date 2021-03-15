<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<title>admin list page</title>
<meta charset="UTF-8">
<title>공지사항목록</title>

<link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/customer/visual.png") no-repeat center;
}
</style>
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
<body>

			<!-- --------------------------- aside --------------------------------------- -->
			<!-- aside 부분 -->


			<aside class="aside" style="float: left">
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current" href="/customer/notice">공지사항</a></li>
						<li><a class="" href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>

					</ul>
				</nav>



			</aside>
			<!-- --------------------------- main --------------------------------------- -->



			<main class="main" style="float: left">
				<form action="list" method="POST">
					<div class="notice margin-top">
						<table class="table">
							<thead>
								<tr>
									<th style="width: 60px;">번호</th>
									<th style="width: 800px;">제목</th>
									<th style="width: 100px;">작성자</th>
									<th style="width: 100px;">작성일</th>
									<th style="width: 60px;">조회수</th>
									<th style="width: 60px;">공개</th>
									<th style="width: 60px;">삭제</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="n" items="${list}">
								<c:set var="open" value=""/>
										<c:if test="${n.pub }">
											<c:set var="open" value="checked"/>
										</c:if>
									<tr>
										<td>${n.id }</td>
										<td class="title indent text-align-left"><a
											href="detail?id=${n.id }">${n.title }</a><span
											style="color: orange">[${n.cmtCount }]</span></td>
										<td>${n.writer }</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${n.regdate }" /></td>
										<td>${n.hit }</td>
										<td><input type="checkbox" name="open-id" ${open } value="${n.id }" /></td>
										<td><input type="checkbox" name="del-id" value="${n.id }" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<c:set var="page" value="${(empty param.p)?1:param.p}" />
					<c:set var="startNum" value="${page-(page-1)%5}" />
					<c:set var="lastNum"
						value="${fn:substringBefore(Math.ceil(count/10),'.')}" />

					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span style="color: orange">${(empty param.p)?1:param.p }</span> /
						${lastNum } pages
					</div>

					<div>
					
						<c:set var="ids" value=""/>
						<c:forEach var="n" items="${list }">
						<c:set var="ids" value="${ids } ${n.id }"/>
						</c:forEach>
						<input type="hidden" name="ids" value="${ids }"/>
						<input type="submit" name="cmd" value="일괄공개" />
						<input type="submit" name="cmd" value="일괄삭제" />
						<a href="reg">글쓰기</a>
					</div>
				</form>
				<nav aria-label="Page navigation" style="textalign: center;">
					<ul class="pagination">
						<li><c:if test="${startNum-1>1}">
								<a href="?p=${startNum-1}&t=&q=">Prev</a>
							</c:if> <c:if test="${startNum-1<=1}">
								<span aria-hidden="true" onclick="alert('이전 페이지가 없습니다.');">Prev</span>
							</c:if></li>

						<c:forEach begin="0" end="4" var="i">
							<c:if test="${(startNum+i) <= lastNum }">
								<li><a style="color:${(page==(startNum+i))?'orange':''}"
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
		<div>
			<form>
				<fieldset>
					<label>검색분류</label> <select name="f">
						<option ${(param.f=="title")?"selected":"" } value="title">제목</option>
						<option ${(param.f=="writer")?"selected":"" } value="writer">작성자</option>
					</select> <label class="hidden">검색어</label> <input type="text" name="q"
						value="${param.q }" /> <input class="btn btn-search"
						type="submit" value="검색" />
				</fieldset>
			</form>
		</div>
	</main>

</body>

</html>