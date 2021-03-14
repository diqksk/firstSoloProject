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
<body>
	<!-------------------------------------------header----------------------------------------------->
	<header>
		<div class="content-container" style="align: center">
			<h1 id="logo">
				<a href="/index.html"> <img src="/images/logo.png" alt="뉴렉처 온라인" />

				</a>
			</h1>

			<section>
				<h1 class="hidden">헤더</h1>

				<nav id="main-menu" style="display: inline-block;">
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
			<aside class="aside" style="float: left">
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
			<main style="float:left">
				<div class="margin-top first">
					<table class="table">
						<tbody>
							<tr>
								<th>제목</th>
								<td class="text-align-left text-indent text-strong text-orange"
									colspan="3">${n.title }</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td class="text-align-left text-indent" colspan="3"><fmt:formatDate
										pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate }" /></td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${n.writer }</td>
								<th>조회수</th>
								<td><fmt:formatNumber type="number" pattern="####,####"
										value="${n.hit }" /></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3" style="text-align: left; text-indent: 10px;">
									<c:forTokens var="fileName" items="${n.files }" delims=","
										varStatus="st">
										<c:set var="style" value="" />
										<c:if test="${fn:endsWith(fileName,'.zip') }">
											<c:set var="style"
												value="f
											ont-weight: bold; color:red;" />
										</c:if>
										<a href="${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
										<c:if test="${!st.last }">
										/
										</c:if>
									</c:forTokens>
								</td>
							</tr>
							<tr class="content">
								<td colspan="4">${n.content }</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="margin-top text-align-center">
					<a class="btn btn-list" href="list">목록</a>
				</div>

				<div class="margin-top">
					<table class="table border-top-default">
						<tbody>

							<tr>
								<th>다음글</th>
								<td colspan="3" class="text-align-left text-indent"><a
									href="detail?id=${nn.id }">${nn.title }</a></td>
							</tr>
							<tr>
								<th>이전글</th>
								<td colspan="3" class="text-align-left text-indent"><a
									class="text-blue text-strong" href="detail?id=${pn.id }">${pn.title }</a></td>
							</tr>


						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>

</body>
</html>