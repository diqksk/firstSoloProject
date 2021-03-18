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
	.jumbotron{
	padding:60px;
	}
	
</style>
<body style="min-width: 1640px;">
	<header
		style="background-image: url(/img/이카오니기리배경.jpg); cursor: pointer;"
		onclick="location.href='/board/list'">
		<div style="text-align: center; height: 200px; padding: 60px 0 0 0;">
		</div>
	</header>

	<!-------------------------------------------aside----------------------------------------------->
	<aside class="aside" style="float: left; padding: 10px 0 10px 10px; margin: 20px 0 0 0; height:100%;">
		<div style="width: 200px">
			<nav class="menu text-menu first margin-top">
				<br />
				<br />
				<c:if test="${login_id!= null }">
					<form action="login" method="POST">
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
					<li>
						<p style="font-size: 20px; margin-top: 30px;">
							<input type="hidden" name="access" value="logout" />
							<input type="submit" value="log out" />
						</p>
					</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</aside>
	<!-- --------------------------- main --------------------------------------- -->


	<main style="float: left; padding: 10px 10px 10px 0; margin: 20px 0 0 0">
		<div style="padding: 40px; margin: 20px; border: 1px solid #eee; min-width: 870px;"  >
			<c:if test="${login_id==null }">
				<div class="jumbotron">
					<form action="login" method="POST">
						<table>
							<tr>
								<th>관리자 ID</th>
								<td><input type="text" name="id" /></td>
							</tr>
							<tr>
								<th>비밀변호</th>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="hidden" name="access"
									value="login" /> <input type="submit" /></td>
							</tr>
						</table>
					</form>
				</div>
			</c:if>
		</div>
	</main>
</body>
</html>