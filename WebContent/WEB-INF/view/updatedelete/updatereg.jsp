<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<title>IKA and ONIGIRI</title>
    <meta charset="UTF-8">
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
				<c:if test="${login_id!= null }  ">
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
                <form method="POST" action="updatereg" enctype="multipart/form-data">
                    <div>
                        <table style = "margin:50px;">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td colspan="3">
                                        <input type="text" name="title" value="${n.title }"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td colspan="3">
                                        <input type="text" name="writer" value="익명" />
                                    </td>
                                </tr>
                                <tr class="content">
                                <td></td>
                                    <td colspan="3">
                                    <textarea class="content" name="content">${n.content }</textarea>
                                 	  <input type="hidden" name="id" value="${id }" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="등록" />
                        <a class="btn-text btn-cancel" href="list">취소</a>
                    </div>
                </form>
              </div>
            </main>
</body>

</html>