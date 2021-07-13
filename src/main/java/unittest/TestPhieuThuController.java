/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.PhieuThuController;
import entity.KhachHangEntity;
import entity.PhieuThuEntity;
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
import service.IPhieuThuService;

/**
 *
 * @author THAIHUYNH
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPhieuThuController {

    @InjectMocks
    PhieuThuController controller;

    @Mock
    IPhieuThuService service;

    @Mock
    IKhachHangService khService;

    @Test
    public void GetOne_NonEmpty_UnitTest() {
        PhieuThuEntity pt = new PhieuThuEntity();
        pt.setMakh(1);
        pt.setMaphieuthu(1);
        pt.setNgaylap("2021-07-14");
        pt.setSodu(1000000);
        pt.setSotiennap(300000);
        pt.setTenkh("Khiếu Châu");
        Mockito.when(service.getOne(1)).thenReturn(pt);
        ModelAndView model = controller.getOne(1);
        PhieuThuEntity actual = (PhieuThuEntity) model.getModel().get("receipt");
        Assert.assertEquals(pt.getTenkh(), actual.getTenkh());
    }

    @Test
    public void GetOne_Empty_UnitTest() {
        PhieuThuEntity pt = new PhieuThuEntity();
        Mockito.when(service.getOne(1)).thenReturn(pt);
        ModelAndView model = controller.getOne(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        PhieuThuEntity actual = (PhieuThuEntity) map.get("receipt");
        Assert.assertEquals(pt, actual);
    }

    @Test
    public void GetOne_CheckView_UnitTest() {
        PhieuThuEntity pt = new PhieuThuEntity();
        Mockito.when(service.getOne(pt.getMaphieuthu())).thenReturn(pt);
        ModelAndView model = controller.getOne(pt.getMaphieuthu());
        String expect_view = "detailReceipt";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void SearchReceipt_Empty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = null;
        Mockito.when(service.searchPaging("Khiếu", 1)).thenReturn(lst);
        ModelAndView model = controller.searchReceipt("Khiếu", 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void SearchReceipt_NonEmpty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        PhieuThuEntity pt = new PhieuThuEntity();
        pt.setMakh(1);
        pt.setMaphieuthu(1);
        pt.setNgaylap("2021-07-14");
        pt.setSodu(10000000);
        pt.setSotiennap(3000000);
        pt.setTenkh("Khiếu Châu");
        lst.add(pt);
        Mockito.when(service.searchPaging("Khiếu", 1)).thenReturn(lst);
        ModelAndView model = controller.searchReceipt("Khiếu", 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list");
        Assert.assertEquals(lst.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void SearchReceipt_CheckView_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        Mockito.when(service.searchPaging("Khiếu", 1)).thenReturn(lst);
        ModelAndView model = controller.searchReceipt("Khiếu", 1);
        String expect_view = "receiptsList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        PhieuThuEntity pt = new PhieuThuEntity();
        pt.setMakh(1);
        pt.setMaphieuthu(1);
        pt.setNgaylap("2021-07-14");
        pt.setSodu(10000000);
        pt.setSotiennap(3000000);
        pt.setTenkh("Khiếu Châu");
        lst.add(pt);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list");
        Assert.assertEquals(lst.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void GetAll_CheckView_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        String expect_view = "receiptsList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
