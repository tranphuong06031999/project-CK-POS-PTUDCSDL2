/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import api.GioHangApi;
import entity.GioHangEntity;
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
import service.IGioHangService;

/**
 *
 * @author NguyenThanhDat
 */
@RunWith(MockitoJUnitRunner.class)
public class GioHangApiTest {

    @InjectMocks
    GioHangApi api = new GioHangApi();

    @Mock
    IGioHangService service;

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<GioHangEntity> list = new ArrayList<>();
        GioHangEntity gh = new GioHangEntity();
        gh.setMagiohang(1);
        gh.setMasp(1);
        gh.setGia(28000);
        gh.setTensp("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        gh.setSoluong(1);
        gh.setGiatong(28000);
        list.add(gh);
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<GioHangEntity> actual = api.showCart();
        Assert.assertEquals(list.get(0).getTensp(), actual.get(0).getTensp());
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<GioHangEntity> list = new ArrayList<>();
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<GioHangEntity> actual = api.showCart();
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void AddCart_Success_UnitTest() {
        GioHangEntity gh = new GioHangEntity();
        gh.setMasp(1);
        gh.setGia(28000);
        gh.setTensp("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        gh.setSoluong(1);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Sucess");
        Mockito.when(service.createCart(gh)).thenReturn(message);
        HashMap<String, String> actual = api.createCart(gh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void AddCart_Error_UnitTest() {
        GioHangEntity gh = new GioHangEntity();
        gh.setMasp(9);
        gh.setGia(28000);
        gh.setTensp("assd");
        gh.setSoluong(1);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Error");
        Mockito.when(service.createCart(gh)).thenReturn(message);
        HashMap<String, String> actual = api.createCart(gh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void DeleteCart_Success_UnitTest() {
        GioHangEntity gh = new GioHangEntity();
        gh.setMagiohang(1);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Sucess");
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(message);
        HashMap<String, String> actual = api.deleteCart(gh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void DeleteCart_Error_UnitTest() {
        GioHangEntity gh = new GioHangEntity();
        gh.setMagiohang(10);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Error");
        Mockito.when(service.deleteCart(gh.getMagiohang())).thenReturn(message);
        HashMap<String, String> actual = api.deleteCart(gh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }
}
