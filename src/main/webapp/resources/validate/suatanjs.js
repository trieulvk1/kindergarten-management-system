$(document).ready(function() {

	//------------------- KHỞI TẠO SUẤT ĂN MỚI VÀ NGĂN CHẶN SỰ KIỆN MẶC ĐỊNH ------------------------------

	var createdSuatAnId;
	$('#khoiTaoBtn').click(function(e) {
		e.preventDefault(); // Ngăn chặn sự kiện mặc định
		themSuatAn('them', null);
	});

	$('#updateSuatAn').click(function(e) {
		e.preventDefault(); // Ngăn chặn sự kiện mặc định
		themSuatAn('capnhat', createdSuatAnId);
	});


	// Hàm 'themSuatAn' dùng để thêm hoặc cập nhật suất ăn
	function themSuatAn(action, selectedMaSuatAn) {
		var errors = false;

		// Kiểm tra tên suất ăn, nếu trống thì báo lỗi
		if ($('#tenSuatAn').val().trim() === '') {
			$('#tenSuatAn').addClass('is-invalid border-danger');
			$('.error-msg1').text('Vui lòng nhập tên suất ăn.').addClass('text-danger');
			errors = true;
		} else if (!/^[a-zA-Z]/.test($('#tenSuatAn').val().trim())) {
			$('#tenSuatAn').addClass('is-invalid border-danger');
			$('.error-msg1').text('Tên suất ăn phải bắt đầu bằng một chữ cái.').addClass('text-danger');
			errors = true;
		} else {
			$('#tenSuatAn').removeClass('is-invalid border-danger');
			$('.error-msg1').text('');
		}

		// Kiểm tra lứa tuổi, nếu trống thì báo lỗi
		if ($('#luaTuoi').val() == '') {
			$('#luaTuoi').addClass('is-invalid border-danger');
			$('.error-msg2').text('Vui lòng chọn lứa tuổi.').addClass('text-danger');
			errors = true;
		} else {
			$('#luaTuoi').removeClass('is-invalid border-danger');
			$('.error-msg2').text('');
		}

		// Kiểm tra số lượng
		const saSoLuongInput = $('#saSoLuong');
		const saSoLuong = parseFloat(saSoLuongInput.val().trim());

		if (isNaN(saSoLuong)) {
			saSoLuongInput.addClass('is-invalid border-danger');
			$('.error-msg3').text('Vui lòng nhập số lượng.').addClass('text-danger');
			errors = true;
		} else if (saSoLuong <= 0) {
			saSoLuongInput.addClass('is-invalid border-danger');
			$('.error-msg3').text('Số lượng phải lớn hơn 0.').addClass('text-danger');
			errors = true;
		} else {
			saSoLuongInput.removeClass('is-invalid border-danger');
			$('.error-msg3').text('');
		}

		// Kiểm tra nội dung, nếu trống thì báo lỗi
		if ($('#noiDung').val() == '') {
			$('#noiDung').addClass('is-invalid border-danger');
			$('.error-msg4').text('Vui lòng nhập nội dung.').addClass('text-danger');
			errors = true;
		} else {
			$('#noiDung').removeClass('is-invalid border-danger');
			$('.error-msg4').text('');
		}

		// Kiểm tra loại suất ăn, nếu trống thì báo lỗi
		if ($('#loaiSuatAn').val() == '') {
			$('#loaiSuatAn').addClass('is-invalid border-danger');
			$('.error-msg5').text('Vui lòng chọn loại suất ăn.').addClass('text-danger');
			errors = true;
		} else {
			$('#loaiSuatAn').removeClass('is-invalid border-danger');
			$('.error-msg5').text('');
		}

		// Nếu có lỗi thì dừng lại, không tiếp tục
		if (errors) {
			return; // Ngăn chặn việc tiếp tục nếu có lỗi
		}

		// Tạo đối tượng suất ăn từ các giá trị nhập vào
		var suatAn = {
			maSuatAn: selectedMaSuatAn,
			tenSuatAn: $('#tenSuatAn').val(),
			noiDung: $('#noiDung').val(),
			luaTuoi: $('#luaTuoi').val(),
			saSoLuong: $('#saSoLuong').val(),
			loaiSuatAn: $('#loaiSuatAn').val(),
			tongTien: $('#totalMoney').val()
		};

		// Tạo danh sách thực phẩm từ bảng
		var thucPhamList = [];
		$('#tableFood tbody tr').each(function(index, tr) {
			var thucPhamId = $(tr).find('.idThucPham').text();
			thucPhamList.push(thucPhamId);
		});
		console.log("suatAn:", suatAn);
		console.log("thucPhamList:", thucPhamList);

		// Gửi yêu cầu AJAX đến server để lưu suất ăn
		$.ajax({
			url: '/MockProject/quanly/suatan/saveSuatAn',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				suatAn: suatAn,
				thucPhamList: thucPhamList
			}),
			success: function(response) {
				if (action === 'them') {
					toastr.success("Dữ liệu đã được thêm thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
					createdSuatAnId = response;
				} else if (action === 'capnhat') {
					toastr.success("Dữ liệu đã được cập nhật thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
				}
				var savedSuatAnId = response;
				console.log("ID của suất ăn vừa được lưu:", savedSuatAnId);

				// Cập nhật ID của suất ăn vừa được lưu vào nút xóa cố định
				$('#deleteSuatAn').data('id', savedSuatAnId);
			},
			error: function(xhr) {
				if (xhr.status === 400 && xhr.responseText.includes("isExist")) {
					toastr.error("Tên suất ăn đã tồn tại, vui lòng chọn tên khác", "Thất bại", {
						positionClass: "toast-top-right",
					});
				} else {
					toastr.error("Thêm dữ liệu thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			}
		});
	}


	// Ẩn thông báo valida suất ăn
	function messageValid() {
		$('#tableFood tbody').empty();
		$('#tenSuatAn').removeClass('is-invalid border-danger');
		$('#luaTuoi').removeClass('is-invalid border-danger');
		$('.error-msg2').text('');
		$('.error-msg1').text('');
		$('#saSoLuong').removeClass('is-invalid border-danger');
		$('.error-msg3').text('');
		$('#loaiSuatAn').removeClass('is-invalid border-danger');
		$('.error-msg5').text('');
		$('#noiDung').removeClass('is-invalid border-danger');
		$('.error-msg4').text('');
	}

	//------------------- COPY THÔNG TIN SUẤT ĂN MẪU ------------------------------
	$('#copySuatAn').click(function() {
		var selectedMaSuatAn = $('#suatAnMau').val();
		if (selectedMaSuatAn == 0) {
			toastr.warning("Vui lòng chọn suất ăn muốn copy", "Thất bại", {
				positionClass: "toast-top-right",
			});
		} else {
			messageValid()
			$.ajax({
				url: '/MockProject/quanly/suatan/getSuatAnMauToCopy',
				data: { maSuatAn: selectedMaSuatAn },
				type: 'GET',
				success: function(response) {
					if (response) {
						toastr.success("Copy dữ liệu suất ăn thành công", "Thành công", {
							positionClass: "toast-top-right"
						});

					} else {
						toastr.warning("Copy dữ liệu suất ăn thành công. Suất ăn chưa có thực phẩm nào", {
							positionClass: "toast-top-right",
						});
					}
					var savedSuatAnId = response.maSuatAn;
					$('#deleteSuatAn').data('id', savedSuatAnId);
					var firstSuatAn = searchedData[0];
					// Gán giá trị của đối tượng firstSuatAn vào các trường HTML
					$('#tenSuatAn').val(firstSuatAn.tenSuatAn);
					$('#luaTuoi').val(firstSuatAn.luaTuoi);
					$('#saSoLuong').val(firstSuatAn.saSoLuong);
					$('#noiDung').val(firstSuatAn.noiDung);
					$('#loaiSuatAn').val(firstSuatAn.loaiSuatAn);
					if (firstSuatAn.idThucPham != null) {
						searchedData.forEach(function(item) {
							var newRow = $('<tr id="row-' + item.idThucPham + '">');
							newRow.append($('<td class="idThucPham">').text(item.idThucPham));
							newRow.append($('<td class="tenThucPham">').text(item.tenThucPham));
							newRow.append($('<td class="donViTinh">').text(item.donViTinh));
							newRow.append($('<td class="soLuong">').text(item.soLuong));
							newRow.append($('<td class="donGia">').text(item.donGia));
							newRow.append($('<td class="thanhTien">').text(item.thanhTien || 'N/A'));
							newRow.append($('<td class="nangLuong">').text(item.nangLuong || 'N/A'));
							newRow.append($('<td class="chatDam">').text(item.chatDam.toFixed(1) || 'N/A'));
							newRow.append($('<td class="chatBeo">').text(item.chatBeo.toFixed(1) || 'N/A'));
							newRow.append($('<td class="botDuong">').text(item.botDuong.toFixed(1) || 'N/A'));
							newRow.append($('<td class="noiLay"><input type="checkbox" class="noiLayCheckbox"' + (item.noiLay === "kho" ? "checked" : "") + ' disabled="true"></td>'));
							$('#tableFood').append(newRow);
						});
						calculateNutrition($('.table1'));
					}
				},
				error: function(xhr, status, error) {
					toastr.error(xhr.responseText, "Lỗi", {
						positionClass: "toast-top-right",
					});
				}

			});
		}
	});

	var searchedData = null; // Biến toàn cục để lưu trữ thông tin tìm kiếm

	//------------------- TÌM KIẾM VÀ HIỂN THỊ THÔNG TIN SUẤT ĂN ------------------------------
	$('#searchSuatAn').click(function() {
		var selectedMaSuatAn = $('#suatAnMau').val();
		savedSuatAnId = selectedMaSuatAn;
		$('#deleteSuatAn').data('id', savedSuatAnId);
		if (selectedMaSuatAn == 0) {
			toastr.warning("Vui lòng chọn suất ăn muốn tìm", "Thất bại", {
				positionClass: "toast-top-right",
			});
		} else {
			$('#tableFood tbody').empty();
			messageValid();
			$.get('/MockProject/quanly/suatan/getSuatAnMau', { maSuatAn: selectedMaSuatAn })
				.done(function(data) {
					searchedData = data;
					var firstSuatAn = data[0];
					// Gán giá trị của đối tượng firstSuatAn vào các trường HTML
					createdSuatAnId = firstSuatAn.maSuatAn;
					$('#tenSuatAn').val(firstSuatAn.tenSuatAn);
					$('#luaTuoi').val(firstSuatAn.luaTuoi);
					$('#saSoLuong').val(firstSuatAn.saSoLuong);
					$('#noiDung').val(firstSuatAn.noiDung);
					$('#loaiSuatAn').val(firstSuatAn.loaiSuatAn);
					if (firstSuatAn.idThucPham != null) {
						data.forEach(function(item) {
							var newRow = $('<tr id="row-' + item.idThucPham + '">');
							newRow.append($('<td class="idThucPham">').text(item.idThucPham));
							newRow.append($('<td class="tenThucPham">').text(item.tenThucPham));
							newRow.append($('<td class="donViTinh">').text(item.donViTinh));
							newRow.append($('<td class="soLuong">').text(item.soLuong));
							newRow.append($('<td class="donGia">').text(item.donGia));
							newRow.append($('<td class="thanhTien">').text(item.thanhTien || 'N/A'));
							newRow.append($('<td class="nangLuong">').text(item.nangLuong.toFixed(1) || 'N/A'));
							newRow.append($('<td class="chatDam">').text(item.chatDam.toFixed(1) || 'N/A'));
							newRow.append($('<td class="chatBeo">').text(item.chatBeo.toFixed(1) || 'N/A'));
							newRow.append($('<td class="botDuong">').text(item.botDuong.toFixed(1) || 'N/A'));
							newRow.append($('<td class="noiLay"><input type="checkbox" class="noiLayCheckbox"' + (item.noiLay === "kho" ? "checked" : "") + ' disabled="true"></td>'));
							$('#tableFood').append(newRow);
						});
						calculateNutrition($('.table1')); // Tính lại dinh dưỡng sau khi thêm mới
						toastr.success("Tìm kiếm suất ăn thành công", "Thành công", {
							positionClass: "toast-top-right",
						});
					} else {
						calculateNutrition($('.table1')); // Tính lại dinh dưỡng sau khi thêm mới
						toastr.warning("Tìm kiếm suất ăn thành công. Suất ăn chưa thêm thực phẩm nào", "Thành công", {
							positionClass: "toast-top-right",
						});
					}

				})
				.fail(function(xhr, status, error) {
					toastr.error("Tìm kiếm suất ăn thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});

				});
		}

	});


	//------------------- XÓA THÔNG TIN SUẤT ĂN ------------------------------
	$('#deleteSuatAn').click(function() {
		var suatAnId = $(this).data('id');
		if (suatAnId) {
			deleteSuatAn(suatAnId);
		} else {
			toastr.warning("Không có suất ăn nào để xóa", "Lỗi", {
				positionClass: "toast-top-right",
			});
		}
	});

	// Hàm để xóa suất ăn dựa trên ID của suất ăn được chọn
	function deleteSuatAn(suatAnId) {
		// Sử dụng hộp thoại swal thay vì hộp thoại alert
		Swal.fire({
			title: 'Xác nhận',
			text: "Bạn có chắc chắn muốn xóa suất ăn này?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Xóa',
			cancelButtonText: 'Hủy'
		}).then((result) => {
			if (result.isConfirmed) {
				// Nếu người dùng đồng ý xóa
				$.ajax({
					url: '/MockProject/quanly/suatan/deleteSuatAn/' + suatAnId,
					type: 'DELETE',
					success: function(response) {
						toastr.success("Suất ăn đã được xóa thành công", "Thành công", {
							positionClass: "toast-top-right",
						});
						// Xóa ID khỏi nút xóa sau khi xóa thành công
						$('#deleteFixedBtn').data('id', '');
						$('#tenSuatAn').val("");
						$('#luaTuoi').val("");
						$('#saSoLuong').val("");
						$('#noiDung').val("");
						$('#loaiSuatAn').val("");
						$('#tableFood tbody').empty();
						calculateNutrition($('.table1'));
					},
					error: function() {
						toastr.error("Xóa suất ăn thất bại", "Thất bại", {
							positionClass: "toast-top-right",
						});
					}
				});
			}
		});
	}

	//------------------- HIỂN THỊ DANH SÁCH SUẤT ĂN MẪU ------------------------------
	$('#suatAnMau').click(function() {
		$.ajax({
			type: 'GET',
			url: '/MockProject/quanly/suatan/getSuatAnMauList',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				var select = $('#suatAnMau');
				var selectedValue = select.val();
				$('#deleteSuatAn').data('id', selectedValue);
				select.empty(); // Xóa các tùy chọn hiện tại
				select.append('<option value="">-Danh sách suất ăn mẫu</option>');

				$.each(data, function(index, item) {
					select.append('<option value="' + item.maSuatAn + '">' + item.tenSuatAn + '</option>');
				});

				// Đặt lại giá trị đã chọn nếu có
				if (selectedValue) {
					select.val(selectedValue);
				}
			},
			error: function(xhr, status, error) {
				console.error("Error fetching SuatAnMau:", error);
				// Xử lý lỗi ở đây nếu cần
			}
		});
	});

	//------------------- HIỂN THỊ DANH SÁCH NHÓM THỰC PHẨM ------------------------------
	$('#listNhomThucPham').change(function() {
		var selectedNhomThucPham = $(this).val();
		console.log(selectedNhomThucPham);
		// Gửi yêu cầu AJAX để lấy danh sách thực phẩm thuộc nhóm được chọn
		$.get('/MockProject/quanly/thucpham/getNhom', { maNhom: selectedNhomThucPham })
			.done(function(data) {
				console.log(data);
				$('#thucPhamDtoList').empty();
				$('#thucPhamDtoList').append($('<option>', {
					value: '',
					text: 'Chọn thực phẩm'
				}));
				$.each(data, function(index, thucPham) {
					$('#thucPhamDtoList').append($('<option>', {
						value: thucPham.idThucPham,
						text: thucPham.tenThucPham
					}));
				});
			})
			.fail(function(xhr, status, error) {
				console.error("Failed to fetch food items: ", xhr.responseText);
			});
	});

	//------------------- HIỂN THỊ DANH SÁCH THỰC PHẨM THEO NHÓM ------------------------------
	$('#thucPhamDtoList').change(function() {
		var selectedThucPhamId = $(this).val();
		console.log(selectedThucPhamId);
		// Gửi yêu cầu AJAX để lấy thông tin chi tiết của thực phẩm được chọn
		$.get('/MockProject/quanly/thucpham/getThucPham', { idThucPham: selectedThucPhamId })
			.done(function(data) {
				// Hiển thị thông tin chi tiết của thực phẩm
				$('#pDV').val(data.damDV);
				$('#pTV').val(data.damTV);
				$('#lDV').val(data.beoDV);
				$('#lTV').val(data.beoTV);
			})
			.fail(function(xhr, status, error) {
				console.error("Failed to fetch chi tiet chat: ", xhr.responseText);
			});

	});

	var currentRow = null;
	// Sự kiện click trên toàn bộ dòng tr
	$('#tableFood').on('click', 'tr', function() {
		if (!$('#editButton').is(':visible')) {
			return; // Không cho phép chọn dòng khác khi đang chỉnh sửa
		}

		// Loại bỏ lựa chọn từ tất cả các dòng khác
		$('#tableFood tr').removeClass('selected');

		// Chọn dòng được click
		$(this).addClass('selected');

		// Lưu hàng hiện tại
		currentRow = $(this);
		currentRow.addClass('selected').siblings().removeClass('selected');
		calculateAndUpdateNutrition(currentRow);
	});

	//------------------- THÊM THỰC PHẨM VÀO DANH SÁCH THỰC PHẨM CỦA SUẤT ĂN ------------------------------
	// Hàm định dạng số thành chuỗi tiền tệ VND
	function formatCurrency(value) {
		return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
	}

	$('#addThucPham').click(function() {
		var selectedThucPhamId = $('#thucPhamDtoList').val();
		if (!selectedThucPhamId) {
			toastr.warning("Vui lòng chọn thực phẩm muốn thêm", "Thất bại", {
				positionClass: "toast-top-right",
			});
			return;
		}

		// Kiểm tra xem thực phẩm đã tồn tại trong bảng chưa
		var isThucPhamExists = false;
		$('#tableFood tbody tr').each(function() {
			var idThucPham = $(this).find('.idThucPham').text();
			if (idThucPham === selectedThucPhamId) {
				isThucPhamExists = true;
				return false; // Thoát khỏi vòng lặp nếu đã tìm thấy ID
			}
		});

		if (isThucPhamExists) {
			toastr.error("Thực phẩm đã tồn tại trong suất ăn", "Thất bại", {
				positionClass: "toast-top-right",
			});
			return;
		}

		// Gửi yêu cầu AJAX để lấy thông tin về thực phẩm được chọn
		$.get('/MockProject/quanly/suatan/addThucPham', { idThucPham: selectedThucPhamId })
			.done(function(data) {
				console.log(data); // Log dữ liệu nhận được từ máy chủ
				// Tạo một hàng mới trong bảng
				var newRow = $('<tr id="row-' + data.idThucPham + '">');
				newRow.append($('<td class="idThucPham">').text(data.idThucPham));
				newRow.append($('<td class="tenThucPham">').text(data.tenThucPham));
				newRow.append($('<td class="donViTinh">').text(data.donViTinh));
				newRow.append($('<td class="soLuong">').text(data.soLuong));
				newRow.append($('<td class="donGia">').text(data.donGia)|| 'N/A');
				newRow.append($('<td class="thanhTien">').text(data.thanhTien  || 'N/A'));
				newRow.append($('<td class="nangLuong">').text(data.nangLuong.toFixed(1) || 'N/A'));
				newRow.append($('<td class="chatDam">').text(data.chatDam.toFixed(1) || 'N/A'));
				newRow.append($('<td class="chatBeo">').text(data.chatBeo.toFixed(1) || 'N/A'));
				newRow.append($('<td class="botDuong">').text(data.botDuong.toFixed(1) || 'N/A'));
				newRow.append($('<td class="noiLay"><input type="checkbox" class="noiLayCheckbox"' + (data.noiLay === "kho" ? "checked" : "") + ' disabled="true"></td>'));
				$('#tableFood').append(newRow);
				calculateNutrition($('#tableFood')); // Tính lại dinh dưỡng sau khi thêm mới
			})
			.fail(function(xhr, status, error) {
				console.error("Failed to fetch chi tiet chat: ", xhr.responseText);
			});
	});


	//------------------- CHỈNH SỬA THÔNG TIN THỰC PHẨM ------------------------------
	$('#editButton').click(function() {
		if (currentRow) {
			// Ẩn nút chỉnh sửa và hiển thị nút lưu
			$(this).hide();
			$('#saveButton').show();

			// Chuyển các ô trong hàng thành input
			currentRow.find('td.idThucPham,td.tenThucPham, td.donViTinh, td.soLuong, td.donGia, td.thanhTien, td.nangLuong, td.chatDam, td.chatBeo, td.botDuong').each(function() {
				var text = $(this).text();

				$(this).html('<input type="text" class="form-control" value="' + text + '">');

				if ($(this).hasClass('thanhTien') || $(this).hasClass('idThucPham')) {
					// Chuyển ô idThucPham thành readonly input
					$(this).html('<input type="text" class="form-control" value="' + text + '" readonly>');
				} else {
					$(this).html('<input type="text" class="form-control" value="' + text + '">');
				}
			});

			// Chuyển ô checkbox thành có thể chỉnh sửa
			currentRow.find('td.noiLay input[type="checkbox"]').prop('disabled', false);

			// Gỡ bỏ sự kiện click trên dòng trước đó
			$('#tableFood').off('click', 'tr');
		} else {
			toastr.warning("Vui lòng chọn thực phẩm muốn sửa", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	// Hàm tính toán thành tiền khi thay đổi số lượng / đơn giá của thực phẩm
	function calculateAndUpdateThanhTien(row) {
		var soLuong = parseFloat(row.find('.soLuong input').val());
		var donGia = parseFloat(row.find('.donGia input').val());
		var thanhTien = soLuong * donGia;
		row.find('.thanhTien input').val(thanhTien.toFixed(0));
	}
	$('#tableFood').on('input', 'td.soLuong input, td.donGia input', function() {
		var row = $(this).closest('tr');
		calculateAndUpdateThanhTien(row);
	});


	//------------------- LƯU THÔNG TIN THỰC PHẨM SAU KHI CHỈNH SỬA ------------------------------
	$('#saveButton').click(function() {
		if (currentRow) {
			// Kiểm tra xem tất cả các trường đã được nhập đầy đủ và hợp lệ hay chưa
			let isValid = true;
			currentRow.find('td.tenThucPham, td.donViTinh, td.soLuong, td.donGia, td.thanhTien, td.nangLuong, td.chatDam, td.chatBeo, td.botDuong').each(function() {
				const input = $(this).find('input');
				if (input.length === 0) {
					return; // Bỏ qua nếu không tìm thấy trường input trong ô dữ liệu
				}
				const inputValue = input.val().trim();
				const isNumberField = $(this).hasClass('soLuong')
					|| $(this).hasClass('donGia')
					|| $(this).hasClass('nangLuong')
					|| $(this).hasClass('chatDam')
					|| $(this).hasClass('chatBeo')
					|| $(this).hasClass('botDuong');
				const isIntegerField = $(this).hasClass('soLuong');

				if (inputValue === '') {
					input.addClass('is-invalid');
					isValid = false;
				} else if (isNumberField && !/^-?\d+(\.\d+)?$/.test(inputValue)) {
					// Sửa đổi biểu thức chính quy để chấp nhận số thập phân
					input.addClass('is-invalid');
					isValid = false;
				} else if (isIntegerField && !/^-?\d+$/.test(inputValue)) {
					// Kiểm tra nếu là trường số lượng, và giá trị không phải số nguyên
					input.addClass('is-invalid');
					isValid = false;
				} else if (isNumberField && parseFloat(inputValue) <= 0) {
					input.addClass('is-invalid');
					isValid = false;
				} else if (!isNumberField && !$(this).hasClass('thanhTien') && !/^[a-zA-Z]/.test(inputValue)) {
					input.addClass('is-invalid');
					isValid = false;
				} else {
					input.removeClass('is-invalid');
				}
			});


			if (!isValid) {
				toastr.error("Vui lòng nhập đầy đủ chính xác các trường", "Thất bại", {
					positionClass: "toast-top-right",
				});
				return; // Ngăn việc gửi dữ liệu nếu có trường bị trống
			}

			// Lưu các giá trị trong input vào bảng
			var updatedData = {};
			currentRow.find('td.idThucPham, td.tenThucPham, td.donViTinh, td.soLuong, td.donGia, td.thanhTien, td.nangLuong, td.chatDam, td.chatBeo, td.botDuong').each(function() {
				var columnName = $(this).attr('class');
				var input = $(this).find('input');
				var text = input.val();
				$(this).text(text);

				// Sửa đổi cách lưu giá trị vào updatedData
				if (columnName === 'donGia' || columnName === 'thanhTien' || columnName === 'nangLuong' || columnName === 'chatDam' || columnName === 'chatBeo' || columnName === 'botDuong') {
					updatedData[columnName] = parseFloat(text);
				} else {
					updatedData[columnName] = text;
				}
			});


			// Lưu trạng thái checkbox
			var checkbox = currentRow.find('td.noiLay input[type="checkbox"]');
			updatedData['noiLay'] = checkbox.is(':checked') ? 'kho' : 'bên ngoài';

			// Gửi dữ liệu lên server
			$.ajax({
				url: '/MockProject/quanly/thucpham/save',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(updatedData),
				success: function(response) {
					$('#saveButton').hide();
					$('#editButton').show();
					toastr.success("Cập nhật dữ liệu thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
					calculateNutrition($('.table1'));
					currentRow.removeClass('selected');
					currentRow = null;

					// Thêm lại sự kiện click cho hàng
					$('#tableFood').on('click', 'tr', function() {
						if (!$('#editButton').is(':visible')) {
							return; // Không cho phép chọn dòng khác khi đang chỉnh sửa
						}

						// Loại bỏ lựa chọn từ tất cả các dòng khác
						$('#tableFood tr').removeClass('selected');

						// Chọn dòng được click
						$(this).addClass('selected');

						// Lưu hàng hiện tại
						currentRow = $(this);
						currentRow.addClass('selected').siblings().removeClass('selected');
						calculateAndUpdateNutrition(currentRow);
					});
				},
				error: function() {
					toastr.error("Cập nhật dữ liệu thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});

			// Khóa lại checkbox sau khi lưu
			currentRow.find('td.noiLay input[type="checkbox"]').prop('disabled', true);
		}
	});

	//------------------- XÓA THỰC PHẨM TRONG DANH SÁCH SUẤT ĂN ------------------------------
	$('#deleteFood').click(function() {
		var selectedRow = $('#tableFood tr.selected');
		if (selectedRow.length) {
			var selectedRowId = selectedRow.data('id');
			console.log('ID của dòng được chọn:', selectedRowId);
			// Xóa dòng đã chọn khỏi bảng
			selectedRow.remove();
			// Gọi lại hàm tính toán dinh dưỡng nếu cần thiết
			calculateNutrition($('.table1'));
			calculateAndUpdateNutrition($('.selected'));
			toastr.success("Xóa dữ liệu thành công", "Thành công", {
				positionClass: "toast-top-right",
			});
		} else {
			toastr.warning("Vui lòng chọn thực phẩm muốn xóa", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	// Hàm tính toán và cập nhật thông số
	function calculateNutrition(table) {
		var money = 0;
		var totalCalo = 0;
		var totalProtid = 0;
		var totalLipid = 0;
		var totalLucid = 0;

		var totalCaloPerServing = 0;
		var totalProtidPerServing = 0;
		var totalLipidPerServing = 0;
		var totalLucidPerServing = 0;
		var totalSuatAn = 0;

		table.find('tbody tr').each(function() {
			var thanhTienText = $(this).find('.thanhTien').text();
			var thanhTien = parseFloat(thanhTienText.replace(/[^0-9.-]+/g, "")) || 0; // Chuyển đổi định dạng tiền tệ thành số
			var calo = parseFloat($(this).find('td:nth-child(7)').text()) || 0;
			var protid = parseFloat($(this).find('td:nth-child(8)').text()) || 0;
			var lipid = parseFloat($(this).find('td:nth-child(9)').text()) || 0;
			var lucid = parseFloat($(this).find('td:nth-child(10)').text()) || 0;

			money += thanhTien;
			totalCalo += calo;
			totalProtid += protid;
			totalLipid += lipid;
			totalLucid += lucid;
			totalSuatAn++;

			totalCaloPerServing += calo / totalSuatAn;
			totalProtidPerServing += protid / totalSuatAn;
			totalLipidPerServing += lipid / totalSuatAn;
			totalLucidPerServing += lucid / totalSuatAn;
		});

		// Tính tỉ số từng chất
		var totalNutrients = totalProtid + totalLipid + totalLucid;
		var ratioP = totalNutrients > 0 ? (totalProtid / totalNutrients) * 100 : 0;
		var ratioL = totalNutrients > 0 ? (totalLipid / totalNutrients) * 100 : 0;
		var ratioG = totalNutrients > 0 ? (totalLucid / totalNutrients) * 100 : 0;

		var singleNumber = Math.sqrt(Math.pow(ratioP, 2) + Math.pow(ratioL, 2) + Math.pow(ratioG, 2));

		// Tính tỷ lệ phần trăm đạt được
		var ratioAchievementC = totalCalo > 0 ? (totalCaloPerServing / totalCalo) * 100 : 0;
		var ratioAchievementP = totalProtid > 0 ? (totalProtidPerServing / totalProtid) * 100 : 0;
		var ratioAchievementL = totalLipid > 0 ? (totalLipidPerServing / totalLipid) * 100 : 0;
		var ratioAchievementG = totalLucid > 0 ? (totalLucidPerServing / totalLucid) * 100 : 0;

		// Cập nhật giao diện người dùng với các giá trị được tính toán
		$('#totalMoney').val(formatCurrency(money));
		$('#totalCalo').val(totalCalo.toFixed(1));
		$('#totalProtid').val(totalProtid.toFixed(1));
		$('#totalLipid').val(totalLipid.toFixed(1));
		$('#totalLucid').val(totalLucid.toFixed(1));
		$('#ratioPLGTC').val(singleNumber.toFixed(0) + '%');
		$('#ratioAchievementC').val(ratioAchievementC.toFixed(0) + '%');
		$('#ratioAchievementP').val(ratioAchievementP.toFixed(0) + '%');
		$('#ratioAchievementL').val(ratioAchievementL.toFixed(0) + '%');
		$('#ratioAchievementG').val(ratioAchievementG.toFixed(0) + '%');
	}



	//------------------- XUẤT FILE EXCEL SUẤT ĂN ------------------------------
	$('#exportToExcel').click(function() {
		var errors = false;

		// Kiểm tra tên suất ăn, nếu trống thì báo lỗi
		if ($('#tenSuatAn').val().trim() === '') {
			$('#tenSuatAn').addClass('is-invalid border-danger');
			$('.error-msg1').text('Vui lòng nhập tên suất ăn.').addClass('text-danger');
			errors = true;
		} else if (!/^[a-zA-Z]/.test($('#tenSuatAn').val().trim())) {
			$('#tenSuatAn').addClass('is-invalid border-danger');
			$('.error-msg1').text('Tên suất ăn phải bắt đầu bằng một chữ cái.').addClass('text-danger');
			errors = true;
		} else {
			$('#tenSuatAn').removeClass('is-invalid border-danger');
			$('.error-msg1').text('');
		}


		// Kiểm tra lứa tuổi, nếu trống thì báo lỗi
		if ($('#luaTuoi').val() == '') {
			$('#luaTuoi').addClass('is-invalid border-danger');
			$('.error-msg2').text('Vui lòng chọn lứa tuổi.').addClass('text-danger');
			errors = true;
		} else {
			$('#luaTuoi').removeClass('is-invalid border-danger');
			$('.error-msg2').text('');
		}

		// Kiểm tra số lượng
		const saSoLuongInput = $('#saSoLuong');
		const saSoLuong = parseFloat(saSoLuongInput.val().trim());

		if (isNaN(saSoLuong)) {
			saSoLuongInput.addClass('is-invalid border-danger');
			$('.error-msg3').text('Vui lòng nhập số lượng.').addClass('text-danger');
			errors = true;
		} else if (saSoLuong <= 0) {
			saSoLuongInput.addClass('is-invalid border-danger');
			$('.error-msg3').text('Số lượng phải lớn hơn 0.').addClass('text-danger');
			errors = true;
		} else {
			saSoLuongInput.removeClass('is-invalid border-danger');
			$('.error-msg3').text('');
		}

		// Kiểm tra nội dung, nếu trống thì báo lỗi
		if ($('#noiDung').val() == '') {
			$('#noiDung').addClass('is-invalid border-danger');
			$('.error-msg4').text('Vui lòng nhập nội dung.').addClass('text-danger');
			errors = true;
		} else {
			$('#noiDung').removeClass('is-invalid border-danger');
			$('.error-msg4').text('');
		}

		// Kiểm tra loại suất ăn, nếu trống thì báo lỗi
		if ($('#loaiSuatAn').val() == '') {
			$('#loaiSuatAn').addClass('is-invalid border-danger');
			$('.error-msg5').text('Vui lòng chọn loại suất ăn.').addClass('text-danger');
			errors = true;
		} else {
			$('#loaiSuatAn').removeClass('is-invalid border-danger');
			$('.error-msg5').text('');
		}

		// Nếu có lỗi thì dừng lại, không tiếp tục
		if (errors) {
			return; // Ngăn chặn việc tiếp tục nếu có lỗi
		}

		// Tạo đối tượng suất ăn từ các giá trị nhập vào
		var suatAn = {
			tenSuatAn: $('#tenSuatAn').val(),
			noiDung: $('#noiDung').val(),
			luaTuoi: $('#luaTuoi').val(),
			saSoLuong: $('#saSoLuong').val(),
			loaiSuatAn: $('#loaiSuatAn').val(),
			tongTien: $('#totalMoney').val()
		};

		// Tạo danh sách thực phẩm từ bảng
		var thucPhamList = [];
		$('#tableFood tbody tr').each(function(index, tr) {
			var thucPhamId = $(tr).find('.idThucPham').text();
			thucPhamList.push(thucPhamId);
		});

		$.ajax({
			url: '/MockProject/quanly/suatan/checkBeforeExport',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				suatAn: suatAn,
				thucPhamList: thucPhamList
			}),
			success: function(response) {
				if (response === true) {
					// Lấy dữ liệu từ các trường input
					var tenSuatAn = $('#tenSuatAn').val();
					var luaTuoi = $('#luaTuoi').val();
					var saSoLuong = $('#saSoLuong').val();
					var noiDung = $('#noiDung').val();
					var loaiSuatAn = $('#loaiSuatAn').val();
					var tongTien = $('#totalMoney').val();

					// Tạo dữ liệu cho bảng
					var data = [
						['**Thông tin suất ăn**'],
						['Tên suất ăn', 'Lứa tuổi', 'Số lượng', 'Nội dung', 'Loại suất ăn', 'Tổng tiền'],
						[tenSuatAn, luaTuoi, saSoLuong, noiDung, loaiSuatAn, tongTien],

						['', ''],
						['**Các món ăn**'],
						['ID', 'Tên thực phẩm', 'Đơn vị tính', 'Số lượng', 'Đơn giá', 'Thành tiền', 'Năng lượng', 'Protid', 'Lipid', 'Glucic', 'Lấy từ kho'],
					];

					// Thêm các món ăn vào dữ liệu
					$('#tableFood tbody tr').each(function() {
						var row = $(this);
						var id = row.find('td:eq(0)').text();
						var tenThucPham = row.find('td:eq(1)').text();
						var donViTinh = row.find('td:eq(2)').text();
						var soLuong = row.find('td:eq(3)').text();
						var donGia = row.find('td:eq(4)').text();
						var thanhTien = row.find('td:eq(5)').text();
						var nangLuong = row.find('td:eq(6)').text();
						var protid = row.find('td:eq(7)').text();
						var lipid = row.find('td:eq(8)').text();
						var glucic = row.find('td:eq(9)').text();
						var layTuKho = row.find('td:eq(10) input[type="checkbox"]').is(':checked') ? "kho" : "ngoài";

						data.push([id, tenThucPham, donViTinh, soLuong, donGia, thanhTien, nangLuong, protid, lipid, glucic, layTuKho]);
					});

					// Thêm thành phần dinh dưỡng vào dữ liệu
					data.push(['', '']);
					data.push(['**Thành phần dinh dưỡng**']);
					data.push(['Suất ăn', 'T/C']);
					data.push(['Năng lượng', $('#totalCalo').val()]);
					data.push(['Đạm (P) - (g)', $('#totalProtid').val()]);
					data.push(['Chất béo (L) - (g)', $('#totalLipid').val()]);
					data.push(['Bột đường (G) - (g)', $('#totalLucid').val()]);
					data.push(['Tỷ lệ P-L-G (%)', $('#ratioPLGTC').val()]);
					data.push(['Tỉ lệ đạt (%) - Calo', $('#ratioAchievementC').val(), '']);
					data.push(['Tỉ lệ đạt (%) - Protid', $('#ratioAchievementP').val(), '']);
					data.push(['Tỉ lệ đạt (%) - Lipid', $('#ratioAchievementL').val(), '']);
					data.push(['Tỉ lệ đạt (%) - Lucid', $('#ratioAchievementG').val(), '']);

					// Tạo workbook và worksheet
					var workbook = XLSX.utils.book_new();
					var worksheet = XLSX.utils.aoa_to_sheet(data);
					XLSX.utils.book_append_sheet(workbook, worksheet, 'Suất ăn dinh dưỡng');

					// Tạo file Excel và download
					XLSX.writeFile(workbook, 'SuatAnDinhDuong.xlsx');
					toastr.success("Xuất excel thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
				} else {
					toastr.error("Vui lòng sao lưu suất ăn trước khi xuất Excel", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			},
			error: function() {
				toastr.error("Cập nhật dữ liệu thất bại", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}
		});


	});
});
