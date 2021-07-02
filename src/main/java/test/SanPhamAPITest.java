/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import api.SanPhamAPI;
import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import repository.ISanPhamRepository;
import service.IPhieuThuService;

/**
 *
 * @author Trần Đinh Phương
 */
@RunWith(MockitoJUnitRunner.class)
public class SanPhamAPITest {
    
    @InjectMocks
    SanPhamAPI api = new SanPhamAPI();

    @Mock
    ISanPhamRepository service;
       
    @Test
    public void AddCustomer_Success_UnitTest() {
        SanPhamEntity sp = new SanPhamEntity(11, "Phân Bón Npk 20-20-15 đa năng 3 màu", 1, "Bao", 100, 46000, "tránh xa tầm tay trẻ em bảo quản nới thoáng mát Sản phẩm nếu đã khui mà chưa sử dụng hết nên bỏ vào hộp đậy kín");
        HashMap<String, String> message = new HashMap<>();
        boolean added = true;
        Mockito.when(service.create(sp)).thenReturn(added);
        HashMap<String, String> actual = api.addProduct(sp);
        Assert.assertEquals(message.get("message"), actual.get("message"));
    }
    
    @Test
    public void EditCustomer_Success_UnitTest() {
        SanPhamEntity sp = new SanPhamEntity(9, "Phân Bón Npk 20-20-15 đa năng 3 màu", 1, "Bao", 100, 46000, "tránh xa tầm tay trẻ em bảo quản nới thoáng mát Sản phẩm nếu đã khui mà chưa sử dụng hết nên bỏ vào hộp đậy kín");
        HashMap<String, SanPhamEntity> data = new HashMap<>();
        boolean edited = true;
        Mockito.when(service.update(sp)).thenReturn(edited);
        HashMap<String, SanPhamEntity> actual = api.updateProduct(sp);
        Assert.assertEquals(data.get("sanpham"), actual.get("sanpham"));
    }
    
    @Test
    public void EditCustomer_Failed_UnitTest() {
        SanPhamEntity sp = new SanPhamEntity(15, "Phân Bón Npk 20-20-15 đa năng 3 màu", 1, "Bao", 100, 46000, "tránh xa tầm tay trẻ em bảo quản nới thoáng mát Sản phẩm nếu đã khui mà chưa sử dụng hết nên bỏ vào hộp đậy kín");
        HashMap<String, SanPhamEntity> data = new HashMap<>();
        boolean edited = false;
        Mockito.when(service.update(sp)).thenReturn(edited);
        HashMap<String, SanPhamEntity> actual = api.updateProduct(sp);
        Assert.assertEquals(data.get("sanpham"), actual.get("sanpham"));
    }
//    @Test
//    public void AddCustomer_Failed_UnitTest() {
//        SanPhamEntity sp = new SanPhamEntity(11, "Phân Bón Npk 20-20-15 đa năng 3 màu", 3, "Bao", 100, 46000, "tránh xa tầm tay trẻ em bảo quản nới thoáng mát Sản phẩm nếu đã khui mà chưa sử dụng hết nên bỏ vào hộp đậy kín");
//        HashMap<String, String> message = new HashMap<>();
//        boolean added = true;
//        Mockito.when(service.create(sp)).thenReturn(added);
//        HashMap<String, String> actual = api.addProduct(sp);
//        Assert.assertEquals(message.get("message"), actual.get("message"));
//    }
}