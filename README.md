0. Validate mọi thứ có thể
1. Hiển thị danh sách khách hàng
- url: /api/customer
- method: get
- output: mảng json chứa object customer

2. Tìm khách hàng (theo tên hoặc mã)
-url: /api/customer/search
-method: get
-input: keyword
-output: mảng json chứa object customer

3. Thêm khách hàng
-url: /api/customer/add
-method: post
-input: tenkh, sodienthoai
-output: message, redirect

4. Cập nhập khách hàng
-url: /api/customer/update
-method: put
-input: makh, tenkh, sodu, sodienthoai
-output: message, redirect

5. Hiển thị 1 khách hàng
-url: /api/customer/{id}
-method: get
-output: object customer (ko phải mảng, trả về 1 object)

6. Nạp tiền
-url: /api/customer/pay-in
-method: put
-input: makh, tiennap
-output: message, redirect

7. Hiển thị danh sách phiếu thu
-url: /api/receipt
-method: get
-output: mảng json chứa object phiếu thu

8. Tìm phiếu thu (theo tên hoặc mã)
-url: /api/receipt/search
-method: get
-input: keyword
-output: mảng json chứa object phiếu thu

9. Hiển thị 1 phiếu thu
-url: /api/receipt/{id}
-method: get
-output: object phiếu thu (ko phải mảng, trả về 1 object)

10. Hiển thị giỏ hàng
-url: /api/cart
-method: get
-output mảng json chứa object giỏ hàng

11. Thêm giỏ hàng
-url: /api/cart/add
-method: post
-input: masp,tensp,gia,soluong
-output: message

12. Xóa giỏ hàng
-url: /api/cart/delete
-method: delete
-input: magiohang
-output: message
