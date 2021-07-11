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
    public void Customer_payin_failure_UnitTest() {
        AbstractEntity entity = new AbstractEntity();
        String message = "payin failed";
        entity.setMakh(1);
        entity.setTiennap(10000);
        Mockito.when(service.payIn(entity.getMakh(), entity.getTiennap())).thenReturn(message);
        ModelAndView model = controller.payIn(entity);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_payin_success_UnitTest() {
        AbstractEntity entity = new AbstractEntity();
        String message = "payin success";
        Mockito.when(service.payIn(entity.getMakh(), entity.getTiennap())).thenReturn(message);
        ModelAndView model = controller.payIn(entity);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_UpdateCustomer_failure_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("090xxxx.xxxx");
        kh.setTenkh("Nguyen van A");
        kh.setSodu(0);
        String message = "update customer failed";
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_UpdateCustomer_success_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("090xxxx.xxxx");
        kh.setTenkh("Nguyen van A");
        kh.setSodu(0);
        String message = "update customer success";
        Mockito.when(service.updateCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.updateCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_AddCustomer_failure_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("090xxxx.xxxx");
        kh.setTenkh("Nguyen van A");
        kh.setSodu(0);
        String message = "add customer failed";
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.addCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_AddCustomer_success_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("090xxxx.xxxx");
        kh.setTenkh("Nguyen van A");
        kh.setSodu(0);
        String message = "add customer successful";
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        ModelAndView model = controller.addCustomer(kh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(message, actual);
    }

    @Test
    public void Customer_SearchCustomer_UnitTest() {
        String keyword = "";
        ArrayList<KhachHangEntity> lst = null;
        Mockito.when(service.searchPaging(keyword, 1)).thenReturn(lst);
        ModelAndView model = controller.searchCustomer(keyword, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void Customer_NonEmpty_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);
        kh.setLoaikh(1);
        kh.setSodienthoai("090xxxx.xxxx");
        kh.setTenkh("Nguyen van A");
        kh.setSodu(0);
        lst.add(kh);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, "");
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getMakh(), actual.get(0).getMakh());
    }

    @Test
    public void Customer_Empty_UnitTest() {
        ArrayList<KhachHangEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, "");
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<KhachHangEntity> actual = (ArrayList<KhachHangEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void Customer_CheckView_UnitTest() {
        ArrayList<KhachHangEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, "");
        String expect_view = "customersList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
