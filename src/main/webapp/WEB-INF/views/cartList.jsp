<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            /* Chrome, Safari, Edge, Opera */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
              -webkit-appearance: none;
              margin: 0;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer">Khách Hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/product">Sản phẩm</a>
                    </li>
                </ul>
            </div>
        </nav>
        <input type="hidden" id="message" value="${message}">
        <div class="container-fluid pt-3">
            <h5>Giỏ hàng</h5>
            <div class="row d-flex justify-content-between p-2">
                <div clas="col-md-10" style="width: 70% ">
                    <div class="table-title d-block justify-content-between mb-2">
                        <table class="table table-hover ">
                            <thead>
                                <tr>
                                    <th scope="col">Mã</th>
                                    <th scope="col">Mã KH</th>
                                    <th scope="col">Mã SP</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Giá(đ)</th>                                    
                                    <th scope="col">Giá tổng(đ)</th>
                                    <th scope="col" style="width: 16%">Số Lượng</th>
                                    <th scope="col" >Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${cartList == null}">
                                        <tr>
                                            <td colspan="8">Chưa có sản phẩm vào</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="product" items="${cartList}">
                                            <tr>
                                                <td>${product.magiohang}</td>
                                                <td>${product.makh}</td>
                                                <td>${product.masp}</td>
                                                <td>${product.tensp}</td>                                                  
                                                <td>
                                                    <fmt:formatNumber value="${product.gia}" pattern="#,###.##" var="pat" /> 
                                                    ${pat}
                                                </td>
                                                <td>
                                                    <fmt:formatNumber value="${product.giatong}" pattern="#,###.##" var="pat" /> 
                                                    ${pat}
                                                </td>
                                                <td>
                                                    <div class="d-flex">
                                                        <button class="btn btn-outline-secondary" id="reduce" data-quantity="${product.soluong}" data-price="${product.gia}" data-id="${product.magiohang}" data-makh="${product.makh}" data-masp="${product.masp}" onclick="reductionCart(this)">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-lg" viewBox="0 0 16 16">
                                                            <path d="M0 8a1 1 0 0 1 1-1h14a1 1 0 1 1 0 2H1a1 1 0 0 1-1-1z"/>
                                                            </svg>
                                                        </button>
                                                        <input class="form-control" type="number" name="quantity" onchange="quantityFunction(this)" data-price="${product.gia}" data-id="${product.magiohang}" data-makh="${product.makh}" data-masp="${product.masp}" value="${product.soluong}">
                                                        <button class="btn btn-outline-secondary" id="increase" data-quantity="${product.soluong}" data-price="${product.gia}" data-id="${product.magiohang}" data-makh="${product.makh}" data-masp="${product.masp}" onclick="increaseCart(this)">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                                            <path d="M8 0a1 1 0 0 1 1 1v6h6a1 1 0 1 1 0 2H9v6a1 1 0 1 1-2 0V9H1a1 1 0 0 1 0-2h6V1a1 1 0 0 1 1-1z"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a href="/cart/delete/${product.makh}/${product.magiohang}" class="btn btn-outline-danger">
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
                <div clas="col-md-2 ml-5">
                    <div class="card">
                        <div class="card-header text-center">
                            Thành tiền
                        </div>
                        <div class="card-body p-3"  style="width: 440px;">
                            <h5 class="card-title text-center" style="color: red;">
                                <fmt:formatNumber value="${totalPrice}" pattern="#,###.##" var="pat" /> 
                                 ${pat} đ
                            </h5>
                            <hr>
                            <div class="d-block">
                                <p>With supportimg</p>
                                <span style="float: right;" >50%</span>
                            </div>
                            <div class="d-block">
                                <p>With supportimg</p>
                                <span style="float: right;" >50%</span>
                            </div>
                            <div class="d-block">
                                <p>With supportimg</p>
                                <span style="float: right;" >50%</span>
                            </div>
                            <hr>
                            <c:choose>
                                <c:when test="${totalPrice != 0}">
                                    <a href="#" class="btn btn-primary">Thanh toán</a>
                                </c:when>    
                                <c:otherwise>
                                    <a href="" class="btn btn-secondary disabled">Thanh toán</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form method="post" id="hiddenForm">
            <input type="hidden" id="cartid" name="magiohang">
            <input type="hidden" id="price" name="gia">
            <input type="hidden" id="qty" name="soluong">            
            <input type="hidden" id="masp" name="masp">
            <input type="hidden" id="customerid" name="makh">
        </form>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js " integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN " crossorigin="anonymous "></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js " integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q " crossorigin="anonymous "></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js " integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl " crossorigin="anonymous "></script>
    <script>
        var message = $('#message').val();
        if (message !== "") {
            alert(message);
        }
        
        function quantityFunction(cart){
            var id = cart.getAttribute("data-id");
            var quantity = cart.value;            
            var price = cart.getAttribute("data-price");
            var makh = cart.getAttribute("data-makh");
            var masp = cart.getAttribute("data-masp");
            if(quantity >= 1 && quantity <= 10000){
                $("#cartid").val(id);
                $("#price").val(price);
                $("#qty").val(quantity);
                $('#masp').val(masp);
                $("#customerid").val(makh);
                $("#hiddenForm").attr('action', '/cart/update');
                $("#hiddenForm").submit();
            }
            else{
                if(quantity == 0){
                    alert("Số lượng phải lớn hơn 1, vui lòng nhập lại.");
                }
                else if(quantity > 10000){
                    alert("Số lượng chỉ được tối đa 10000, vui lòng nhập lại.");
                }
            }
        }
        
        function reductionCart(cart) {
            var id = cart.getAttribute("data-id");
            var quantity = cart.getAttribute("data-quantity");            
            var price = cart.getAttribute("data-price");
            var masp = cart.getAttribute("data-masp");
            var makh = cart.getAttribute("data-makh");
            if(quantity > 1){
                quantity = quantity - 1;
                $("#cartid").val(id);
                $("#price").val(price);
                $("#qty").val(quantity);
                $('#masp').val(masp);
                $("#customerid").val(makh);
                $("#hiddenForm").attr('action', '/cart/update');
                $("#hiddenForm").submit();
            }
        }
        
        function increaseCart(cart) {
            var id = cart.getAttribute("data-id");
            var quantity = cart.getAttribute("data-quantity");            
            var price = cart.getAttribute("data-price");
            var makh = cart.getAttribute("data-makh");
            var masp = cart.getAttribute("data-masp");
            if(quantity <= 10000){
                quantity++;
                $("#cartid").val(id);
                $("#price").val(price);
                $("#qty").val(quantity);
                $('#masp').val(masp);
                $("#customerid").val(makh);
                $("#hiddenForm").attr('action', '/cart/update');
                $("#hiddenForm").submit();
            }
        }
    </script>
</html>
