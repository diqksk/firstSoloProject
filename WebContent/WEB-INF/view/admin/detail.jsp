<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
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
  
    <!-- --------------------------- <body> --------------------------------------- -->
    <div id="body">
        <div class="content-container clearfix">

            <!-- --------------------------- aside --------------------------------------- -->
            <!-- aside 부분 -->


            <aside class="aside" style="float: left">
                <h1>ADMIN PAGE</h1>

                <nav class="menu text-menu first margin-top">
                    <h1>마이페이지</h1>
                    <ul>
                        <li><a href="/admin/index.html">관리자홈</a></li>
                        <li><a href="/teacher/index.html">선생님페이지</a></li>
                        <li><a href="/student/index.html">수강생페이지</a></li>
                    </ul>
                </nav>

                <nav class="menu text-menu">
                    <h1>알림관리</h1>
                    <ul>
                        <li><a href="/admin/board/notice/list.html">공지사항</a></li>
                    </ul>
                </nav>

            </aside>
            <!-- --------------------------- main --------------------------------------- -->




            <main style="float: left">
                <div class="margin-top first">
                    <h3 class="hidden">공지사항 내용</h3>
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
								<td><fmt:formatNumber type="number" pattern=",###"
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
										<a download href="/upload/${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
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
                    <a class="btn-text btn-cancel" href="list">목록</a>
                    <a class="btn-text btn-default" href="edit">수정</a>
                    <a class="btn-text btn-default" href="del">삭제</a>
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
									class="text-blue text-strong" href="detail?id=${pn.id }">${pn.title }</a></td>                            </tr>
                        </tbody>
                    </table>
                </div>
            </main>

        </div>
    </div>

</body>

</html>