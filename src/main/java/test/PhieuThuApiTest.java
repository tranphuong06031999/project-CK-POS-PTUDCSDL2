/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import api.PhieuThuApi;
import entity.PhieuThuEntity;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import service.IPhieuThuService;

/**
 *
 * @author NguyenThanhDat
 */
@RunWith(MockitoJUnitRunner.class)
public class PhieuThuApiTest {

    @InjectMocks
    PhieuThuApi api = new PhieuThuApi();

    @Mock
    IPhieuThuService service;

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        list.add(new PhieuThuEntity(1, 1, "admin", 100000, 10000, "2021-06-28"));
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<PhieuThuEntity> actual = api.getAll();
        Assert.assertEquals(list.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        Mockito.when(service.getAll()).thenReturn(list);
        ArrayList<PhieuThuEntity> actual = api.getAll();
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void SearchReceipt_NonEmpty_UnitTest() {
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        list.add(new PhieuThuEntity(1, 1, "admin", 100000, 10000, "2021-06-28"));
        Mockito.when(service.searchReceipt("1")).thenReturn(list);
        ArrayList<PhieuThuEntity> actual = api.searchReceipt("1");
        Assert.assertEquals(list.get(0).getTenkh(), actual.get(0).getTenkh());
    }

    @Test
    public void SearchReceipt_Empty_UnitTest() {
        ArrayList<PhieuThuEntity> list = null;
        Mockito.when(service.searchReceipt("10")).thenReturn(list);
        ArrayList<PhieuThuEntity> actual = api.searchReceipt("10");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void GetOne_NonEmpty_UnitTest() {
        PhieuThuEntity kh = new PhieuThuEntity(1, 1, "admin", 100000, 10000, "2021-06-28");
        Mockito.when(service.getOne(1)).thenReturn(kh);
        PhieuThuEntity actual = api.getOne(1);
        Assert.assertEquals(kh.getTenkh(), actual.getTenkh());
    }

    @Test
    public void GetOne_Empty_UnitTest() {
        PhieuThuEntity kh = null;
        Mockito.when(service.getOne(10)).thenReturn(kh);
        PhieuThuEntity actual = api.getOne(10);
        Assert.assertEquals(null, actual);
    }
}
