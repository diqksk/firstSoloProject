<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width,initial-scale=1"> -->
<title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
<link href="/css/admin/layout.css" type="text/css" rel="stylesheet" />
<style>
    
    #visual .content-container{	
        height:inherit;
        display:flex; 
        align-items: center;
        
        background: url("/images/mypage/visual.png") no-repeat center;
    }
</style>
</head>
<body>
<header style="background-image:url(/img/이카오니기리배경.jpg); cursor: pointer; " onclick="location.href='list'">
		<div style="text-align:center; height:200px; padding:60px 0 0 0;">
		</div>
	</header>
	<!-------------------------------------------aside----------------------------------------------->
			<aside class="aside" style="float:left; padding:10px; margin:20px 0 0 0">
				<div style="width:200px">
					<nav class="menu text-menu first margin-top">
						<h1 style="margin:0 0 0 65px;">목록</h1>
						<br /><br /><br />
						<ul>
							<li><a href="/board/list">추억</a></li>
							<li><a href="/gallery/list">그림게시판</a></li>
							<li><a href="/admin/login">관리자로그인</a></li>
						</ul>
					</nav>
				</div>
			</aside>
			<!-- --------------------------- main --------------------------------------- -->
		<main style="float:left; padding:10px; margin:20px 0 0 0">
		</main>
		<h2 class="main title">관리자홈</h2>
		
		<div class="breadcrumb">
			<h3 class="hidden">breadlet</h3>
			<ul>
				<li>home</li>
				<li>마이페이지</li>
				<li>홈</li>
			</ul>
		</div>
		
		<div class="margin-top first">
		
		</div>
		
	</main>
	<!-- ------------------- <footer> --------------------------------------- -->
	

        <footer id="footer">
            <div class="content-container">
                <h2 id="footer-logo"><img src="/images/logo-footer.png" alt="회사정보"></h2>
    
                <div id="company-info">
                    <dl>
                        <dt>주소:</dt>
                        <dd>서울특별시 </dd>
                        <dt>관리자메일:</dt>
                        <dd>admin@newlecture.com</dd>
                    </dl>
                    <dl>
                        <dt>사업자 등록번호:</dt>
                        <dd>111-11-11111</dd>
                        <dt>통신 판매업:</dt>
                        <dd>신고제 1111 호</dd>
                    </dl>
                    <dl>
                        <dt>상호:</dt>
                        <dd>뉴렉처</dd>
                        <dt>대표:</dt>
                        <dd>홍길동</dd>
                        <dt>전화번호:</dt>
                        <dd>111-1111-1111</dd>
                    </dl>
                    <div id="copyright" class="margin-top">Copyright ⓒ newlecture.com 2012-2014 All Right Reserved.
                        Contact admin@newlecture.com for more information</div>
                </div>
            </div>
        </footer>
</body>
</html>