<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Danh sách sản phẩm</title>
    </head>

    <body>
        <div class="container-fluid pt-2">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/log">Lịch sử bán hàng <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/customer">Khách Hàng</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="/product">Sản phẩm</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="/product/search" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Tìm kiếm sản phẩm..." aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </nav>
            <input type="hidden" id="message" value="${message}">
            <div class="container-fluid pt-3">
                <div class="table-title d-flex justify-content-between mb-2">
                    <h5>Danh sách sản phẩm</h5>
                    <div>
                        <a href="/bill" class="btn btn-outline-info">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash" viewBox="0 0 16 16">
                            <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
                            <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"/>
                            </svg> Lịch sử giao dịch
                        </a>   
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addProductModal">
                            <svg xmlns="http://www.w3.org/2000/svg " width="16 " height="16 " fill="currentColor " class="bi bi-plus " viewBox="0 0 16 16 ">
                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z "/>
                            </svg> Thêm mới
                        </button>
                    </div>

                </div>
                <div class="row ">
                    <table class="table table-hover ">
                        <thead>
                            <tr>
                                <th scope="col">Mã</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col" style="width: 6%">Đơn vị</th>
                                <th scope="col" style="width: 7%">Số lượng</th>
                                <th scope="col">Ghi chú</th>
                                <th scope="col" style="width: 10%">Mua hàng</th>
                                <th scope="col" style="width: 13% ">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${list == null}">
                                    <tr >
                                        <td colspan="7">Không tìm thấy sản phẩm</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="product" items="${list}">
                                        <tr>
                                            <td>${product.maSP}</td>
                                            <td>${product.tenSP}</td>
                                            <td>${product.donVi}</td>
                                            <td>${product.soLuong}</td>
                                            <td>${product.ghiChu}</td>
                                            <td>
                                                <a href="/specifications/${product.maSP}"
                                                   role="button" class="btn btn-outline-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket" viewBox="0 0 16 16">
                                                    <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z"/>
                                                    </svg> Mua hàng
                                                </a>
                                            </td>
                                            <td>
                                                <button data-masp="${product.maSP}" data-tensp="${product.tenSP}" 
                                                        data-donvi="${product.donVi}"
                                                        data-soluong="${product.soLuong}" data-ghichu="${product.ghiChu}"
                                                        type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#updateProductModal">                
                                                    <svg xmlns="http://www.w3.org/2000/svg " width="16 " height="16 " fill="currentColor " class="bi bi-pencil-square " viewBox="0 0 16 16 ">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z "/>
                                                    <path fill-rule="evenodd " d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z "/>
                                                    </svg> Cập nhật
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="container-fluid d-flex justify-content-center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <input type="hidden" id="keyword" value="${keyword}">
                            <input type="hidden" id="currentPage" value="${currentPage}">
                            <input type="hidden" id="totalPage" value="${totalPage}">
                            <c:choose>
                                <c:when test="${keyword != null}">
                                    <li class="page-item" id="previous"><a class="page-link" id="page-previous" href="#">Previous</a></li>
                                        <c:forEach var = "i" begin = "1" end = "${totalPage}">
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/product/search?keyword=${keyword}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item" id="next"><a class="page-link" id="page-next" href="#">Next</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="page-item" id="previous"><a class="page-link" id="page-previous" href="#">Previous</a></li>
                                        <c:forEach var = "i" begin = "1" end = "${totalPage}">
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/product?page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item" id="next"><a class="page-link" id="page-next" href="#">Next</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm sản phẩm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/product/add" method="post" accept-charset="utf-8">
                            <div class="form-group">
                                <label for="tenSP">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="tenSP" name="tenSP" placeholder="Nhập tên" required minlength="3">
                            </div>
                            <div class="form-group">
                                <label for="donVi">Đơn vị</label>
                                <input type="text" class="form-control" id="donVi" name="donVi" placeholder="Nhập đơn vị" required minlength="1">
                            </div>
                            <div class="form-group">
                                <label for="soLuong">Số lượng</label>
                                <input type="number" class="form-control" id="soLuong" name="soLuong" placeholder="Nhập số lượng" required min="1">
                            </div>
                            <div class="form-group">
                                <label for="ghiChu">Ghi chú</label>
                                <input type="text" class="form-control" id="ghiChu" name="ghiChu" placeholder="Nhập ghi chú" required minlength="3">
                            </div>
                            <button class="btn btn-outline-info"> <svg xmlns="http://www.w3.org/2000/svg " width="16 " height="16 " fill="currentColor " class="bi bi-plus " viewBox="0 0 16 16 ">
                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z "/>
                                </svg> Thêm</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="updateProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cập nhập sản phẩm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/product/update" method="post" accept-charset="utf-8">
                            <input type="hidden" id="masp" name="maSP">
                            <div class="form-group">
                                <label for="tenSP">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="tenSP1" name="tenSP" placeholder="Nhập tên" required minlength="3">
                            </div>
                            <div class="form-group">
                                <label for="donVi">Đơn vị</label>
                                <input type="text" class="form-control" id="donVi1" name="donVi" placeholder="Nhập đơn vị" required minlength="1">
                            </div>
                            <div class="form-group">
                                <label for="soLuong">Số lượng</label>
                                <input type="number" class="form-control" id="soLuong1" name="soLuong" placeholder="Nhập số lượng" required min="1">
                            </div>
                            <div class="form-group">
                                <label for="ghiChu">Ghi chú</label>
                                <input type="text" class="form-control" id="ghiChu1" name="ghiChu" placeholder="Nhập ghi chú" required minlength="3">
                            </div>
                            <button id="btnUpdate" class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg " width="16 " height="16 " fill="currentColor " class="bi bi-pencil-square " viewBox="0 0 16 16 ">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z "/>
                                <path fill-rule="evenodd " d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z "/>
                                </svg> Cập nhật</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js " integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN " crossorigin="anonymous "></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js " integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q " crossorigin="anonymous "></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js " integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl " crossorigin="anonymous "></script>
    <script>
        var currentPage = 'page' + $('#currentPage').val();
        var keyword = $('#keyword').val();
        if ($('#currentPage').val() === '1') {
            $('#previous').addClass("disabled");
        }
        if ($('#currentPage').val() === $('#totalPage').val() || $('#totalPage').val() === '0') {
            $('#next').addClass("disabled");
        }
        $('#page-previous').on('click', function (e) {
            var atag = e.target;
            var currentPage = $('#currentPage').val() - 1;
            if (keyword !== "") {
                $(atag).attr("href", "/product/search?keyword=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/product?page=" + currentPage);
            }
        });
        $('#page-next').on('click', function (e) {
            var atag = e.target;
            var currentPage = Number($('#currentPage').val()) + 1;
            if (keyword !== "") {
                $(atag).attr("href", "/product/search?keyword=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/product?page=" + currentPage);
            }
        });
        $('#' + currentPage).addClass('active');
        var message = $('#message').val();
        if (message !== "") {
            alert(message);
        }
        $('#updateProductModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var masp = button.data('masp');
            var tensp = button.data('tensp');
            var donvi = button.data('donvi');
            var soluong = button.data('soluong');
            var ghichu = button.data('ghichu');
            $('#tenSP1').val(tensp);
            $('#masp').val(masp);
            $('#soLuong1').val(soluong);
            $('#donVi1').val(donvi);
            $('#ghiChu1').val(ghichu);
        })
    </script>
</html>
