<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/css/indexStyle.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<title>join</title>
</head>
<body>
	<%@ include file="./fix/header.jsp" %>
	
	<h4>회원 가입 페이지</h4>
	
	<p>${errorMsg}</p>
	
	<form method="post">
		<div class="mb-3">
			 <label for="mid" class="form-label">ID</label>
			 <input type="text" class="form-control" id="mid" name="mid">
		</div>
		<div class="mb-3">
			 <label for="mpw" class="form-label">Password</label>
			 <input type="password" class="form-control" id="mpw" name="mpw">
		</div>
		<div class="mb-3">
			 <label for="mname" class="form-label">Name</label>
			 <input type="text" class="form-control" id="mname" name="mname">
		</div>
		<div class="mb-3">
			 <label for="memail" class="form-label">Email address</label>
			 <input type="email" class="form-control" id="memail" name="memail">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>