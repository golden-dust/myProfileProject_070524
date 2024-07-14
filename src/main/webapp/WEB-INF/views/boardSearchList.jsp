<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/css/indexStyle.css" />
	<link rel="stylesheet" src="css/board.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<title>board</title>

</head>
<body>
	<%@ include file="./fix/header.jsp" %>
	
	<h4>board list page</h4>
	
	<div>
		<div class="d-flex justify-content-center">
			<div class="hstack gap-4">
				<div class="p-2">
					<span>Total : ${pageDto.total} &nbsp;|&nbsp; Page : ${currPage} / ${pageDto.lastPage}</span>
				</div>
				<form action="/board-search" class="d-flex" role="search">
					<div class="p-2 ms-auto">
						<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">선택</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">제목</a></li>
							<li><a class="dropdown-item" href="#">본문</a></li>
							<li><a class="dropdown-item" href="#">제목+본문</a></li>
							<li><a class="dropdown-item" href="#">작성자</a></li>
						</ul>
					</div>
					<div class="p-2">
						<input class="form-control me-2" type="search" name="key" placeholder="Search" value="${key}" aria-label="Search">
					</div>
					<div class="p-2">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</div>
				</form>
			</div>
		</div>
		<div>
			<ol class="list-group list-group-flush">
				<c:forEach items="${bDtos}" var="post" >
					<li class="list-group-item d-flex justify-content-between align-items-start">
						<div class="ms-2 me-auto">
							<div class="fw-bold"><span>${post.pnum}</span> <a href="/board-post${post.pnum}">${post.ptitle}</a></div>
							&emsp;&emsp;${post.mid} | ${post.pdate}
						</div>
						<c:if test="${post.numcomments > 0}">
							<span class="badge text-bg-primary rounded-pill">${post.numcomments}</span>
						</c:if>
					</li>
				</c:forEach>
			</ol>
		</div>
		<div>
			<div class="d-flex justify-content-end">
				<button type="button" class="btn btn-primary" onclick="location.href='/board-write'">글쓰기</button>
			</div>	
		</div>
		<div>
			<nav aria-label="page-navigation" class="d-flex justify-content-center">
				<ul class="pagination">
					<c:if test="${pageDto.prev}">
						<li class="page-item">
							<a href="/board-search?key=${key}&pageNum=1" class="page-link"><<</a>
						</li>
						<li class="page-item">
							<a href="/board-search?key=${key}&pageNum=${pageDto.startPage - 10}" class="page-link"><</a>
						</li>
					</c:if>
					<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="pageNumber">
						<c:choose>
							<c:when test="${currPage == pageNumber}">
								<li class="page-item active" aria-current="page">
									<span class="page-link">${pageNumber}</span>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="/board-search?key=${key}&pageNum=${pageNumber}">${pageNumber}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageDto.next}">
						<li class="page-item">
							<a class="page-link" href="/board-search?key=${key}&pageNum=${pageDto.startPage + 10}">></a>
						</li>
						<li class="page-item">
							<a class="page-link" href="/board-search?key=${key}&pageNum=${pageDto.lastPage}">>></a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>