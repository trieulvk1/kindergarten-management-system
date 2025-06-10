<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Meal management</title>
<link href="<c:url value="../resources/css/bootstrap.min.css" />" rel="stylesheet">
<!-- fontawesome -->
<link href="<c:url value="../resources/fontawesome/css/all.min.css" />" rel="stylesheet">
<link href="<c:url value="../resources/css/css/quanlysuatan.css" />" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">
<!-- jQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Toastr JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</head>
<body>
	<div class="row border-dark"
		style="background-color: rgb(209, 209, 209); padding-left: 50px;">
		<!-- Tab bars -->
		<nav>
			<div class="nav nav-tabs " id="nav-tab" role="tablist">
				<a class="nav-link active" href="suatan" data-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true" style="font-weight: bold; box-shadow: none;">Suất ăn dinh dưỡng</a> 
				<a class="nav-link" href="thucdon" data-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false" style="font-weight: bold; box-shadow: none;">Thực đơn Ngày/Tuần</a>
			</div>
		</nav>
	</div>

	<!-- Nhập nuất ăn dinh dưỡng -->
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active mb-3" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<div class="container-fluid mt-3 ml-3 mt-3">
				<form:form modelAttribute="suatAnForm" action="${pageContext.request.contextPath}/suatan/nhap" method="post">
					<div class="row">
						<div class="col-9 pr-1">
							<div class="row pr-5">
								<div class="col-12">
									<div class="row">
										<!-- Nhập tên suất ăn -->
										<div class="col-5">
											<div class="form-group row mt-3 pl-3">
												<label for="recipient-name" class="col-form-label col-3">Tên suất ăn</label>
												<form:input path="tenSuatAn" style="border: solid 1px;" name="tenSuatAn" type="text" class="form-control border-sl col-9 pl-2" id="tenSuatAn" />
											</div>
											<div class="row"  style="margin-top: -12px">
											<div class="col-3"></div>
												<div class="col-9">
													<span class="error-msg1"></span>
												</div>
											</div>
										</div>
										<!-- Nhập lứu tuổi -->
										<div class="col-4">
											<div class="form-group row mt-3">
												<label for="message-text" class="col-form-label col-5">Lứa tuổi</label>
												<form:select path="luaTuoi" name="luaTuoi" type="text" id="luaTuoi" class="form-control col-7 pl-2 " style="border: solid 1px;">
													<option value="Mẫu giáo nhỏ">Mẫu giáo</option>
													<option value="Mẫu giáo lớn">Mẫu giáo lớn</option>
													<option value="Thầy cô">Thầy cô</option>
												</form:select>
											</div>
											<div class="row ml-5" style="margin-top: -10px;">
												<div class="col justify-content-center d-flex">
													<span class="error-msg2"></span>
												</div>
											</div>
										</div>
										<!-- Nhập số lượng -->
										<div class="col-3">
											<div class="form-group row mt-3">
												<label for="message-text" class="col-form-label col-5">Số lượng</label>
												<form:input path="saSoLuong" name="saSoLuong" style="border: solid 1px;" type="number" id="saSoLuong" class="form-control col-7 pl-2" />
											</div>
											<div class="row" style="margin-top: -10px; margin-left: 70px;">
												<div class="col justify-content-center d-flex p-0">
													<span class="error-msg3 p-0"></span>
												</div>
											</div>
										</div>
									</div>
									<!-- Nhập nội dung -->
									<div class="row">
										<div class="col-5">
											<div class="form-group row mt-3 pl-3">
												<label for="message-text" class="col-form-label col-3" style="padding-right: 27px">Nội dung </label>
												<form:input path="noiDung" style="border: solid 1px;" name="noiDung" type="text" id="noiDung" class="form-control col-9 pl-2 custom-input border-sl"/>	
											</div>
											<div class="row" style="margin-top: -10px;">
											<div class="col-3"></div>
												<div class="col-9">
													<span class="error-msg4"></span>
												</div>
											</div>
										</div>
										<!-- Chọn suất ăn mẫu -->
										<div class="col-4">
									    <div class="form-group row mt-3">
									        <label for="message-text" class="col-form-label col-5">Suất ăn mẫu</label>
									        <select name="suatAnMau" style="border: solid 1px;" class="form-control border-sl col-7 pl-2" id="suatAnMau">
									            <option value="">-Danh sách suất ăn mẫu</option>
									            <c:forEach items="${listSuatAnMau}" var="sa">
									                <option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
									            </c:forEach>
									        </select>
									    </div>
									</div>

										<!-- Chọn loại suất ăn -->
										<div class="col-3">
											<div class="form-group row mt-3 ml-2">
												<form:select path="loaiSuatAn" style="border: solid 1px;" name="loaiSuatAn" type="text" id="loaiSuatAn" class="form-control pl-2 col-11 ml-4 border-sl">
													<option value="">Loại suất ăn</option>
													<option value="Thường">Thường</option>
													<option value="Liên hoan">Liên hoan</option>
												</form:select>
											</div>
											<div class="row" style="margin-left: 15px;margin-top: -10px;">
												<div class="col">
													<span class="error-msg5"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Bảng chi tiết thành phần dinh dưỡng -->
							<div class="container mt-3 border-table ">
								<h6 class="title-table">Chi tiết thành phần dinh dưỡng</h6>
								<div class="row">
									<div class="col-12  ">
										<div class="scrollable-table">
											<table class="table table-striped table-bordered pt-0 table1" id="tableFood">
												<thead class="bg-secondary">
													<tr>
														<th>ID</th>
														<th>Tên thực phẩm</th>
														<th>Đơn vị tính</th>
														<th>Số lượng</th>
														<th>Đơn giá</th>
														<th>Thành tiền</th>
														<th>Năng lượng(Kcal)</th>
														<th>Protid(g)</th>
														<th>Lipid(g)</th>
														<th>Glucic(g)</th>
														<th>Lấy từ kho</th>
													</tr>
												</thead>
												<tbody id="">

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>

							<!-- Thành phần tỉ lệ dinh dưỡng -->
							<div class="container mt-4 border-table " style="border: solid 2px black;">
								<h6 class="title-table">Thành phần tỉ lệ dinh dưỡng</h6>
								<table class="table table-striped table-bordered pt-0 pt-0 table2">
									<thead class="bg-secondary">
										<tr>
											<th colspan="2" style="text-align: center;">Năng lượng(Kcal)</th>
											<th colspan="2" style="text-align: center;">Đạm(P)-(g)</th>
											<th colspan="2" style="text-align: center;">Chất béo(L)-(g)</th>
											<th colspan="2" style="text-align: center;">Bột đường(G)-(g)</th>
											<th colspan="2" style="text-align: center;">Tỷ lệ P-L-G(%)</th>
											<th colspan="4" style="text-align: center;">Tỉ lệ đạt(%)</th>
										</tr>
									</thead>
									<tbody>
										<tr></tr>
										<tr class="thanhphan">
											<td >
												<h6>Suốt ăn</h6> <input type="text" id="calo" name="achievement" class="form-control" readonly="readonly" value="0" >
											</td>
											<td>
												<h6>T/C</h6> <input type="text" id="totalCalo" name="achievement" class="form-control"  readonly="readonly" value="0">
											</td>

											<td>
												<h6>Suốt ăn</h6> <input type="text" id="protid" name="protid" class="form-control" readonly="readonly" value="0">
											</td>
											<td>
												<h6>T/C</h6> <input type="text" id="totalProtid" name="achievement" class="form-control"  readonly="readonly" value="0">
											</td>

											<td>
												<h6>Suốt ăn</h6> <input type="text" id="lipid" name="lipid" class="form-control"  readonly="readonly" value="0">
											</td>
											<td>
												<h6>T/C</h6> <input type="text" id="totalLipid" name="achievement" class="form-control"  readonly="readonly" value="0">
											</td>

											<td>
												<h6>Suốt ăn</h6> <input type="text" id="lucid" name="lucid" class="form-control"  readonly="readonly" value="0">
											</td>
											<td>
												<h6>T/C</h6> <input type="text" id="totalLucid" name="achievement" class="form-control"  readonly="readonly" value="0">
											</td>
											<td>
												<h6>Suốt ăn</h6> <input type="text" id="ratioPLG" name="achievement" class="form-control p-0"  readonly="readonly" value="0%">
											</td>
											<td>
												<h6>T/C</h6> <input type="text" id="ratioPLGTC" name="achievement" class="form-control p-0"  readonly="readonly" value="0%">
											</td>
											<td>
												<h6>Calo</h6> <input type="text" id="ratioAchievementC" name="totalCalo" class="form-control"  readonly="readonly" value="0%">
											</td>

											<td>
												<h6>Protid</h6> <input type="text" id="ratioAchievementP" name="totalProtid" class="form-control"  readonly="readonly" value="0%">
											</td>

											<td>
												<h6>Lipid</h6> <input type="text" id="ratioAchievementL" name="totalLipid" class="form-control"  readonly="readonly" value="0%">
											</td>

											<td>
												<h6>Lucid</h6> <input type="text" id="ratioAchievementG" name="totalLucid" class="form-control"  readonly="readonly" value="0%">
											</td>
										</tr>
									</tbody>
								</table>

							</div>

						</div>
						<div class="col-3 pl-1">
							<div class="row mt-3 ml-0">
								<div class="col-11">
									<!-- Form thao tác suất ăn mẫu -->
									<div class="container mt-2 border-table pb-2">
										<h6 class="title-table">Thao tác suất ăn mẫu</h6>
										<div class="col-12 mt-0 mb-0">
											<div class="row">
												<div class="col-3 p-1">
													<button type="button" class=" outline form-control btn-sm" id="copySuatAn">Copy</button>
												</div>
												<div class="col-4 p-1">
													<button type="button" class=" outline form-control btn-sm" id="deleteSuatAn">Xóa suất ăn</button>
												</div>
												<div class="col-5 p-1">
													<div class="input-group mb-3">
														<div class="input-group-prepend">
														  <button class="outline form-control pl-1 pr-2 btn-sm" type="button" id="searchSuatAn"><i class="fas fa-search " style="width: 30px;"></i>Tìm kiếm</button>
														</div>
													  </div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-11 mt-2">
									<!-- Form chọn suất ăn mẫu -->
									<div class="container mt-3 border-table pb-2 mt-1">
										<h6 class="title-table">Chọn thực phẩm cho suất ăn</h6>
										<div class="row">
											<div class="col-12" style="padding: 0 25px;">
												<label for="" style="color: rgb(101, 101, 255);"> Nhóm thực phẩm</label> <select name="listNhomThucPham" id="listNhomThucPham" class="form-control" style="border: solid 1px;">
													<option value="">Chọn nhóm thực phẩm</option>
													<c:forEach items="${listNhomThucPham}" var="sa">
														<option value="${sa.maNhom}">${sa.tenNhom}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-12 mt-2" style="padding: 0 25px;">
												<label for=""> Thực phẩm</label> <select name="listNhomThucPham" id="thucPhamDtoList" class="form-control" style="border: solid 1px; width: 70%">
													<option value="">Chọn thực phẩm</option>
												</select>
											</div>
										</div>
										<div class="col-12 mt-3 mb-3" style="padding: 0 25px;">
											<div class="row">
												<div class="col btn-ctp pl-0">
													<button type="button" class="outline form-control" id="addThucPham">Thêm</button>
												</div>
												<div class="col btn-ctp">
													<button type="button" class=" outline form-control " id="deleteFood">Xóa</button>
												</div>
												<div class="col btn-ctp pr-0">
													<button type="button" class=" outline form-control" id="editButton">Sửa</button>
													<button type="button" class=" outline form-control" id="saveButton" style="display: none;">Cập Nhật</button>
												</div>
											</div>
										</div>
										<div class="form-group row mt-3">
											<label for="message-text" class="col-form-label col-6" style="text-align: center">Protid-Động vật</label> 
											<input type="text" class="form-control col-5" id="pDV"  readonly="readonly"/>
										</div>
										<div class="form-group row mt-3">
											<label for="message-text" class="col-form-label col-6" style="text-align: center">Protid-Thực vật</label> 
											<input type="text" class="form-control col-5" id="pTV"  readonly="readonly" />
										</div>
										<div class="form-group row mt-3">
											<label for="message-text" class="col-form-label col-6"style="text-align: center">Lipid-Động vật</label> 
											<input type="text" class="form-control col-5" id="lDV" readonly="readonly"/>
										</div>
										<div class="form-group row mt-3">
											<label for="message-text" class="col-form-label col-6" style="text-align: center">Lipid-Thực vật</label> 
											<input type="text" class="form-control col-5" id="lTV"  readonly="readonly"/>
										</div>
										<div class="form-group row mt-3">
											<label for="message-text" class="col-form-label col-6 text-danger" style="text-align: center">TỔNG TIỀN</label> 
											<input  type="text" class="form-control col-5 text-danger" id="totalMoney" value="0" style="font-weight: bold; border: solid 1px black;" readonly="readonly"  />
										</div>
										<!-- Khởi tạo thêm suất ăn -->
										<div class="form-group row pl-4 pr-4 mt-3">
											<button type="button" id="khoiTaoBtn" class=" form-control">Khởi
												tạo thêm mới suất ăn</button>
										</div>
										<!-- Thêm và xóa suất ăn -->
										<div class="col-12 mt-3 pl-2 pr-2 mb-3">
											<div class="row">
												<div class="col-7 btn-ctp pl-3">
													<button type="button" class=" outline form-control" id="updateSuatAn">Cập nhật</button>
												</div>
												<div class="col-5 btn-ctp pr-3">
													<button type="button" class=" outline form-control" id="exportToExcel">Xuất suất</button>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/popper.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="<c:url value="../resources/validate/suatanjs.js" />"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
</body>
</html>
