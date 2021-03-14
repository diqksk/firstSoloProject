<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</style>
<body>
	<!-------------------------------------------header----------------------------------------------->
<header>
		<div class="content-container" style="align:center">
			<h1 id="logo">
				<a href="/index.html"> <img src="/images/logo.png" alt="뉴렉처 온라인" />

				</a>
			</h1>

			<section>
				<h1 class="hidden">헤더</h1>

				<nav id="main-menu" style="	display: inline-block;	">
					<h1>메인메뉴</h1>
					<ul>
						<li><a href="/guide">학습가이드</a></li>

						<li><a href="/course">강좌선택</a></li>
						<li><a href="/answeris/index">AnswerIs</a></li>
					</ul>
				</nav>

			</section>

		</div>

	</header>

	<!-------------------------------------------aside----------------------------------------------->
	<div id="body">
		<div class="content-container clearfix">
			<aside class="aside" style="float:left">
				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current" href="/customer/notice">공지사항</a></li>
						<li><a class="" href="/customer/faq">자유게시판</a></li>
						<li><a class="" href="/customer/question">자유게시판2</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>

					</ul>
				</nav>



			</aside>
			<!-------------------------------------------main----------------------------------------------->


			<main class="main" style="float:left">
				<div>
					<table class="table">
						<thead>
							<tr>
								<th style="width: 60px;">번호</th>
								<th style="width: 800px;">제목</th>
								<th style="width: 100px;">작성자</th>
								<th style="width: 100px;">작성일</th>
								<th style="width: 60px;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="n" items="${list}">
								<tr>
									<td>${n.id }</td>
									<td class="title indent text-align-left"><a
										href="detail?id=${n.id }">${n.title }</a><span style="color:orange">[${n.cmtCount }]</span></td>
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


				<div style="float:right">
					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span style="color:orange">${(empty param.p)?1:param.p }</span>
						/ ${lastNum } pages
					</div>
				</div>

				<nav aria-label="Page navigation" style="textalign:center;">
					<ul class="pagination" >
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
		</div>
	</div>
</body>
</html>