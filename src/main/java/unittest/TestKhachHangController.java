/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.KhachHangController;
import entity.AbstractEntity;
import entity.KhachHangEntity;
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
import service.IKhachHangService;

/**
 *
 * @author THAIHUYNH
 */
@RunWith(MockitoJUnitRunner.class)
public class TestKhachHangController {

    @InjectMocks
    KhachHangController controller;

    @Mock
    IKhachHangService service;

    @Test
    public void PayIn_Failed_UnitTest() {
        String message = "Nạp tiền thất bại";
        AbstractEntity entity = new AbstractEntity();
        entity.setMakh(1);
        entity.setTiennap(-10000);
        Mockito.when(service.payIn(entity.getMakh(), entity.getTiennap())).thenReturn(message);
        ModelAndView model = controller.payIn(entity);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void PayIn_Success_UnitTest() {
        String message = "Nạp tiền thành công";
        AbstractEntity entity = new AbstractEntity();
        entity.setMakh(1);
        entity.setTiennap(10000);
        Mockito.when(service.payIn(entity.getMakh(), entity.getTiennap())).thenReturn(message);
        ModelAndView model = controller.payIn(entity);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void PayIn_RedirectView_UnitTest() {
        String message = "Nạp tiền thành công";
        AbstractEntity entity = new AbstractEntity();
        entity.setMakh(1);
        entity.setTiennap(10000);
        Mockito.when(service.payIn(entity.getMakh(), entity.getTiennap())).thenReturn(message);
        ModelAndView model = controller.payIn(entity);
        String expect_view = "redirect:/customer";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void UpdateCustomer_Failed_UnitTest() {
        String message = "Cập nhập khách hàng thất bại";
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setTenkh("Trần Văn A");
        kh.setSodu(-1000);
        //Số điện thoại đã tồn tại
        kh.setSodienthoai("0892310523");
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void UpdateCustomer_Success_UnitTest() {
        String message = "Cập nhập khách hàng thành công";
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setTenkh("Nguyen Van A");
        kh.setSodu(1000);
        kh.setSodienthoai("0932310523");
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void UpdateCustomer_RedirectView_UnitTest() {
        String message = "Cập nhập khách hàng thành công";
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setTenkh("Nguyen Van A");
        kh.setSodu(1000);
        kh.setSodienthoai("0892310523");
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        String expect_view = "redirect:/customer";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void AddCustomer_Success_UnitTest() {
        String message = "Thêm khách hàng thành công";
        KhachHangEntity kh = new KhachHangEntity();
        kh.setSodienthoai("0892310523");
        kh.setTenkh("Nguyễn Văn A");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.addCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void AddCustomer_Failed_UnitTest() {
        String message = "Thêm khách hàng thất bại";
        KhachHangEntity kh = new KhachHangEntity();
        //Số điện thoại đã tồn tại
        kh.setSodienthoai("0892310523");
        kh.setTenkh("Nguyến Văn A");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.addCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void AddCustomer_RedirectView_UnitTest() {
        String message = "Thêm khách hàng thất bại";
        KhachHangEntity kh = new KhachHangEntity();
        //Số điện thoại đã tồn tại
        kh.setSodienthoai("0892310523");
        kh.setTenkh("Nguyến Văn A");
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        String expect_view = "redirect:/customer";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void SearchCustomer_Empty_UnitTest() {
        String keyword = "";
        ArrayList<KhachHangEntity> lst = null;
        Mockito.when(service.searchPaging(keyword, 1)).thenReturn(lst);
        ModelAndView model = controller.searchCustomer(keyword, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void SearchCustomer_NonEmpty_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("0892310523");
        kh.setTenkh("Nguyến Văn A");
        kh.setSodu(10000000);
        lst.add(kh);
        Mockito.when(service.searchPaging("Văn A", 1)).thenReturn(lst);
        ModelAndView model = controller.searchCustomer("Văn A", 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getMakh(), actual.get(0).getMakh());
    }

    @Test
    public void SearchCustomer_CheckView_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        Mockito.when(service.searchPaging("Văn A", 1)).thenReturn(lst);
        ModelAndView model = controller.searchCustomer("Văn A", 1);
        String expect_view = "customersList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("0892310523");
        kh.setTenkh("Nguyến Văn A");
        kh.setSodu(10000000);
        lst.add(kh);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<KhachHangEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void GetAll_CheckView_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        String expect_view = "customersList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
