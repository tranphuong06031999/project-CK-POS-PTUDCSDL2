/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.KhachHangController;
import controller.PhieuThuController;
import entity.AbstractEntity;
import entity.PhieuThuEntity;
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

    @Test
    public void Bill_NonEmpty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        PhieuThuEntity pt = new PhieuThuEntity();
        pt.setMakh(1);
        pt.setMaphieuthu(0);
        pt.setNgaylap("1/1/2021");
        pt.setSotiennap(0);
        pt.setSodu(0);
        pt.setTenkh("Nguyen Van A");
        lst.add(pt);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getMakh(), actual.get(0).getMakh());
    }

    @Test
    public void Bill_Empty_UnitTest() {
        ArrayList<PhieuThuEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<PhieuThuEntity> actual = (ArrayList<PhieuThuEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void Bill_CheckView_UnitTest() {
        ArrayList<PhieuThuEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        String expect_view = "customersList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
