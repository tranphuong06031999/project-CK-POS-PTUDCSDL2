/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.LichBanHangController;
import entity.LichBanHangEnity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.ILichBanHangService;

/**
 *
 * @author THAIHUYNH
 */
@RunWith(MockitoJUnitRunner.class)
public class TestLichBanHangController {

    @InjectMocks
    LichBanHangController controller;

    @Mock
    ILichBanHangService service;

    @Test
    public void LogList_Search_UnitTest() {
        int makh = 1;
        ArrayList<LichBanHangEnity> lst = null;
        Mockito.when(service.getAllPagingSearch(1, makh)).thenReturn(lst);
        ModelAndView model = controller.getAllSearch(1, makh);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<LichBanHangEnity> actual = (ArrayList<LichBanHangEnity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }
    
    
    @Test
    public void LogList_NonEmpty_UnitTest() {
        List<LichBanHangEnity> lst = new ArrayList<>();
        LichBanHangEnity lbh = new LichBanHangEnity();
        lbh.setHoadon_id(1);
        lbh.setMakh(1);
        lbh.setTenkh("Nguyen Van A");
        lbh.setNgaylap("1/1/2021");
        lbh.setTongtien(1000000);
        lst.add(lbh);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);        
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<LichBanHangEnity> actual = (ArrayList<LichBanHangEnity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getMakh(), actual.get(0).getMakh());
    }

    @Test
    public void LogList_Empty_UnitTest() {
        ArrayList<LichBanHangEnity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<LichBanHangEnity> actual = (ArrayList<LichBanHangEnity>) map.get("list");
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void LogList_CheckView_UnitTest() {
        ArrayList<LichBanHangEnity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1);
        String expect_view = "logList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

}
