<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="context-path" content="${pageContext.request.contextPath}">
<title>Insert title here</title>
<link href="<c:url value="resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/css/login.css" />" rel="stylesheet">
<!-- fontawesome -->
<link href="<c:url value="resources/fontawesome/css/all.min.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css" />
<style type="text/css">
.jumbotron {
	background-image: url('<c:url value="/resources/images/banner.jpg" />');
	background-size: cover;
	background-position: center;
	color: rgb(0, 0, 0);
	text-shadow: 1px 1px 6px #747474;
	height: 550px;
}
</style>
</head>
<body>
	<!-- Tab bars -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Quản Lý Hệ Thống</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="#">Trang Chủ</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Quản Lý
						Trẻ</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Quản Lý
						Nhân Viên</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Thống Kê</a>
				</li>
				<li class="nav-item">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Đăng nhập</button>
				</li>
			</ul>
		</div>
	</nav>
	<!-- Banner -->
	<div class="jumbotron text-center">
		<h1 class="display-4" style="font-weight: bold;">Quản Lý Hệ Thống Nhà Trẻ</h1>
		<p class="lead">Chúng tôi cam kết mang đến môi trường học tập và phát triển toàn diện cho trẻ em.</p>
		<p>Vui lòng đăng nhập vào hệ thống</p>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Đăng nhập</button>
	</div>
	<div class="container mt-5">
		<h2 class="text-center">Tính Năng Chính</h2>
		<div class="row text-center mt-4">
			<div class="col-md-4">
				<div class="feature-icon mb-3">
					<i class="fas fa-users"></i>
				</div>
				<h3>Quản Lý Trẻ</h3>
				<p>Quản lý thông tin và hoạt động của trẻ trong hệ thống.</p>
			</div>
			<div class="col-md-4">
				<div class="feature-icon mb-3">
					<i class="fas fa-users-cog"></i>
				</div>
				<h3>Quản Lý Nhân Viên</h3>
				<p>Quản lý thông tin và chức năng của nhân viên trong hệ thống.</p>
			</div>
			<div class="col-md-4">
				<div class="feature-icon mb-3">
					<i class="fas fa-chart-line"></i>
				</div>
				<h3>Thống Kê</h3>
				<p>Phân tích và biểu đồ hóa dữ liệu để hiểu rõ hơn về hoạt động
					của hệ thống.</p>
			</div>
		</div>
	</div>
	<!-- Modal đăng nhập -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="border-table">
					<h5 class="title-table" style="font-weight: bold;">Đăng nhập hệ thống</h5>
				<div class="modal-body">
					<form:form modelAttribute="loginForm" action="${pageContext.request.contextPath}/login" id="formLogin" method="post">
						<div class="row">
							<div class="col-4 " style="text-align: right;">
								<div class="row">
									<div class="col" >
										<label for="" class="mt-2 mb-4" style="font-weight: bold;">Tên đăng nhập</label>
									</div>
								</div>
								<div class="row">
									<div class="col" style="margin-right: 36px;margin-bottom: 5px;" >
										<label for="" class="mt-3" style="font-weight: bold;">Mật khẩu</label>
									</div>
								</div>
							</div>
							<div class="col-7">
								<div class="row mt-2 mb-2">
									<div class="col">
										<div class="form-group">
											<form:input path="userName" name="userName" type="text" class="form-control" id="userName" style="border:solid 1px"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<form:input path="passWord" name="passWord" type="password" class="form-control" id="passWord" style="border:solid 1px" />
											<div>
												<p id="login-message" style="font-size: 15px;" class="text-danger mt-2 error-msg">${errors }</p>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="row">
											<div class="col-6">
												<button type="button" class="btn btn-block" id="submitBtn" style="border: solid 1px black;box-shadow: 2px 3px 4px 0px;">Đăng nhập</button>
											</div>
											<div class="col-6">
												<button type="button" class="btn btn-block" data-dismiss="modal" id="close-btn" style="border: solid 1px black ;box-shadow: 2px 3px 4px 0px;">Thoát</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row mt-2">
									<div class="col">
										<input type="checkbox"> <label for="" style="font-weight: bold;">Nhớ mật khẩu</label>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<footer class="footer bg-light text-center py-3 mt-5">
		<div class="container">
			<span class="text-muted">© 2024 Hệ Thống Nhà Trẻ</span>
		</div>
	</footer>
	<script src="<c:url value="resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="resources/js/popper.min.js" />"></script>
	<script src="<c:url value="resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="resources/validate/login.js" />"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
</body>
</html>
