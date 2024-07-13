<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/css/indexStyle.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<title>HOME</title>
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
				
				<!-- 수정 삭제 목록 버튼 -->
				<!-- <div class="row d-md-flex justify-content-end">
					<button class="btn btn-primary" type="button">Button</button>
  					<button class="btn btn-primary" type="button">Button</button>
				</div> -->
				
			</div>
			
			<div class="container text-center">
				<form>
				<input type="hidden" name="pnum" value="${post.pnum}">
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<button class="btn btn-light btn-sm" type="button" onclick="location.href='/board'"><small>목록</small></button> 
					</div>
					
					<div class="p-2">
						<button class="btn btn-light btn-sm" type="submit" formaction="/delete-post"><small>삭제</small></button> 
					</div>
					<div class="p-2">
						<button class="btn btn-light btn-sm" type="submit" formaction="/modify-post"><small>수정</small></button> 
					</div>
				</div>
				</form>
			</div>
			
			<!-- 댓글 입력 -->
			<div class="container text-start">
				<form>
					<div class="d-flex align-items-center">
						<div class="p-2 flex-grow-1">
							<textarea name="ctext" class="form-control" style="font-size: 0.8em;"></textarea>
						</div>
						<div>
							<button class="btn btn-light">작성</button>
						</div>
					</div>
				</form>
			</div>
			
			<!-- 댓글 리스트 -->
			<div class="container text-start">
				
				<div class="list-group list-group-flush">
					<!-- 각 댓글 -->
					<div class="list-group-item">
						<div class="d-flex w-100 justify-content-between">
							<span class="mb-1 text-break">comment text</span>
							<small class="fs-8 fw-light">hour/day ago</small>
						</div>
						<small class="fs-8 fw-light">writer &middot; replydate</small>
					</div>
					<!-- 각 댓글 -->
					<div class="list-group-item">
						<div class="d-flex w-100 justify-content-between">
							<span class="mb-1 text-break">comment text blanbalksdjflakjsdfl;kajsdl;fkjasl;dkfj;asdfasdfalksdfj;lakdjsfl;akjsd;flkasjdfls;dkjfa;lksdjf;lk</span>
							<small class="fs-8 fw-light">hour/day ago</small>
						</div>
						<small class="fs-8 fw-light">writer &middot; replydate</small>
					</div>
				</div>
				
			</div>
	
	</div>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>