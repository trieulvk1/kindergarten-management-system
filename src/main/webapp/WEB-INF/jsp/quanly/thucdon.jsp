<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu management</title>
<link href="<c:url value="../resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="../resources/css/css/quanlythucdon.css" />" rel="stylesheet">
<!-- fontawesome -->
<link href="<c:url value="../resources/fontawesome/css/all.min.css" />" rel="stylesheet">
<link href="<c:url value="../resources/fontawesome/css/all.min.css" />" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Toastr JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</head>
<body>
	<div class="row border-dark" style="background-color: rgb(209, 209, 209); padding-left: 50px;">
		<!-- Tab bars -->
		<nav>
			<div class="nav nav-tabs " id="nav-tab" role="tablist">
				<a class="nav-link " href="suatan" data-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true" style="font-weight: bold; box-shadow: none;">Suất ăn dinh dưỡng</a> 
				<a class="nav-link active" href="thucdon" data-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false" style="font-weight: bold; box-shadow: none;">Thực đơn Ngày/Tuần</a>
			</div>
		</nav>
	</div>
	<div class="tab-content " id="nav-tabContent">
		<div class="row part2 mt-3 ">
			<div class="col-1"></div>
			<div class="col-2 pl-3">
				<h6 class="ml-5 pl-3" id="ba1">ĂN SÁNG - ĂN NHẸ 1</h6>
			</div>
			<div class="col-2">
				<h6 class="ml-5 pl-5" id="ba1">ĂN TRƯA</h6>
			</div>
			<div class="col-2 pl-2" id="ba1">
				<h6 class="ml-4">ĂN XẾ(ĂN NHẸ HOẶC UỐNG SỮA)</h6>
			</div>
			<div class="col-2 pl-5" id="ba1">
				<h6 class="ml-5">ĂN CHIỀU</h6>
			</div>
			<div class="col-3 pl-2">
				<div class="row">
					<div class="col">
						<h6>SỐ LƯỢNG</h6>
					</div>
					<div class="col pl-0">
						<h6>T/C CHI</h6>
					</div>
					<div class="col pl-0">
						<h6>ĐÃ CHI</h6>
					</div>
				</div>
			</div>
		</div>

		<div class="row ml-3 mr-3" style="border: solid 1px;">
			<div class="col-1">
				<div class="box ml-1">
					<label for="">THỨ 2</label>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD2-1" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3"
						id="tableSuatAn2-1">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD2-2" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3"
						id="tableSuatAn2-2">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD2-3" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3"
						id="tableSuatAn2-3">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD2-4" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3"
						id="tableSuatAn2-4">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-3">
				<div class="row pt-3 mr-1">
					<div class="col">
						<input type="text" class="form-control" id="soLuongT2"
							style="text-align: center; font-weight: bold;" value="0"
							readonly="readonly">
					</div>
					<div class="col">
						<input type="text" class="form-control text-danger"
							id="tongTienT2"
							style="text-align: center; font-weight: bold; border: solid 1px black"
							value="0" readonly="readonly">
					</div>
					<div class="col">
						<input type="text" class="form-control"
							style="text-align: center; font-weight: bold;" value="0"
							readonly="readonly">
					</div>
				</div>
				<div class="row pt-2 mr-1">
					<div class="col-12">
						<button class="form-control tdct" id="chiTietButton2">Thực đơn
							chi tiết thứ 2</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row ml-3 mr-3 mt-3" style="border: solid 1px;">
			<div class="col-1">
				<div class="box ml-1">
					<label for="">THỨ 3</label>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD3-1" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3" id="tableSuatAn3-1">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD3-2" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3" id="tableSuatAn3-2">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD3-3" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3" id="tableSuatAn3-3">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-2 p-3">
				<select name="" id="suatAnMauTD3-4" class=" form-control">
					<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
					<c:forEach items="${listSuatAn}" var="sa">
						<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
					</c:forEach>
				</select>
				<div class="scrollable-table pt-1">
					<table class="table table-striped table-bordered table3" id="tableSuatAn3-4">
						<tbody class="pt-2"></tbody>
					</table>
				</div>
			</div>
			<div class="col-3">
				<div class="row pt-3 mr-1">
					<div class="col">
						<input type="text" class="form-control" id="soLuongT3" style="text-align: center; font-weight: bold;" value="0" readonly="readonly">
					</div>
					<div class="col">
						<input type="text" class="form-control text-danger" id="tongTienT3" style="text-align: center; font-weight: bold; border: solid 1px black" value="0" readonly="readonly">
					</div>
					<div class="col">
						<input type="text" class="form-control" style="text-align: center; font-weight: bold;" value="0" readonly="readonly">
					</div>
				</div>
				<div class="row pt-2 mr-1">
					<div class="col-12">
						<button class="form-control tdct" id="chiTietButton3">Thực đơn chi tiết thứ 3</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row ml-3 mr-3 mt-3" style="border: solid 1px;">
		<div class="col-1">
			<div class="box ml-1">
				<label for="">THỨ 4</label>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD4-1" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn4-1">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD4-2" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn4-2">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD4-3" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn4-3">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD4-4" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn4-4">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-3">
			<div class="row pt-3 mr-1">
				<div class="col">
					<input type="text" class="form-control" id="soLuongT4"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control text-danger" id="tongTienT4"
						style="text-align: center; font-weight: bold; border: solid 1px black"
						value="0" readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
			</div>
			<div class="row pt-2 mr-1">
				<div class="col-12">
					<button class="form-control tdct" id="chiTietButton4">Thực đơn
						chi tiết thứ 4</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row ml-3 mr-3 mt-3" style="border: solid 1px;">
		<div class="col-1">
			<div class="box ml-1">
				<label for="">THỨ 5</label>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD5-1" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn5-1">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD5-2" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn5-2">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD5-3" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn5-3">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD5-4" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn5-4">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-3">
			<div class="row pt-3 mr-1">
				<div class="col">
					<input type="text" class="form-control" id="soLuongT5"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control text-danger" id="tongTienT5"
						style="text-align: center; font-weight: bold; border: solid 1px black"
						value="0" readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
			</div>
			<div class="row pt-2 mr-1">
				<div class="col-12">
					<button class="form-control tdct" id="chiTietButton5">Thực đơn
						chi tiết thứ 5</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row ml-3 mr-3 mt-3" style="border: solid 1px;">
		<div class="col-1">
			<div class="box ml-1">
				<label for="">THỨ 6</label>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD6-1" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn6-1">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD6-2" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn6-2">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD6-3" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn6-3">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD6-4" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn6-4">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-3">
			<div class="row pt-3 mr-1">
				<div class="col">
					<input type="text" class="form-control" id="soLuongT6"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control text-danger" id="tongTienT6"
						style="text-align: center; font-weight: bold; border: solid 1px black"
						value="0" readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
			</div>
			<div class="row pt-2 mr-1">
				<div class="col-12">
					<button class="form-control tdct" id="chiTietButton6">Thực đơn
						chi tiết thứ 6</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row ml-3 mr-3 mt-3" style="border: solid 1px;">
		<div class="col-1">
			<div class="box ml-1">
				<label for="">THỨ 7</label>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD7-1" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn7-1">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD7-2" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn7-2">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD7-3" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn7-3">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-2 p-3">
			<select name="" id="suatAnMauTD7-4" class=" form-control">
				<option value="" class="pl-4">-Chọn suất ăn mẫu</option>
				<c:forEach items="${listSuatAn}" var="sa">
					<option value="${sa.maSuatAn}">${sa.tenSuatAn}</option>
				</c:forEach>
			</select>
			<div class="scrollable-table pt-1">
				<table class="table table-striped table-bordered table3"
					id="tableSuatAn7-4">
					<tbody class="pt-2"></tbody>
				</table>
			</div>
		</div>
		<div class="col-3">
			<div class="row pt-3 mr-1">
				<div class="col">
					<input type="text" class="form-control" id="soLuongT7"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control text-danger" id="tongTienT7"
						style="text-align: center; font-weight: bold; border: solid 1px black"
						value="0" readonly="readonly">
				</div>
				<div class="col">
					<input type="text" class="form-control"
						style="text-align: center; font-weight: bold;" value="0"
						readonly="readonly">
				</div>
			</div>
			<div class="row pt-2 mr-1">
				<div class="col-12">
					<button class="form-control tdct" id="chiTietButton7">Thực đơn
						chi tiết thứ 7</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row ml-3 mt-3">
		<div class="col-3">
			<div class="row">
				<div class="col-6">
					<label for="">Chọn Tuần, Ngày</label> <input type="date"
						class="form-control col" id="selectedDate">

				</div>
				<div class="col-6">
					<label for="">Chọn Theo Nhóm Tuổi</label> <select name=""
						id="nhomTuoi" class=" form-control">
						<option value="" class="pl-4">- Chọn nhóm tuổi</option>
						<c:forEach items="${listNhomTuoi}" var="sa">
							<option value="${sa.maNhomTuoi}">${sa.tenNhomTuoi}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="row mt-1">
				<!-- Chọn mẫu thưc đơn -->
				<div class="col-6 d-flex align-items-center justify-content-center">
					<div class="d-flex align-items-center">
						<input type="checkbox" id="chonLamMau" class="mr-2"> <label
							for="checkbox1" class="mb-1">Chọn làm mẫu</label>
					</div>
				</div>	
				<div class="col-6 d-flex align-items-center justify-content-center">
					<div class="d-flex align-items-center">
						<input type="checkbox" id="xoaMauChon" class="mr-2"> <label
							for="checkbox2" class="mb-1">Xóa mẫu chọn</label>
					</div>
				</div>
			</div>
			<select name="" id="thucDonMau" class=" form-control mt-1">
				<option value="" class="pl-4">- THỰC ĐƠN MẪU</option>
				<c:forEach items="${listThucDon}" var="sa">
					<option value="${sa.maThucDonMau}">${sa.tenThucDonMau}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-1 mb-3">
			<label for="" style="margin-top: 35px;">Thực đơn mẫu</label>
		</div>

		<div class="col-5">
			<div class="row align-items-center" style="height: 30px;">
				<div class="col-12">
					<p class="error-msg10" style="font-size: 13px;"></p>
				</div>
			</div>
			<div class="row ">
				<div class="col-4">
					<!-- Khởi tạo thêm mới thực đơn -->
					<button type="button" class=" outline form-control" id="reset">
						<i class="far fa-sticky-note"></i> Khởi tạo thêm mới
					</button>
				</div>
				<div class="col">
					<!-- Cập nhật thực đơn -->
					<button type="button" class=" outline form-control" id="capNhat">
						<i class="far fa-edit"></i> Cập nhật
					</button>
				</div>
				<div class="col">
					<!-- Xuất báo cáo thực đơn -->
					<button type="button" class=" outline form-control" id="xuatExcel">Xuất
						báo cáo</button>
				</div>
				<div class="col mr-3">
					<!-- Đóng lại -->
					<button type="button" class=" outline form-control pr-3" id="closeTab">
						<i class="fas fa-times"></i> Đóng lại
					</button>
				</div>
			</div>
		</div>

	</div>
	<!-- Modal -->
	<div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="menuModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" style="margin-left: 250px"
						id="menuModalLabel">Thực đơn chi tiết</h5>
				</div>
				<div class="modal-body">
					<table class="table tablemodal table-bordered-outer table-striped" style="border: solid 1px black">
						  <thead class="thead-dark">
							<tr>
								<th width="20%" rowspan="2" class="align-middle">Bữa ăn</th>
								<th rowspan="2" class="align-middle">Tên suất</th>								
								<th width="40%" colspan="3">Thực phẩm</th>
								<th rowspan="2" class="align-middle">Số lượng</th>
								<th rowspan="2" class="align-middle">Tổng tiền</th>
								
							</tr>
							<tr>
								<th>Tên</th>
								<th>Số lượng</th>
								<th>Thành tiền</th>
							</tr>
						</thead>
						<tbody id="modalTableBody"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/popper.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="<c:url value="../resources/validate/thucdonjs.js" />"></script>
	<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript"> $('body').scrollspy({ target: '#navbar-example' })</script>

</body>
</html>
