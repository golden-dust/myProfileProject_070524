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
		<nav class="navbar bg-body-tertiary">
			<div class="d-flex mb-2">
				<div class="me-auto p-2">
					<span>${pageDto.total}</span>
				</div>
				<div class="p-2">
					<form action="/board-search" class="d-flex" role="search">
						<input class="form-control me-2" type="search" name="key" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
		<div>
			<ol>
				<c:forEach items="${bDtos}" var="post" >
					<li class="list-group-item d-flex justify-content-between align-items-start">
						<div class="ms-2 me-auto">
							<div class="fw-bold"><span>${post.pnum}</span> <a href="/board-post${post.pnum}">${post.ptitle}</a></div>
							&emsp;&emsp;${post.mid} | ${post.pdate}
						</div>
						<span class="badge text-bg-primary rounded-pill"></span>
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
			<div class="d-flex justify-content-center">
				<c:if test="${pageDto.prev}">
					<a href="/board-search?key=${key}&pageNum=1" class="pagelink"><<</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/board-search?key=${key}&pageNum=${pageDto.startPage - 10}" class="pagelink"><</a>
				</c:if>
				&nbsp;&nbsp;
				<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="pageNumber">
					<c:choose>
						<c:when test="${currPage == pageNumber}">
							&nbsp;<span class="pagelink-curr">${pageNumber}</span>&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a class="pagelink" href="/board-search?key=${key}&pageNum=${pageNumber}">${pageNumber}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				&nbsp;&nbsp;
				<c:if test="${pageDto.next}">
					<a class="pagelink" href="/board-search?key=${key}&pageNum=${pageDto.startPage + 10}">></a>
					&nbsp;&nbsp;&nbsp;
					<a class="pagelink" href="/board-search?key=${key}&pageNum=${pageDto.lastPage}">>></a>
				</c:if>
			</div>
		</div>
	</div>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>