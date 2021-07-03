<%-- 
    Document   : addNewProduct
    Created on : Jul 2, 2021, 1:01:55 PM
    Author     : Trần Đinh Phương
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa sản phẩm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/18600336/api/products-list">Danh sách sản phẩm</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
        <div class="container p-3">
            <legend>Sửa sản phẩm</legend>
            <hr>
            <form id="edit-product">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="hidden" id="masp"value="${sanpham.maSP}">
                        <div class="form-group">
                            <label for="name">Tên sản phẩm</label>
                            <input type="text" class="form-control" id="name" aria-describedby="nameHelp" value="${sanpham.tenSP}">
                            <!--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                        </div>
                        <div class="form-group">
                          <label for="type">Loại sản phẩm</label>
                          <input type="number" class="form-control" id="type" placeholder="Nhập loại.." value="${sanpham.loai}">
                        </div>
                        <div class="form-group">
                          <label for="cost">Giá gốc</label>
                          <input type="number" class="form-control" id="cost" placeholder="Nhập giá gốc.." value="${sanpham.gia}">
                        </div>
                    </div>
                    <div class="col-sm-6">
                         <div class="form-group">
                            <label for="unit">Đơn vị</label>
                            <input type="text" class="form-control" id="unit" aria-describedby="unitHelp" placeholder="Nhập đơn vị..." value="${sanpham.donVi}">
                            <!--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                        </div>
                        <div class="form-group">
                          <label for="amount">Số lượng</label>
                          <input type="number" class="form-control" id="amount" placeholder="Nhập số lượng..." value="${sanpham.soLuong}">
                        </div>
                        <div class="form-group">
                          <label for="note">Ghi chú</label>
                          <input type="text" class="form-control" id="note" placeholder="Nhập ghi chú..." value="${sanpham.ghiChu}">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Thay đổi</button>
          </form>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        (function ($) {
            "use strict";

            jQuery(document).ready(function ($) {
//                console.log('hello');
            });

            $(document).on('submit', '#edit-product', function (e) {
              e.preventDefault();
              const masp = $('#masp').val();
              const name = $('#name').val();
              const type = $('#type').val();
              const cost = $('#cost').val();
              const unit = $('#unit').val();
              const amount = $('#amount').val();              
              const note = $('#note').val();

              if(name == '' || type == '' || cost == '' || unit == '' || amount == '' || note == ''
              || !name || !type || !cost || !unit || !amount || !note){
                alert("Vui lòng nhập đủ thông tin");
              }
              else{
                $.ajax({
                    type: "POST",
                    url: '/18600336/api/product/edit',
                    contentType : 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify({
                        "maSP": masp,
                        "tenSP": name,
                        "loai": type,
                        "donVi": unit,
                        "soLuong": amount,
                        "gia": cost,
                        "ghiChu": note
                    }),
                    success: function (response) {
                        if(response.sanpham){
                            $('#masp').val(`${response.sanpham.maSP}`);
                            $('#name').val(`${response.sanpham.tenSP}`);
                            $('#type').val(`${response.sanpham.loai}`);
                            $('#cost').val(`${response.sanpham.gia}`);
                            $('#unit').val(`${response.sanpham.donVi}`);
                            $('#amount').val(`${response.sanpham.soLuong}`);              
                            $('#note').val(`${response.sanpham.ghiChu}`);
                            alert("Sửa thành công!");
                        }
               
                    },
                    error: function (res) {
                       console.log(res.responseJSON);
                    }
                });
              }
        
            });

        }(jQuery));
    </script>
</html>
