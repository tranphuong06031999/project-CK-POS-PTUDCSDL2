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
        <title>Danh sách phiếu thu</title>
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
                        <li class="nav-item active">
                            <a class="nav-link" href="/customer">Khách Hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/product">Sản phẩm</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="/receipt/search" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Tìm kiếm phiếu thu..." aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </nav>
            <div class="container-fluid pt-3">
                <div class="table-title mb-2 d-flex justify-content-between">
                    <h5>Danh sách phiếu thu</h5>
                </div>
                <div class="row ">
                    <table class="table table-hover ">
                        <thead>
                            <tr>
                                <th scope="col ">Mã phiếu thu</th>
                                <th scope="col ">Mã khách hàng</th>
                                <th scope="col ">Tên khách hàng</th>
                                <th scope="col ">Số tiền nạp</th>
                                <th scope="col ">Số dư</th>
                                <th scope="col ">Ngày lập</th>
                                <th scope="col " style="width: 13% ">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${list == null}">
                                    <tr >
                                        <td colspan="7">Không tìm thấy phiếu thu</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="receipt" items="${list}">
                                        <tr>
                                            <td>${receipt.maphieuthu}</td>
                                            <td>${receipt.makh}</td>
                                            <td>${receipt.tenkh}</td>
                                            <td>
                                                <fmt:formatNumber type = "number" 
                                                                  groupingUsed = "false" value = "${receipt.sotiennap}"/>
                                            </td>
                                            <td>
                                                <fmt:formatNumber type = "number" 
                                                                  groupingUsed = "false" value = "${receipt.sodu}"/>
                                            </td>
                                            <td>${receipt.ngaylap}</td>
                                            <td>
                                                <a href="/receipt/${receipt.maphieuthu}" type="button" class="btn btn-outline-info">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-receipt" viewBox="0 0 16 16">
                                                    <path d="M1.92.506a.5.5 0 0 1 .434.14L3 1.293l.646-.647a.5.5 0 0 1 .708 0L5 1.293l.646-.647a.5.5 0 0 1 .708 0L7 1.293l.646-.647a.5.5 0 0 1 .708 0L9 1.293l.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .801.13l.5 1A.5.5 0 0 1 15 2v12a.5.5 0 0 1-.053.224l-.5 1a.5.5 0 0 1-.8.13L13 14.707l-.646.647a.5.5 0 0 1-.708 0L11 14.707l-.646.647a.5.5 0 0 1-.708 0L9 14.707l-.646.647a.5.5 0 0 1-.708 0L7 14.707l-.646.647a.5.5 0 0 1-.708 0L5 14.707l-.646.647a.5.5 0 0 1-.708 0L3 14.707l-.646.647a.5.5 0 0 1-.801-.13l-.5-1A.5.5 0 0 1 1 14V2a.5.5 0 0 1 .053-.224l.5-1a.5.5 0 0 1 .367-.27zm.217 1.338L2 2.118v11.764l.137.274.51-.51a.5.5 0 0 1 .707 0l.646.647.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.509.509.137-.274V2.118l-.137-.274-.51.51a.5.5 0 0 1-.707 0L12 1.707l-.646.647a.5.5 0 0 1-.708 0L10 1.707l-.646.647a.5.5 0 0 1-.708 0L8 1.707l-.646.647a.5.5 0 0 1-.708 0L6 1.707l-.646.647a.5.5 0 0 1-.708 0L4 1.707l-.646.647a.5.5 0 0 1-.708 0l-.509-.51z"/>
                                                    <path d="M3 4.5a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5zm8-6a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5z"/>
                                                    </svg> Xuất phiếu thu
                                                </a>
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
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/receipt/search?keyword=${keyword}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item" id="next"><a class="page-link" id="page-next" href="#">Next</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="page-item" id="previous"><a class="page-link" id="page-previous" href="#">Previous</a></li>
                                        <c:forEach var = "i" begin = "1" end = "${totalPage}">
                                        <li class="page-item" id="page${i}"><a class="page-link" href="/receipt?page=${i}">${i}</a></li>
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
                $(atag).attr("href", "/receipt/search?keyword=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/receipt?page=" + currentPage);
            }
        });
        $('#page-next').on('click', function (e) {
            var atag = e.target;
            var currentPage = Number($('#currentPage').val()) + 1;
            if (keyword !== "") {
                $(atag).attr("href", "/receipt/search?keyword=" + keyword + "&page=" + currentPage);
            } else {
                $(atag).attr("href", "/receipt?page=" + currentPage);
            }
        });
        $('#' + currentPage).addClass('active');
    </script>
</html>