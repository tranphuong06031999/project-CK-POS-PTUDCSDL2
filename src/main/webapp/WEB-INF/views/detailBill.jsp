<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Xuất phiếu</title>
        <style>
            body {
                margin-top: 20px;
                color: #484b51;
            }

            .text-secondary-d1 {
                color: #728299!important;
            }

            .page-header {
                margin: 0 0 1rem;
                padding-bottom: 1rem;
                padding-top: .5rem;
                border-bottom: 1px dotted #e2e2e2;
                display: -ms-flexbox;
                display: flex;
                -ms-flex-pack: justify;
                justify-content: space-between;
                -ms-flex-align: center;
                align-items: center;
            }

            .page-title {
                padding: 0;
                margin: 0;
                font-size: 1.75rem;
                font-weight: 300;
            }

            .brc-default-l1 {
                border-color: #dce9f0!important;
            }

            .ml-n1,
            .mx-n1 {
                margin-left: -.25rem!important;
            }

            .mr-n1,
            .mx-n1 {
                margin-right: -.25rem!important;
            }

            .mb-4,
            .my-4 {
                margin-bottom: 1.5rem!important;
            }

            hr {
                margin-top: 1rem;
                margin-bottom: 1rem;
                border: 0;
                border-top: 1px solid rgba(0, 0, 0, .1);
            }

            .text-grey-m2 {
                color: #888a8d!important;
            }

            .text-success-m2 {
                color: #86bd68!important;
            }

            .font-bolder,
            .text-600 {
                font-weight: 600!important;
            }

            .text-110 {
                font-size: 110%!important;
            }

            .text-blue {
                color: #478fcc!important;
            }

            .pb-25,
            .py-25 {
                padding-bottom: .75rem!important;
            }

            .pt-25,
            .py-25 {
                padding-top: .75rem!important;
            }

            .bgc-default-tp1 {
                background-color: rgba(121, 169, 197, .92)!important;
            }

            .bgc-default-l4,
            .bgc-h-default-l4:hover {
                background-color: #f3f8fa!important;
            }

            .page-header .page-tools {
                -ms-flex-item-align: end;
                align-self: flex-end;
            }

            .btn-light {
                color: #757984;
                background-color: #f5f6f9;
                border-color: #dddfe4;
            }

            .w-2 {
                width: 1rem;
            }

            .text-120 {
                font-size: 120%!important;
            }

            .text-primary-m1 {
                color: #4087d4!important;
            }

            .text-danger-m1 {
                color: #dd4949!important;
            }

            .text-blue-m2 {
                color: #68a3d5!important;
            }

            .text-150 {
                font-size: 150%!important;
            }

            .text-60 {
                font-size: 60%!important;
            }

            .text-grey-m1 {
                color: #7b7d81!important;
            }

            .align-bottom {
                vertical-align: bottom!important;
            }
        </style>
    </head>

    <body>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <div class="container-fluid">
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
                </div>
            </nav>
            <div class="page-content container">
                <div class="page-header text-blue-d2">
                    <h1 class="page-title text-secondary-d1">
                        Hóa đơn
                        <small class="page-info">
                            <i class="fa fa-angle-double-right text-80"></i>
                            Mã hóa đơn: ${hoadon.hoadon_id}
                        </small>
                    </h1>

                    <div class="page-tools">
                        <div class="action-buttons">
                            <a class="btn bg-white btn-light mx-1px text-95" href="#" data-title="Print">
                                <i class="mr-1 fa fa-print text-primary-m1 text-120 w-2"></i> Print
                            </a>
                        </div>
                    </div>
                </div>

                <div class="container px-0">
                    <div class="row mt-4">
                        <div class="col-12 col-lg-10 offset-lg-1">
                            <hr class="row brc-default-l1 mx-n1 mb-4" />

                            <div class="row">
                                <div class="col-sm-6">
                                    <div>
                                        <span class="text-sm text-grey-m2 align-middle">Mã khách hàng:</span>
                                        <span class="text-600 text-110 text-blue align-middle">${kh.makh}</span>
                                    </div>
                                    <div>
                                        <span class="text-sm text-grey-m2 align-middle">Tên khách hàng:</span>
                                        <span class="text-600 text-110 text-blue align-middle">${kh.tenkh}</span>
                                    </div>
                                    <div class="text-grey-m2">
                                        <div class="my-1"><i class="fa fa-phone fa-flip-horizontal text-secondary"></i> <b class="text-600">${kh.sodienthoai}</b></div>
                                    </div>
                                </div>
                                <!-- /.col -->

                                <div class="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">
                                    <hr class="d-sm-none" />
                                    <div class="text-grey-m2">
                                        <div class="mt-1 mb-2 text-secondary-m1 text-600 text-125">
                                            Hóa đơn
                                        </div>

                                        <div class="my-2"><i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span class="text-600 text-90">Mã hóa đơn:</span> ${hoadon.hoadon_id}</div>

                                        <div class="my-2"><i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span class="text-600 text-90">Ngày lập phiếu:</span> ${hoadon.ngaylap}</div>
                                    </div>
                                </div>
                                <!-- /.col -->
                            </div>
                            <div class="mt-4">
                                <div class="row text-600 text-white bgc-default-tp1 py-25">
                                    <div class="col-sm-1">ID</div>
                                    <div class="col-sm-2">Mã sản phẩm</div>
                                    <div class="col-sm-2">Số lượng</div>
                                    <div class="col-sm-2">Giá gốc</div>
                                    <div class="col-sm-2">Sau khuyến mãi</div>
                                    <div class="col-sm-1">Tổng</div>
                                    <div class="col-sm-2">Chiết khấu</div>
                                </div>
                                <c:forEach var="bill" items="${list}">
                                    <div class="text-95 text-secondary-d3">
                                        <div class="row mb-2 mb-sm-0 py-25">
                                            <div class="col-sm-1">${bill.chitiethoadon_id}</div>
                                            <div class="col-sm-2">${bill.masp}</div>
                                            <div class="col-sm-2">${bill.soluong}</div>
                                            <div class="col-sm-2">${bill.giagoc}</div>
                                            <div class="col-sm-2">${bill.giasaukhuyenmai}</div>
                                            <div class="col-sm-1">${bill.tongtien}</div>
                                            <div class="col-sm-2">${bill.chietkhau}</div>
                                        </div>
                                    </div>
                                </c:forEach>


                                <div class="row border-b-2 brc-default-l2"></div>
                                <div class="row mt-3">
                                    <div class="col-12 col-sm-7 text-grey-d2 text-95 mt-2 mt-lg-0">
                                        Extra note such as company or payment information...
                                    </div>

                                    <div class="col-12 col-sm-5 text-grey text-90 order-first order-sm-last">

                                        <div class="row my-2 align-items-center bgc-primary-l3 p-2">
                                            <div class="col-7 text-right">
                                                Tổng tiền
                                            </div>
                                            <div class="col-5">
                                                <span class="text-150 text-success-d3 opacity-2">${hoadon.tongtien}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>

                                <div>
                                    <span class="text-secondary-d1 text-105">Thank you for your business</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>