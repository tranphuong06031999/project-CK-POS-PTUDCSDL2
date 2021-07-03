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
        <title>Thêm sản phẩm mới</title>
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
            <legend>Thêm sản phẩm mới</legend>
            <hr>
            <form id="add-new">
                <div class="row">
                    <div class="col-sm-6">
                         <div class="form-group">
                            <label for="name">Tên sản phẩm</label>
                            <input type="text" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Nhập tên sản phẩm...">
                            <!--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                        </div>
                        <div class="form-group">
                          <label for="type">Loại sản phẩm</label>
                          <input type="number" class="form-control" id="type" placeholder="Nhập loại..">
                        </div>
                        <div class="form-group">
                          <label for="cost">Giá gốc</label>
                          <input type="number" class="form-control" id="cost" placeholder="Nhập giá gốc..">
                        </div>
                    </div>
                    <div class="col-sm-6">
                         <div class="form-group">
                            <label for="unit">Đơn vị</label>
                            <input type="text" class="form-control" id="unit" aria-describedby="unitHelp" placeholder="Nhập đơn vị...">
                            <!--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                        </div>
                        <div class="form-group">
                          <label for="amount">Số lượng</label>
                          <input type="number" class="form-control" id="amount" placeholder="Nhập số lượng...">
                        </div>
                        <div class="form-group">
                          <label for="note">Ghi chú</label>
                          <input type="text" class="form-control" id="note" placeholder="Nhập ghi chú...">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Thêm mới</button>
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

            $(document).on('submit', '#add-new', function (e) {
              e.preventDefault();
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
                    url: '/18600336/api/product/add',
                    contentType : 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify({
                        "tenSP": name,
                        "loai": type,
                        "donVi": unit,
                        "soLuong": amount,
                        "gia": cost,
                        "ghiChu": note
                    }),
                    success: function (response) {
                        if(response.message == 'Success'){
                            $('#name').val('');
                            $('#type').val('');
                            $('#cost').val('');
                            $('#unit').val('');
                            $('#amount').val('');              
                            $('#note').val('');
                            alert("Thêm thành công!");
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
