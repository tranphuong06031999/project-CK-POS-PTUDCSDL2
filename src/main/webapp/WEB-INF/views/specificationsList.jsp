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
                    <h5>Danh sách giá</h5>
                    <div>
                        <a href="/bill" class="btn btn-outline-info">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash" viewBox="0 0 16 16">
                            <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
                            <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"/>
                            </svg> Lịch sử giao dịch
                        </a>   
                        <button data-masp="${product.maSP}" type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addSpecificationModal">
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
                                <th scope="col">Mã sản phẩm</th>
                                <th scope="col">Đơn vị</th>
                                <th scope="col">Quy cách</th>
                                <th scope="col">Giá gốc</th>
                                <th scope="col" style="width: 10%">Thêm vô giỏ hàng</th>
                                <th scope="col" colspan="2" style="width: 13% ">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${list == null}">
                                    <tr >
                                        <td colspan="7">Rỗng</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="specification" items="${list}">
                                        <tr>
                                            <td>${specification.id}</td>
                                            <td>${specification.masp}</td>
                                            <td>${product.donVi}</td>
                                            <td>${specification.quycach}</td>
                                            <td>
                                                <fmt:formatNumber value="${specification.giagoc}" pattern="#,###.##" var="pat" /> 
                                                ${pat}
                                            </td>
                                            <td>
                                                <button data-masp="${product.maSP}" data-quycach="${specification.quycach}" 
                                                        data-giagoc="${specification.giagoc}" data-soluong ="${product.soLuong}"
                                                        data-toggle="modal" data-target="#addCartModal"
                                                        type="button" class="btn btn-outline-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                    </svg> Thêm vô giỏ hàng
                                                </button>
                                            </td>
                                            <td>
                                                <button data-id="${specification.id}" data-quycach="${specification.quycach}" 
                                                        data-giagoc="${specification.giagoc}" data-masp="${product.maSP}"
                                                        type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#updateSpecificationModal">                
                                                    <svg xmlns="http://www.w3.org/2000/svg " width="16 " height="16 " fill="currentColor " class="bi bi-pencil-square " viewBox="0 0 16 16 ">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z "/>
                                                    <path fill-rule="evenodd " d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z "/>
                                                    </svg> Cập nhật
                                                </button>
                                            </td>
                                            <td>
                                                <a href="/specifications/${specification.masp}/delete/${specification.id}" class="btn btn-outline-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                                                    </svg>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="addSpecificationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="addForm" action="" method="post" accept-charset="utf-8">
                            <div class="form-group">
                                <label for="quycach">Quy cách</label>
                                <input type="number" class="form-control" name="quycach" placeholder="Nhập quy cách" required min="1">
                            </div>
                            <div class="form-group">
                                <label for="giagoc">Giá gốc</label>
                                <input type="number" class="form-control" name="giagoc" placeholder="Nhập giá gốc" required min="10000">
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

        <div class="modal fade" id="updateSpecificationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cập nhập</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="updateForm" action="" method="post" accept-charset="utf-8">
                            <input type="hidden" id="id" name="id">
                            <div class="form-group">
                                <label for="quycach">Quy cách</label>
                                <input type="number" class="form-control" id="quycach" name="quycach" placeholder="Nhập quy cách" required min="1">
                            </div>
                            <div class="form-group">
                                <label for="giagoc">Giá gốc</label>
                                <input type="number" class="form-control" id="giagoc" name="giagoc" placeholder="Nhập giá gốc" required min="10000">
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
        <div class="modal fade" id="addCartModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm vô giỏ hàng</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/cart/add" method="post" accept-charset="utf-8">
                            <input type="hidden" id="masp" name="masp">
                            <div class="form-group">
                                <label for="makh">Mã khách hàng</label>
                                <input type="number" class="form-control" id="makh" name="makh" placeholder="Nhập mã khách hàng" required min="1">
                            </div>
                            <input type="hidden" id="soluong" name="soluong">
                            <input type="hidden" id="giatong" name="giatong"> 
                            <button id="btnAddCart" class="btn btn-outline-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                </svg> Thêm vô giỏ hàng</button>
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
        var message = $('#message').val();
        if (message !== "") {
            alert(message);
        }
        $('#addSpecificationModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var masp = button.data('masp');
            $('#addForm').attr('action', '/specifications/' + masp + '/add');
        });
        $('#updateSpecificationModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var masp = button.data('masp');
            var quycach = button.data('quycach');
            var giagoc = button.data('giagoc');
            var id = button.data('id');
            $('#id').val(id);
            $('#updateForm').attr('action', '/specifications/' + masp + '/update');
            $('#giagoc').val(giagoc);
            $('#quycach').val(quycach);
        });

        $('#addCartModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var masp = button.data('masp');
            var quycach = button.data('quycach');
            var giagoc = button.data('giagoc');
            var soluongton = button.data('soluong');
            if (soluongton < quycach) {
                $('#btnAddCart').text('Số lượng tồn không đủ');
                $('#btnAddCart').attr('disabled', true);
                $('#makh').attr('disabled', true);
            } else {
                $('#masp').val(masp);
                $('#soluong').val(quycach);
                $('#giatong').val(giagoc);
            }
        });
    </script>
</html>
