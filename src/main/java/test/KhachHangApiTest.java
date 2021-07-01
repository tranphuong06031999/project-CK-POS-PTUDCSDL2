/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import api.KhachHangApi;
import entity.KhachHangEntity;
import java.util.ArrayList;
import java.util.HashMap;
import mapper.RequestPayinMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.IKhachHangService;
import org.junit.Assert;

/**
 *
 * @author NguyenThanhDat
 */
@RunWith(MockitoJUnitRunner.class)
public class KhachHangApiTest {

    @InjectMocks
    KhachHangApi api = new KhachHangApi();

    @Mock
    IKhachHangService service;

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        list.add(new KhachHangEntity(1, "admin2", 135000, "9876543210"));
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<KhachHangEntity> actual = api.getAll();
        Assert.assertEquals(list.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<KhachHangEntity> actual = api.getAll();
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void SearchCustomer_NonEmpty_UnitTest() {
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        list.add(new KhachHangEntity(2, "Andrew", 10000, "094554545"));
        Mockito.when(service.searchCustomer("2")).thenReturn(list);
        ArrayList<KhachHangEntity> actual = api.searchCustomer("2");
        Assert.assertEquals(list.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void SearchCustomer_Empty_UnitTest() {
        ArrayList<KhachHangEntity> list = null;
        Mockito.when(service.searchCustomer("Andrew")).thenReturn(list);
        ArrayList<KhachHangEntity> actual = api.searchCustomer("Andrew");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void AddCustomer_Success_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity(1, "Andrew", 10000, "094554545");
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "success");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        HashMap<String, String> actual = api.addCustomer(kh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void AddCustomer_Error_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity(3, "Andrew", 10000, "9876543210");
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Phone number already exists");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        HashMap<String, String> actual = api.addCustomer(kh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void UpdateCustomer_Success_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity(2, "Brother", 20000, "094554545");
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Success");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        HashMap<String, String> actual = api.addCustomer(kh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void UpdateCustomer_Error_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity(5, "Brother", 20000, "094554545");
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Not found customer");
        Mockito.when(service.addCustomer(kh)).thenReturn(message);
        HashMap<String, String> actual = api.addCustomer(kh);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void GetOne_NonEmpty_UnitTest() {
        KhachHangEntity kh = new KhachHangEntity(2, "admin2", 135000, "9876543210");
        Mockito.when(service.getOne(2)).thenReturn(kh);
        KhachHangEntity actual = api.getOne(2);
        Assert.assertEquals(kh.getTenkh(), actual.getTenkh());
    }

    @Test
    public void GetOne_Empty_UnitTest() {
        KhachHangEntity kh = null;
        Mockito.when(service.getOne(5)).thenReturn(kh);
        KhachHangEntity actual = api.getOne(5);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void PayIn_Success_UnitTest() {
        RequestPayinMapper mapper = new RequestPayinMapper();
        mapper.setMakh(1);
        mapper.setTiennap(200000);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Success");
        Mockito.when(service.payIn(1, 200000)).thenReturn(message);
        HashMap<String, String> actual = api.payIn(mapper);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }

    @Test
    public void PayIn_Error_UnitTest() {
        RequestPayinMapper mapper = new RequestPayinMapper();
        mapper.setMakh(5);
        mapper.setTiennap(20000);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Not found customer");
        Mockito.when(service.payIn(5, 20000)).thenReturn(message);
        HashMap<String, String> actual = api.payIn(mapper);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }
    
    
    @Test
    public void PayIn_AmoutLessThan100000_UnitTest() {
        RequestPayinMapper mapper = new RequestPayinMapper();
        mapper.setMakh(1);
        mapper.setTiennap(20000);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Amount must be greater than 100000");
        Mockito.when(service.payIn(1, 20000)).thenReturn(message);
        HashMap<String, String> actual = api.payIn(mapper);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }
}
