/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.GioHangController;
import entity.GioHangEntity;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.IGioHangService;

/**
 *
 * @author Trần Đinh Phương
 */
@RunWith(MockitoJUnitRunner.class)
public class TestGioHangController {

    @InjectMocks
    GioHangController controller = new GioHangController();

    @Mock
    IGioHangService service;

    @Test
    public void CreateCart_Success_UnitTest() {
        String messenge = "Thêm giỏ hàng thành công";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        gh.setMasp(1);
        gh.setTensp("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        gh.setSoluong(10);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void CreateCart_Failed_UnitTest() {
        String messenge = "Thêm giỏ hàng thất bại";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        gh.setMasp(1);
        gh.setTensp("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        //Vượt quá số lượng tồn
        gh.setSoluong(10001);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void CreateCart_CustomerNotExist_UnitTest() {
        String messenge = "Không tìm thấy khách hàng";
        GioHangEntity gh = new GioHangEntity();
        //Mã khách hàng không tồn tại
        gh.setMakh(10000);
        gh.setMasp(1);
        gh.setTensp("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        gh.setSoluong(10);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void CreateCart_RedirectView_UnitTest() {
        String messenge = "Thêm giỏ hàng thất bại!";
        GioHangEntity gh = new GioHangEntity();
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        String expect_view = "redirect:/specifications/" + 0;
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void CartList_Empty_UnitTest() {
        int makh = 1;
        ArrayList<GioHangEntity> lst = null;
        Mockito.when(service.getAll(makh)).thenReturn(lst);
        ModelAndView model = controller.cartList(makh, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<GioHangEntity> actual = (ArrayList<GioHangEntity>) map.get("cartList"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void CartList_CheckView_UnitTest() {
        int makh = 1;
        ArrayList<GioHangEntity> lst = new ArrayList<>();
        Mockito.when(service.getAll(makh)).thenReturn(lst);
        ModelAndView model = controller.cartList(makh, null);
        String expect_view = "cartList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void CartList_NonEmpty_UnitTest() {
        int makh = 1;
        ArrayList<GioHangEntity> lst = new ArrayList<>();
        GioHangEntity gh = new GioHangEntity();
        gh.setMagiohang(1);
        gh.setMakh(1);
        gh.setMasp(1);
        gh.setSoluong(10);
        gh.setGiatong(380000);
        lst.add(gh);
        Mockito.when(service.getAll(makh)).thenReturn(lst);
        ModelAndView model = controller.cartList(makh, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<GioHangEntity> actual = (ArrayList<GioHangEntity>) map.get("cartList"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void DeleteProductCart_Success_UnitTest() {
        String messenge = "Xóa giỏ hàng thành công";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        gh.setMagiohang(7);
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(messenge);
        ModelAndView model = controller.deleteProductCart(gh.getMagiohang(), gh.getMakh());
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void DeleteProductCart_Failed_UnitTest() {
        String messenge = "Xóa giỏ hàng thất bại";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        //Mã giỏ hàng không tồn tại
        gh.setMagiohang(1000);
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(messenge);
        ModelAndView model = controller.deleteProductCart(gh.getMagiohang(), gh.getMakh());
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void DeleteProductCart_RedirectView_UnitTest() {
        String messenge = "Xóa giỏ hàng thất bại!";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        //Mã giỏ hàng không tồn tại
        gh.setMagiohang(1000);
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(messenge);
        ModelAndView model = controller.deleteProductCart(gh.getMagiohang(), gh.getMakh());
        String expect_view = "redirect:/cart/" + gh.getMakh();
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }
    
    @Test
    public void Checkout_SotienDuNhoHonAm30tr_Success_UnitTest() {
        int makh = 1;
        int maximumDebt = -30000000;
        NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
        String formatPrìce = FORMATTER.format(maximumDebt);
        String messenge = "Thanh toán thất bại, bạn đã nợ vượt quá "+ formatPrìce+", vui lòng nạp thêm tiền!";
        Mockito.when(service.checkoutCart(makh)).thenReturn(messenge);
        ModelAndView model = controller.checkoutCart(makh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void Checkout_SotienDuLonHonAm30tr_Success_UnitTest() {
        int makh = 1;
        int maximumDebt = -30000000;
        NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
        String formatPrìce = FORMATTER.format(maximumDebt);
        String messenge = "Thanh toán thất bại, Đơn hàng này đã làm vượt quá số nợ "+ formatPrìce+", vui lòng nạp thêm tiền!";
        Mockito.when(service.checkoutCart(makh)).thenReturn(messenge);
        ModelAndView model = controller.checkoutCart(makh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void Checkout_Success_UnitTest() {
        int makh = 1;
        String messenge = "Bạn đã thanh toán đơn hàng thành công";
        Mockito.when(service.checkoutCart(makh)).thenReturn(messenge);
        ModelAndView model = controller.checkoutCart(makh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
}
