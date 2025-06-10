$(document).ready(function() {
	var contextPath = $('meta[name="context-path"]').attr('content');

	function login() {
		var userName = $('#userName').val();
		var passWord = $('#passWord').val();

		$('#userName').removeClass('is-invalid');
		$('#passWord').removeClass('is-invalid');
		$loginMessage.text('');

		$.ajax({
			type: 'POST',
			url: contextPath + '/login',
			data: { userName: userName, passWord: passWord },
			async: false,
			success: function(response) {
				$('#exampleModal').modal('show');
				if (response === "true") {
					toastr.success("Đăng nhập thành công", "Thành công", {
						positionClass: "toast-top-right"
					});
					setTimeout(function() {
						window.location.replace(contextPath + '/quanly/suatan');
					}, 1000);
				} else if (response == "userNameTrue") {
					$('#passWord').addClass('is-invalid border-danger');
					$loginMessage.html('<div class="alert alert-danger">Thông tin mật khẩu không đúng.</div>');
					toastr.error("Đăng nhập thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				} else if (response == "accountIsLock") {
					$('#userName').addClass('is-invalid border-danger');
					$('#passWord').addClass('is-invalid border-danger');
					$loginMessage.html('<div class="alert alert-danger">Tài khoản của bạn đã bị khóa, thử lại sau 5 phút.</div>');
					toastr.error("Đăng nhập thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				} else {
					$('#userName').addClass('is-invalid border-danger');
					$('#passWord').addClass('is-invalid border-danger');
					$loginMessage.html('<div class="alert alert-danger">Thông tin tài khoản và mật khẩu không đúng.</div>');
					toastr.error("Đăng nhập thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			},
			error: function(xhr, status, error) {
				console.error("Đăng nhập thất bại: ", xhr.responseText);
				$loginMessage.html('<div class="alert alert-danger">Đã xảy ra lỗi. Vui lòng thử lại sau.</div>');
				toastr.error("Đăng nhập thất bại", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}
		});
	}

	var $loginMessage = $('#login-message');

	var $loginMessage = $('#login-message');

	$('#formLogin').on('keyup', function(e) {
		// Nếu người dùng nhấn Enter
		if (e.keyCode === 13) {
			$('#submitBtn').click();
		}
	});

	$('#submitBtn').click(function(e) {
		e.preventDefault();

		// Xóa lỗi trước đó
		$('#userName, #passWord').removeClass('is-invalid border-danger');
		$loginMessage.html('').removeClass('text-danger');

		var userNameValue = $('#userName').val();
		var passWordValue = $('#passWord').val();

		var userNameRegex = /^[a-zA-Z][a-zA-Z0-9_]*$/;


		var errors = false;

		// Kiểm tra trường Tên đăng nhập
		if (userNameValue === '') {
			$('#userName').addClass('is-invalid border-danger');
			$loginMessage.html('<div class="alert alert-danger">Vui lòng nhập đầy đủ thông tin.</div>').addClass('text-danger');
			errors = true;
		} else if (!userNameRegex.test(userNameValue)) {
			$('#userName').addClass('is-invalid border-danger');
			$loginMessage.html('<div class="alert alert-danger">Tên đăng nhập không hợp lệ.</div>').addClass('text-danger');
			errors = true;
		}

		// Kiểm tra trường Mật khẩu
		if (passWordValue === '') {
			$('#passWord').addClass('is-invalid border-danger');
			$loginMessage.html('<div class="alert alert-danger">Vui lòng nhập đầy đủ thông tin.</div>').addClass('text-danger');
			errors = true;
		} else if (passWordValue.length <= 6) {
			$('#passWord').addClass('is-invalid border-danger');
			$loginMessage.html('<div class="alert alert-danger">Mật khẩu phải trên 6 ký tự.</div>').addClass('text-danger');
			errors = true;
		}


		if (!errors) {
			login();
		} else {
			toastr.error("Đăng nhập thất bại", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	function resetModal() {
		$('#userName, #passWord').val('').removeClass('is-invalid border-danger');
		$('.error-msg').text('');
		$('#error-msg12').text('');
		$loginMessage.html('');
	}

	$('#close-btn').click(function() {
		resetModal();
	});

	$('#loginForm').on('submit', function(e) {
		e.preventDefault();
	});

	// Ngăn modal bị đóng không mong muốn
	$('#exampleModal').on('hide.bs.modal', function(e) {
		if ($('.is-invalid').length > 0 || $loginMessage.text() !== '') {
			e.preventDefault();
		}
	});

	// Ngăn sự kiện click lan truyền đến các phần tử khác
	$('#exampleModal').on('click', function(e) {
		e.stopPropagation();
	});

});
