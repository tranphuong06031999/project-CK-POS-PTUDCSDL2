/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.GioHangController;
import controller.SanPhamController;
import entity.GioHangEntity;
import entity.SanPhamEntity;
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
import service.ISanPhamService;

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
    public void AddCart_Success_UnitTest() {
        String messenge = "Thêm giỏ hàng thành công";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);        
        gh.setSoluong(10);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void AddCart_Failed_UnitTest() {
        String messenge = "Thêm giỏ hàng thất bại";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);        
        gh.setSoluong(10000);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void AddCart_KHNotExist_UnitTest() {
        String messenge = "Không tìm thấy khách hàng";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1000);        
        gh.setSoluong(10);
        Mockito.when(service.createCart(gh)).thenReturn(messenge);
        ModelAndView model = controller.createCart(gh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void CartList_KH_UnitTest() {
        int makh = 1;
        ArrayList<GioHangEntity> lst = new ArrayList<GioHangEntity>();
        Mockito.when(service.getAll(makh)).thenReturn(lst);
        ModelAndView model = controller.cartList(makh,null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<GioHangEntity> actual = (ArrayList<GioHangEntity>) map.get("cartList"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }
    
    @Test
    public void CartList_CheckView_UnitTest() {
        int makh = 1;
        ArrayList<GioHangEntity> lst = new ArrayList<GioHangEntity>();
        Mockito.when(service.getAll(makh)).thenReturn(lst);
        ModelAndView model = controller.cartList(makh,null);
        String expect_view = "cartList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }
    
//    @Test
//    public void UpdateQuantity_Product_UnitTest() {
//        String messenge = "Cập nhật số lượng thành công";
//        GioHangEntity gh = new GioHangEntity();
//        gh.setMakh(1);
//        gh.setGia(28000);
//        gh.setSoluong(20);
//        Mockito.when(service.updateCart(gh)).thenReturn(messenge);
//        ModelAndView model = controller.updateCart(gh);
//        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
//        ArrayList<GioHangEntity> actual = (ArrayList<GioHangEntity>) map.get("cartList"); //Tên object gán cho ModelAndView
//        Assert.assertEquals(lst, actual);
//    }
    
    @Test
    public void DeleteProduct_Success_UnitTest() {
        String messenge = "Xóa giỏ hàng thành công";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        gh.setMagiohang(7);
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(messenge);
        ModelAndView model = controller.deleteProductCart(gh.getMagiohang(),gh.getMakh());
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
    @Test
    public void DeleteProduct_Failed_UnitTest() {
        String messenge = "Xóa giỏ hàng thất bại";
        GioHangEntity gh = new GioHangEntity();
        gh.setMakh(1);
        gh.setMagiohang(7);
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(messenge);
        ModelAndView model = controller.deleteProductCart(gh.getMagiohang(),gh.getMakh());
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }
    
//    @Test
//    public void UpdateQuantity_NhoHon1_UnitTest() {
//        String messenge = "Cập nhật số lượng thất bại";
//        GioHangEntity gh = new GioHangEntity();
//        gh.setMakh(1);
//        gh.setGia(28000);
//        gh.setSoluong(0);
//        Mockito.when(service.updateCart(gh)).thenReturn(messenge);
//        ModelAndView model = controller.updateCart(gh);
//        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
//        ArrayList<GioHangEntity> actual = (ArrayList<GioHangEntity>) map.get("cartList"); //Tên object gán cho ModelAndView
//        Assert.assertEquals(lst, actual);
//    }
}
