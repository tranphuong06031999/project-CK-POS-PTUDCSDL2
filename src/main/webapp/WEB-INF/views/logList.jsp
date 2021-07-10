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
        <title>Danh sách hóa đơn</title>
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
                            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/customer">Khách Hàng</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="/product">Sản phẩm</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="/log/search" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="makh" placeholder="Nhập mã khách hàng..." aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </nav>
            <div class="container-fluid pt-3">
                <div class="table-title mb-2 d-flex justify-content-between">
                    <h5>Lịch sử bán hàng</h5>
                </div>
                <div class="row ">
                    <table class="table table-hover ">
                        <thead>
                            <tr>
                                 <th scope="col ">Mã khách hàng</th>
                                <th scope="col ">Tên khách hàng</th>
                                <th scope="col ">Tổng tiền</th>
                                <th scope="col ">Ngày lập</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${list == null}">
                                    <tr >
                                        <td colspan="7">Không tìm thấy hóa đơn</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="log" items="${list}">
                                        <tr>
                                            <td>${log.makh}</td>
                                            <td>${log.tenkh}</td>
                                            <td>
                                                <fmt:formatNumber value="${log.tongtien}" pattern="#,###.##" var="pat" /> 
                                                ${pat}
                                            </td>
                                            <td>${log.ngaylap}</td>
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
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/log/search?makh=${keyword}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item" id="next"><a class="page-link" id="page-next" href="#">Next</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="page-item" id="previous"><a class="page-link" id="page-previous" href="#">Previous</a></li>
                                        <c:forEach var = "i" begin = "1" end = "${totalPage}">
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/log?page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item" id="next"><a class="page-link" id="page-next" href="#">Next</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
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
                $(atag).attr("href", "/log/search?makh=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/log?page=" + currentPage);
            }
        });
        $('#page-next').on('click', function (e) {
            var atag = e.target;
            var currentPage = Number($('#currentPage').val()) + 1;
            if (keyword !== "") {
                $(atag).attr("href", "/log/search?makh=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/log?page=" + currentPage);
            }
        });
        $('#' + currentPage).addClass('active');
    </script>
</html>