/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.HoaDonController;
import entity.HoaDonEntity;
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
import service.IHoaDonService;
import service.IKhachHangService;

/**
 *
 * @author TIEN TAI
 */
@RunWith(MockitoJUnitRunner.class)
public class TestHoaDonController {

    @InjectMocks
    HoaDonController controller;

    @Mock
    IHoaDonService service;

    @Mock
    IKhachHangService khService;

    @Test
    public void getAllCTHD_UnitTest() {
        ArrayList<HoaDonEntity> lst = new ArrayList<>();

        HoaDonEntity hd = new HoaDonEntity();
        hd.setHoadon_id(1);
        hd.setMakh(1);

        lst.add(hd);

        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);

        Mockito.when(khService.getOne(service.getOne(hd.getHoadon_id()).getMakh())).thenReturn(kh);
        Mockito.when(service.searchPaging(kh.getMakh(), 1)).thenReturn(lst);
        ModelAndView model = controller.getAllCTHD(hd.getMakh());
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<HoaDonEntity> actual = (ArrayList<HoaDonEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void Search_UnitTest() {
        ArrayList<HoaDonEntity> lst = new ArrayList<>();

        HoaDonEntity hd = new HoaDonEntity();
        hd.setHoadon_id(1);
        hd.setMakh(1);

        lst.add(hd);

        KhachHangEntity kh = new KhachHangEntity();
        kh.setMakh(1);

        Mockito.when(service.searchPaging(kh.getMakh(), 1)).thenReturn(lst);
        ModelAndView model = controller.search(kh.getMakh(), 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<HoaDonEntity> actual = (ArrayList<HoaDonEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void NonEmpty_UnitTest() {
        ArrayList<HoaDonEntity> lst = new ArrayList<>();
        HoaDonEntity hd = new HoaDonEntity();
        hd.setMakh(1);
        lst.add(hd);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<HoaDonEntity> actual = (ArrayList<HoaDonEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getMakh(), actual.get(0).getMakh());
    }

    @Test
    public void Empty_UnitTest() {
        ArrayList<HoaDonEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<HoaDonEntity> actual = (ArrayList<HoaDonEntity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void CheckView_UnitTest() {
        ArrayList<HoaDonEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        String expect_view = "billsList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
