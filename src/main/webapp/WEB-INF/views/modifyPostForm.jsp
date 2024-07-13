<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/css/indexStyle.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<title>write</title>
</head>
<body>
	<%@ include file="./fix/header.jsp" %>
	
	<h4>write post page</h4>
	
	<div>
		<form method="post">
			<div class="row mb-3">

				<div class="form-floating mb-3">
					<input type="text" name="ptitle" id="floatingPtitle" class="form-control" value="${post.ptitle}">
					<label for="floatingPtitle">제목</label>
				</div>
			</div>
			<div class="mb-3">
				<textarea class="form-control" id="postTextarea" name="pcontent" style="height: 300px">${post.pcontent}</textarea>
			</div>
			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-primary">수정</button>
			</div>
		</form>
	</div>
	
	<%@ include file="./fix/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
<script type="text/javascript">
	//$('#postTextarea').on("keypress", function(e) {
		//var key = e.keyCode;
		
		// if the user has pressed enter
		//if (key == 13) {
			// document.getElementById("postTextarea").value = document.getElementById("postTextarea").value + "\n";
			// 오라클은 \n을 데이터베이스에 넣을 때 지워버리나 봄 -> <br>로 넣어줬더니 개행됨
			//document.getElementById("postTextarea").value = document.getElementById("postTextarea").value + "<br>";
			//return false;
		//}
		//else {
			return true;
		//}
	//});		
</script>
</html>