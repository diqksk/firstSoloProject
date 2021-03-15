<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>

    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
        #visual .content-container {
            height: inherit;
            display: flex;
            align-items: center;

            background: url("/images/mypage/visual.png") no-repeat center;
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


<header>
		<div class="content-container" style="align:center">
			<h1 id="logo">
				<a href="/index.html"> <img src="/images/logo.png" alt="오징어 오니기리의 페이지" />

				</a>
			</h1>

			<section>
				<h1 class="hidden">헤더</h1>

				<nav id="main-menu" style="	display: inline-block;	">
					<h1>메인메뉴</h1>
					<ul>
						<li><a href="/guide">사야랑나랑추억</a></li>

						<li><a href="/course">하고싶은말</a></li>
						<li><a href="/answeris/index">그림그린거올리기</a></li>
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
					<h1>고객센터</h1>
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
              
                <form method="POST" action="reg" enctype="multipart/form-data">
                    <div class="margin-top first">
                        <h3 class="hidden">공지사항 입력</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="title" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>첨부파일</th>
                                    <td colspan="3"><input type="file" name="file" /> </td>
                                </tr>
                                <tr>
                                    <th>첨부파일</th>
                                    <td colspan="3" ><input type="file" name="file" /> </td>
                                </tr>
                                <tr class="content">
                                    <td colspan="4"><textarea class="content" name="content"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-align-right">
                                    <input class="vertical-align" type="checkbox" id="open" name="open" value="true">
                                    <label for="open" class="margin-left">바로공개</label> </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="등록" />
                        <a class="btn-text btn-cancel" href="list.html">취소</a>
                    </div>
                </form>

            </main>
        </div>
    </div>

</body>

</html>