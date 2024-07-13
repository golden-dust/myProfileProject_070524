<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/css/indexStyle.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<title>HOME</title>
	
	<style>
		button {
			-webkit-appearance: none;
  			-moz-appearance: none;
  			appearance: none;
  			
  			margin: 0;
  			padding: 0;
  			
  			display: inline-block;
  			width: auto;
  			
  			background: none;
  			
  			border: none;
  			border-radius: 4px;
		}
	
		.mini-btn-cmt {
  			padding: 0 2px 0 2px;
		}
	</style>
	
</head>
<body>
	<%@ include file="./fix/header.jsp" %>
	<br><br>
	<div id="wrapper">
		
		<!-- 게시글 컨테이너 -->
		<div class="container text-start">

			<!-- 본문 -->
			<div class="row justify-content-start">
				<div class="col-9">
					<p class="fs-3 fw-semibold">${post.ptitle}</p>
				</div>
			</div>
			
			<div class="row justify-content-start">
				<div class="col-9">
					<p class="fs-8 fw-light">${post.mid} &middot; ${post.pdate}</p>
				</div>
			</div>
			
			<div class="row justify-content-start">
				<div class="col-9">
					<p class="fs-6 fw-medium">${post.pcontent}</p>
				</div>
			</div>

		</div>
		
		<!-- 수정 삭제 목록 버튼 -->
		<div class="container text-center">
			<form>
			<input type="hidden" name="pnum" value="${post.pnum}">
			<div class="d-flex flex-row-reverse">
				<div class="p-2">
					<button class="btn btn-light btn-sm" type="button" onclick="location.href='/board'"><small>목록</small></button> 
				</div>
				<c:if test="${mid eq post.mid}">
				<div class="p-2">
					<button class="btn btn-light btn-sm" type="submit" formaction="/delete-post">
						<small>삭제</small>
					</button> 
				</div>
				<div class="p-2">
					<button class="btn btn-light btn-sm" type="submit" formaction="/modify-post">
						<small>수정</small>
					</button> 
				</div>
				</c:if>
			</div>
			</form>
		</div>
		
		<!-- 댓글 입력 -->
		<div class="container text-start">
			<form method="post">
				<div class="d-flex align-items-center">
					<div class="p-2 flex-grow-1">
						<textarea name="ctext" class="form-control" style="font-size: 0.8em;"></textarea>
					</div>
					<div>
						<button class="btn btn-light"><span>작성</span></button>
					</div>
				</div>
			</form>
		</div>
		<!-- 댓글 리스트 -->
		<div class="container text-start">
			
			<div class="list-group list-group-flush">
				<!-- 각 댓글 -->
				<c:forEach items="${cmDtos}" var="cmDto">
					<div class="list-group-item">
						<div class="d-flex w-100 justify-content-between">
							<span class="mb-1 text-break">${cmDto.ctext}</span>
							
							<!-- 본인 댓글 일때만 수정/삭제 버튼 보이도록 -->
							<c:if test="${mid eq cmDto.mid}">
							<div>
								<button type="button" class="mini-btn-cmt" data-bs-toggle="collapse" data-bs-target="#collapseForm-${cmDto.cnum}" aria-expanded="false" aria-controls="collapseForm-${cmDto.cnum}">
									<a>
										<small class="fw-light" style="font-size: 9pt;">수정</small>
									</a>
								</button>
								<small class="fw-light" style="font-size: 9pt;"> | </small>
								<button type="button" class="mini-btn-cmt">
									<a href="/delete-comment?cnum=${cmDto.cnum}">
										<small class="fw-light" style="font-size: 9pt;">삭제</small>
									</a>
								</button>
								
							</div>
							
							</c:if>
							
						</div>
						
						<!-- 댓글 수정 폼 -->
						<small class="fs-8 fw-light">${cmDto.mid} &middot; ${cmDto.cdate}</small>
						<div class="collapse multi-collapse" id="collapseForm-${cmDto.cnum}">
							<form action="/modify-comment" method="post">
								<input type="hidden" name="cnum" value="${cmDto.cnum}">
								<input type="hidden" name="pnum" value="${cmDto.pnum}">
								
								<div class="input-group mb-3">
									<!-- 치환 변수 선언 -->
									<%
										pageContext.setAttribute("cn", "\n");
										pageContext.setAttribute("br", "<br>");
									%>
									<textarea class="form-control" name="ctext" style="height: 100px;">${fn:replace(cmDto.ctext, br, cn)}</textarea>
									<button class="btn btn-outline-secondary" type="submit" id="button-addon2">수정</button>			
									</div>
							</form>
						</div>
					</div>
				</c:forEach>
				
			</div>
			
		</div>

	</div>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

</body>
</html>