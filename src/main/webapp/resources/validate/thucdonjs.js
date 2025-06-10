$(document).ready(function() {
	//------------------- HIỂN THỊ DANH SÁCH THỰC ĐƠN MẪU ------------------------------
	$('#thucDonMau').click(function() {
		$.ajax({
			type: 'GET',
			url: '/MockProject/quanly/thucdon/getListThucDonMau',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				var select = $('#thucDonMau');
				var selectedValue = select.val();
				select.empty(); // Xóa các tùy chọn hiện tại
				select.append('<option value="">-THỰC ĐƠN MẪU</option>');

				$.each(data, function(index, item) {
					select.append('<option value="' + item.maThucDonMau + '">' + item.tenThucDonMau + '</option>');
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


	//------------------- THÊM/CẬP NHẬT THÔNG TIN THỰC ĐƠN ------------------------------
	$('#capNhat').click(function(e) {
		// Lấy giá trị được chọn từ các select box
		var selectedDate = $('#selectedDate').val();
		var nhomTuoi = $('#nhomTuoi').val();

		var isValid = true; // Biến để kiểm tra xem các giá trị đã được chọn có hợp lệ không

		// Kiểm tra và hiển thị thông báo lỗi một lần nếu có suất ăn trống
		var isValid = true;
		var errorShown = false;
		for (var day = 2; day <= 7; day++) {
			for (var meal = 1; meal <= 4; meal++) {
				var maSuatAn = $('#suatAnMauTD' + day + '-' + meal).val();
				if (maSuatAn === '') {
					isValid = false; // Nếu có suất ăn trống, đặt isValid là false
					if (!errorShown) {
						isValid = false;
					}
					// Hiển thị viền đỏ cho select box suất ăn không được chọn
					$('#suatAnMauTD' + day + '-' + meal).addClass('is-invalid');
				} else {
					// Loại bỏ viền đỏ nếu suất ăn đã được chọn
					$('#suatAnMauTD' + day + '-' + meal).removeClass('is-invalid');
				}
			}
		}

		// Kiểm tra ngày và nhóm tuổi
		if (!selectedDate) {
			$('#selectedDate').addClass('is-invalid'); // Thêm lớp 'is-invalid' để hiển thị viền đỏ
			isValid = false; // Đặt biến isValid là false
		} else {
			$('#selectedDate').removeClass('is-invalid'); // Loại bỏ lớp 'is-invalid'
		}

		if (!nhomTuoi) {
			$('#nhomTuoi').addClass('is-invalid'); // Thêm lớp 'is-invalid' để hiển thị viền đỏ
			isValid = false; // Đặt biến isValid là false
		} else {
			$('#nhomTuoi').removeClass('is-invalid'); // Loại bỏ lớp 'is-invalid'
		}

		// Nếu tất cả các giá trị hợp lệ, thực hiện lưu dữ liệu
		if (isValid) {
			saveThucDon();
		} else {
			toastr.error("Vui lòng chọn đầy đủ thông tin cho thực đơn", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	// Tạo yêu cầu lưu thực đơn
	function saveThucDon() {
		var thucDonRequest = {
			selectedDate: $('#selectedDate').val(),
			nhomTuoi: $('#nhomTuoi').val(),
			thucDonDtos: []
		};

		// Kiểm tra xem người dùng đã chọn làm mẫu hoặc xóa mẫu chọn hay không
		var isThucDonMau = $('#chonLamMau').is(':checked');
		var idThucDonMau = null;
		if ($('#xoaMauChon').is(':checked')) {
			idThucDonMau = $('#thucDonMau').val();
		}

		// Duyệt qua các ngày từ thứ 2 đến thứ 7 trong tuần
		for (var day = 2; day <= 7; day++) {
			var maNgay = 'NG' + ('0' + (day)).slice(-2);
			var maBuaAns = [];

			// Duyệt qua các bữa ăn (tổng cộng 4 bữa ăn)
			for (var meal = 0; meal < 4; meal++) {
				var maBuaAn = 'BA' + ('0' + (meal + 1)).slice(-2);
				var maSuatAn = $('#suatAnMauTD' + (day) + '-' + (meal + 1)).val();

				// Nếu có mã suất ăn được chọn, thêm vào danh sách mã bữa ăn
				if (maSuatAn) {
					var tenThucDonMau = isThucDonMau ? "Thực đơn mẫu - " + $('#selectedDate').val() : "";
					maBuaAns.push({
						maBuaAn: maBuaAn,
						maSuatAn: parseInt(maSuatAn),
						tenThucDonMau: tenThucDonMau,
						isThucDonMau: isThucDonMau
					});
				}
			}

			// Nếu danh sách mã bữa ăn không rỗng, thêm thông tin của ngày vào yêu cầu thực đơn
			if (maBuaAns.length > 0) {
				thucDonRequest.thucDonDtos.push({
					maNgay: maNgay,
					maBuaAns: maBuaAns,
					maNhomTuoi: $('#nhomTuoi').val(),
					isThucDonMau: isThucDonMau
				});
			}
		}

		console.log(JSON.stringify(thucDonRequest)); // Log dữ liệu yêu cầu

		var url = '/MockProject/quanly/thucdon/save';
		if (idThucDonMau) {
			url += '/' + idThucDonMau;
		}

		$.ajax({
			url: url,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(thucDonRequest),
			success: function(response) {
				toastr.success("Thực đơn đã được lưu thành công", "Thành công", {
					positionClass: "toast-top-right",
				});
			},
			error: function(xhr, status, error) {
				toastr.error("Lưu thực đơn thất bại", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}
		});
	};



	// Khởi tạo tổng số suất ăn và tổng tiền
	var tongSoSuatAn = 0;
	var tongTien = 0;

	// Khởi tạo giá trị hiện tại cho từng ngày và bữa ăn
	var currentSoLuong = {};
	var currentTongTien = {};

	for (var day = 2; day <= 7; day++) {
		currentSoLuong['day' + day] = { TD1: 0, TD2: 0, TD3: 0, TD4: 0 };
		currentTongTien['day' + day] = { TD1: 0, TD2: 0, TD3: 0, TD4: 0 };
	}

	// Cập nhật tổng số suất ăn và tổng tiền
	function updateTongSoSuatAnAndTongTien(day, meal, newSoLuong, newTongTien) {
		if (!currentSoLuong['day' + day]) {
			currentSoLuong['day' + day] = {};
		}
		if (!currentTongTien['day' + day]) {
			currentTongTien['day' + day] = {};
		}

		// Trừ đi giá trị cũ
		tongSoSuatAn -= currentSoLuong['day' + day][meal] || 0;
		tongTien -= currentTongTien['day' + day][meal] || 0;

		// Cập nhật giá trị mới
		currentSoLuong['day' + day][meal] = newSoLuong;
		currentTongTien['day' + day][meal] = newTongTien;

		// Cập nhật tổng số suất ăn và tổng tiền
		tongSoSuatAn += newSoLuong;
		tongTien += newTongTien;

		// Tính tổng số suất ăn và tổng tiền cho toàn bộ các bữa ăn trong ngày đó
		var soLuongTong = 0;
		var tongTienTong = 0;
		for (var i = 1; i <= 4; i++) {
			soLuongTong += currentSoLuong['day' + day]['TD' + i] || 0;
			tongTienTong += currentTongTien['day' + day]['TD' + i] || 0;
		}

		$('#soLuongT' + day).val(soLuongTong);
		$('#tongTienT' + day).val(tongTienTong);
	}


	// Hiển thị suất ăn theo ngày và bữa ăn được chọn
	function hienThiSuatAn(day, meal, selectedMaSuatAn, tableId) {
		$(tableId).empty();
		$.get('/MockProject/quanly/thucdon/getSuatAnMau', { maSuatAn: selectedMaSuatAn })
			.done(function(data) {
				var soLuong = 0;
				var tongTien1 = 0;
				if (data.length > 0) {
					var firstItem = data[0];
					soLuong = firstItem.saSoLuong;
					tongTien1 = firstItem.tongTien;
				}
				updateTongSoSuatAnAndTongTien(day, meal, soLuong, tongTien1);

				data.forEach(function(item) {
					var newRow = $('<tr id="row-' + item.idThucPham + '">');
					newRow.append($('<td class="tenThucPham p-1 pl-3">').text(item.tenThucPham));
					$(tableId).append(newRow);
				});
			})
			.fail(function(xhr, status, error) {
				console.error("Lấy chi tiết suất ăn thất bại: ", xhr.responseText);
			});
	}


	// Thiết lập các sự kiện thay đổi cho mã suất ăn
	function setChangeHandlers() {
		for (var day = 2; day <= 7; day++) {
			for (var meal = 1; meal <= 4; meal++) {
				$('#suatAnMauTD' + day + '-' + meal).change(function() {
					var day = $(this).data('day');
					var meal = $(this).data('meal');
					var selectedMaSuatAn = $(this).val();
					hienThiSuatAn(day, 'TD' + meal, selectedMaSuatAn, '#tableSuatAn' + day + '-' + meal);
				}).data('day', day).data('meal', meal);
			}
		}
	}

	setChangeHandlers();

	// Khai báo danh sách từng suất ăn theo từng ngày
	var dayMealMap = {
		'NG02': '#suatAnMauTD2',
		'NG03': '#suatAnMauTD3',
		'NG04': '#suatAnMauTD4',
		'NG05': '#suatAnMauTD5',
		'NG06': '#suatAnMauTD6',
		'NG07': '#suatAnMauTD7'
	};

	// Khai báo danh sách bữa ăn của ngày
	var mealTypeMap = {
		'BA01': '-1',
		'BA02': '-2',
		'BA03': '-3',
		'BA04': '-4'
	};

	var currentWeekMenu = null; // Biến lưu trữ thực đơn tuần hiện tại

	//------------------- CHỌN NGÀY ĐỂ TẠO THỰC ĐƠN, HIỂN THỊ THỰC ĐƠN NẾU ĐÃ TẠO ------------------------------
	$('#selectedDate').change(function() {
		var selectedDate = $(this).val();
		$('#thucDonMau').val('');

		// Reset tổng số suất ăn và tổng tiền trước khi tải dữ liệu mới
		tongSoSuatAn = 0;
		tongTien = 0;

		// Reset giá trị hiện tại cho từng ngày và bữa ăn
		for (var day = 2; day <= 7; day++) {
			for (var meal = 1; meal <= 4; meal++) {
				currentSoLuong['day' + day]['TD' + meal] = 0;
				currentTongTien['day' + day]['TD' + meal] = 0;
				$('#suatAnMauTD' + day + '-' + meal).val('')
				$('#tableSuatAn' + day + '-' + meal).empty();
			}
			$('#soLuongT' + day).val('0');
			$('#tongTienT' + day).val('0');
			$('#nhomTuoi').val('');
		}

		if (selectedDate) {
			$.ajax({
				url: '/MockProject/quanly/thucdon/getThucDonByWeek',
				type: 'GET',
				data: { selectedDate: selectedDate },
				success: function(response) {
					// Lưu trữ thực đơn tuần hiện tại
					if (response && response.length > 0) {
						// Lấy nhóm tuổi từ đối tượng đầu tiên
						var nhomTuoiId = response[0].maNhomTuoi;
						$('#nhomTuoi').val(nhomTuoiId).trigger('change');
					}
					currentWeekMenu = response;
					// Điền vào dữ liệu mới
					fillThucDonData(response);
				},
				error: function(xhr, status, error) {
					console.error("Lấy thực đơn theo tuần thất bại: ", xhr.responseText);
				}
			});
		}
	});

	//------------------- XỬ LÝ SỰ KIỆN THAY ĐỔI MÃ THỰC ĐƠN MẪU ------------------------------
	$('#thucDonMau').change(function() {
		var thucPhamMauId = $('#thucDonMau').val();
		if (thucPhamMauId) {
			$.get('/MockProject/quanly/thucdon/getThucDonMau', { maThucDonMau: thucPhamMauId })
				.done(function(data) {
					if (data && data.length > 0) {
						// Lấy nhóm tuổi từ đối tượng đầu tiên
						var nhomTuoiId = data[0].maNhomTuoi;
						$('#nhomTuoi').val(nhomTuoiId).trigger('change');
					}
					fillThucDonMauData(data);
				})
				.fail(function(xhr, status, error) {
					console.error("Lấy chi tiết thực đơn mẫu thất bại: ", xhr.responseText);
				});
		} else {
			// Nếu không chọn thực đơn mẫu, kiểm tra nếu có thực đơn tuần, hiển thị thực đơn tuần, nếu không hiển thị tất cả trở về rỗng
			if (currentWeekMenu && currentWeekMenu.length > 0) {
				fillThucDonData(currentWeekMenu);
			} else {
				for (var day = 2; day <= 7; day++) {
					$('#soLuongT' + day).val('0');
					$('#tongTienT' + day).val('0');
					for (var meal = 1; meal <= 4; meal++) {
						$('#suatAnMauTD' + day + '-' + meal).val('');
						$('#tableSuatAn' + day + '-' + meal).empty();
					}
				}
			}
		}
	});


	// Hàm điền dữ liệu vào từng bữa ăn của từng ngày
	function fillThucDonData(data) {
		// Chỉ reset tổng số suất ăn và tổng tiền, không xóa dữ liệu cũ
		tongSoSuatAn = 0;
		tongTien = 0;
		for (var day = 2; day <= 7; day++) {
			for (var meal = 1; meal <= 4; meal++) {
				currentSoLuong['day' + day]['TD' + meal] = 0;
				currentTongTien['day' + day]['TD' + meal] = 0;
				$('#soLuongT' + day).val('0');
				$('#tongTienT' + day).val('0');
			}
		}

		// Điền vào dữ liệu mới
		data.forEach(function(item) {
			var daySelector = dayMealMap[item.maNgay];
			var mealSelector = mealTypeMap[item.maBuaAn];
			if (daySelector && mealSelector) {
				var selector = daySelector + mealSelector;
				$(selector).val(item.maSuatAn).trigger('change');

				// Cập nhật tổng số suất ăn và tổng tiền
				var day = item.maNgay.slice(-2);
				var meal = mealSelector.slice(-1);
				var selectedMaSuatAn = item.maSuatAn;
				hienThiSuatAn(day, 'TD' + meal, selectedMaSuatAn, '#tableSuatAn' + day + '-' + meal, function(newSoLuong, newTongTien) {
					updateTongSoSuatAnAndTongTien(day, meal, newSoLuong, newTongTien);
				});
			}
		});

		// Cập nhật tổng số suất ăn và tổng tiền
		updateTongSoSuatAnAndTongTien();
	}



	function fillThucDonMauData(data) {
		data.forEach(function(item) {
			var daySelector = dayMealMap[item.maNgay];
			var mealSelector = mealTypeMap[item.maBuaAn];
			if (daySelector && mealSelector) {
				var selector = daySelector + mealSelector;
				$(selector).val(item.maSuatAn).trigger('change');
			}
		});
	}

	//------------------- KHỞI TẠO MỚI THỰC ĐƠN ------------------------------
	$('#reset').click(function(e) {
		// Reset tất cả các trường
		$('.is-invalid').removeClass('is-invalid'); // Loại bỏ lớp 'is-invalid' từ tất cả các trường

		// Xóa giá trị của select box
		$('#selectedDate').val('');
		$('#nhomTuoi').val('');
		$('#xoaMauChon').prop('checked', false);
		$('#chonLamMau').prop('checked', false);
		// Xóa giá trị và loại bỏ lớp 'is-invalid' từ tất cả các select box suất ăn
		for (var day = 2; day <= 7; day++) {
			$('#soLuongT' + day).val('0');
			$('#tongTienT' + day).val('0');
			for (var meal = 1; meal <= 4; meal++) {
				$('#suatAnMauTD' + day + '-' + meal).val('').removeClass('is-invalid');
				$('#tableSuatAn' + day + '-' + meal).empty(); // Xóa tất cả các hàng trong bảng
			}
		}
	});

	//------------------- XUẤT THÔNG TIN THỰC ĐƠN RA FILE EXCEL ------------------------------
	$('#xuatExcel').click(function(e) {
		// Kiểm tra xem đã nhập đầy đủ thông tin hay chưa
		var isValid = true;
		var errorShown = false;
		for (var day = 2; day <= 7; day++) {
			for (var meal = 1; meal <= 4; meal++) {
				var maSuatAn = $('#suatAnMauTD' + day + '-' + meal).val();
				if (maSuatAn === '') {
					isValid = false; // Nếu có suất ăn trống, đặt isValid là false
					if (!errorShown) {
						var isValid = true;
					}
					// Hiển thị viền đỏ cho select box suất ăn không được chọn
					$('#suatAnMauTD' + day + '-' + meal).addClass('is-invalid');
				} else {
					// Loại bỏ viền đỏ nếu suất ăn đã được chọn
					$('#suatAnMauTD' + day + '-' + meal).removeClass('is-invalid');
				}
			}
		}
		// Kiểm tra nhóm tuổi
		var selectedDate = $('#selectedDate').val();
		if (!selectedDate || selectedDate === '') {
			$('#selectedDate').addClass('is-invalid');
			isValid = false;
		} else {
			$('#selectedDate').removeClass('is-invalid');
		}

		// Kiểm tra nhóm tuổi
		var nhomTuoi = $('#nhomTuoi').val();
		if (!nhomTuoi || nhomTuoi === '') {
			$('#nhomTuoi').addClass('is-invalid');
			isValid = false;
		} else {
			$('#nhomTuoi').removeClass('is-invalid');
		}



		// Nếu đã nhập đầy đủ thông tin và hợp lệ, thực hiện xuất Excel
		if (isValid) {
			// Sử dụng xlsx-style thay vì xlsx
			 var workbook = XLSX.utils.book_new();
            var wsData = [];
            var wsName = 'Thuc_don';
            var uniqueMeals = {};
            var selectedDate = $('#selectedDate').val();
            var nhomTuoi = $('#nhomTuoi option:selected').text();

            // Thêm tiêu đề
            wsData.push(["Thực đơn Tuần", selectedDate]);
            wsData.push(["Nhóm tuổi", nhomTuoi]);

            var day = $('#menuModal').data('day');
            var mealNames = ['Ăn sáng - ăn nhẹ 1', 'Ăn trưa', 'Ăn xế (ăn nhẹ hoặc uống sữa)', 'Ăn chiều'];

            for (var day = 2; day <= 7; day++) {
                var dayTotalCost = parseFloat($('#tongTienT' + day).val().replace(/[^\d.-]/g, '')) || 0;
                var dayTotalQuantity = parseFloat($('#soLuongT' + day).val().replace(/[^\d.-]/g, '')) || 0;
                var dayLabelAdded = false;

                for (var meal = 1; meal <= 4; meal++) {
                    var maSuatAn = $('#suatAnMauTD' + day + '-' + meal).val();
                    var tenSuatAn = $('#suatAnMauTD' + day + '-' + meal + ' option:selected').text();

                    // Lấy thông tin chi tiết về thực phẩm từ data attributes của options
                    var thucPhamData = [];
                    $('#tableSuatAn' + day + '-' + meal + ' tr').each(function () {
                        var row = $(this);
                        var tenThucPham = row.find('td:eq(0)').text();
                        thucPhamData.push(tenThucPham);
                    });

                    var mealKey = "Thứ " + day + " - Bữa ăn " + meal + " - " + tenSuatAn;

                    // Nếu suất ăn này chưa tồn tại trong uniqueMeals, thêm vào wsData
                    if (!uniqueMeals[mealKey]) {
                        uniqueMeals[mealKey] = true;
                        var row = [];
                        if (!dayLabelAdded) {
                            row.push("Thứ " + day);
                            dayLabelAdded = true;
                        } else {
                            row.push("");
                        }
                        row.push(mealNames[meal - 1]);
                        row.push(tenSuatAn);
                        row.push(thucPhamData.join(', '));
                        wsData.push(row);
                    }
                }
                // Thêm tổng tiền và số lượng mỗi ngày vào cuối mỗi ngày
                wsData.push(["", "", "", "Số lượng", dayTotalQuantity]);
                wsData.push(["", "", "", "Tổng tiền", dayTotalCost.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })]);
            }

            // Tạo worksheet và thêm vào workbook
            var worksheet = XLSX.utils.aoa_to_sheet(wsData);

            // Apply styles to headers
            var headerStyle = {
                font: { bold: true },
                fill: { fgColor: { rgb: "FFFF00" } } // Yellow background
            };

            // Apply style using xlsx-style (if available) or skip
            if (worksheet['!cols']) {
                worksheet['A1'].s = headerStyle;
                worksheet['A2'].s = headerStyle;
                worksheet['B1'].s = headerStyle;
                worksheet['B2'].s = headerStyle;
            }

            XLSX.utils.book_append_sheet(workbook, worksheet, wsName);

            // Xuất file Excel
            XLSX.writeFile(workbook, 'ThucDon.xlsx');

		} else {
			toastr.error("Vui lòng chọn đầy đủ thông tin cho thực đơn", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	function showMealDetails(day) {
		var modalTitle = $('#menuModalLabel');
		var modalTableBody = $('#modalTableBody');
		modalTableBody.empty(); // Xóa dữ liệu cũ trước khi tải dữ liệu mới

		// Danh sách 4 bữa ăn trong ngày
		var mealNames = ['Ăn sáng - ăn nhẹ 1', 'Ăn trưa', 'Ăn xế (ăn nhẹ hoặc uống sữa)', 'Ăn chiều'];

		modalTitle.text('Thực đơn chi tiết thứ ' + day);

		var ajaxCalls = [];
		var mealRows = []; // Lưu trữ các hàng mới

		for (var meal = 0; meal < 4; meal++) {
			(function(meal) {
				var selectedMaSuatAn = $('#suatAnMauTD' + day + '-' + (meal + 1)).val();

				// Thực hiện yêu cầu AJAX để lấy thông tin suất ăn mẫu
				var ajaxCall = $.ajax({
					url: '/MockProject/quanly/thucdon/getSuatAnMau',
					type: 'GET',
					data: { maSuatAn: selectedMaSuatAn }
				});

				ajaxCalls.push(ajaxCall);

				ajaxCall.done(function(data) {
					if (data.length > 0) {
						const suatAn = data[0];
						const foodDetails = data.map(item => ({
							tenThucPham: item.tenThucPham,
							soLuong: item.soLuong,
							thanhTien: item.thanhTien
						}));

						// Tạo một hàng mới trong bảng thực đơn
						const mealRow = $('<tr></tr>');

						const foodNameCell = $('<td></td>');
						const foodQuantityCell = $('<td></td>');
						const foodAmountCell = $('<td></td>');

						foodDetails.forEach(food => {
							foodNameCell.append(`<div>${food.tenThucPham}</div>`);
							foodQuantityCell.append(`<div>${food.soLuong}</div>`);
							foodAmountCell.append(`<div>${food.thanhTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</div>`);
						});

						mealRow.append(`<td class="align-middle">${mealNames[meal]}</td>`);
						mealRow.append(`<td class="align-middle">${suatAn.tenSuatAn}</td>`);
						mealRow.append(foodNameCell);
						mealRow.append(foodQuantityCell);
						mealRow.append(foodAmountCell);
						mealRow.append(`<td class="align-middle" id="tongSoSuatAn${day}-${meal + 1}">${suatAn.saSoLuong}</td>`);
						mealRow.append(`<td class="align-middle" id="tongTien${day}-${meal + 1}">${suatAn.tongTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</td>`);

						mealRows[meal] = mealRow;
					}
				}).fail(function(xhr, status, error) {
					console.error("Lấy chi tiết suất ăn thất bại: ", xhr.responseText);
				});
			})(meal);
		}

		// Khi tất cả các yêu cầu AJAX hoàn tất
		$.when.apply($, ajaxCalls).then(function() {
			// Xóa dữ liệu cũ trong bảng
			modalTableBody.empty();

			// Thêm các hàng mới vào bảng theo đúng thứ tự
			mealRows.forEach(function(row) {
				if (row) {
					modalTableBody.append(row);
				}
			});

			// Tính tổng saSoLuong và tongTien
			let totalSaSoLuong = 0;
			let totalTongTien = 0;

			mealRows.forEach((row, index) => {
				totalSaSoLuong += parseInt($(`#tongSoSuatAn${day}-${index + 1}`).text());
				totalTongTien += parseInt($(`#tongTien${day}-${index + 1}`).text().replace(/\D/g, '')); // Loại bỏ ký hiệu tiền tệ
			});

			// Tạo một hàng tổng
			const totalRow = $('<tr></tr>');
			totalRow.append('<td class="align-middle" colspan="5"><strong>Tổng cộng</strong></td>');
			totalRow.append(`<td class="align-middle"><strong>${totalSaSoLuong}</strong></td>`);
			totalRow.append(`<td class="align-middle"><strong>${totalTongTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</strong></td>`);

			// Thêm hàng tổng vào cuối bảng
			modalTableBody.append(totalRow);

			// Hiển thị phương thức
			$('#menuModal').modal('show');
		});
	}

	// Trình xử lý sự kiện cho nút chi tiết
	$('[id^=chiTietButton]').click(function() {
		var day = $(this).data('day');
		showMealDetails(day);
	});

	// Gắn dữ liệu 'day' với giá trị thứ
	$('#chiTietButton2').data('day', 2);
	$('#chiTietButton3').data('day', 3);
	$('#chiTietButton4').data('day', 4);
	$('#chiTietButton5').data('day', 5);
	$('#chiTietButton6').data('day', 6);
	$('#chiTietButton7').data('day', 7);

	$('#closeTab').click(function() {
		Swal.fire({
			title: 'Xác nhận',
			text: "Bạn có chắc chắn muốn thoát chương trình không?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Xác nhận',
			cancelButtonText: 'Hủy'
		}).then((result) => {
			if (result.isConfirmed) {
				window.location.href = '/MockProject';

			}
		});
	})

});
