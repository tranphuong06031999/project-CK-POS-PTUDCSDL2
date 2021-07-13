/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.SanPhamController;
import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.ISanPhamService;

/**
 *
 * @author NguyenThanhDat
 */
@RunWith(MockitoJUnitRunner.class)
public class TestSanPhamController {

    @InjectMocks
    SanPhamController controller = new SanPhamController();

    @Mock
    ISanPhamService service;

    @Test
    public void ProductList_NonEmpty_UnitTest() {
        ArrayList<SanPhamEntity> lst = new ArrayList<>();
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(1);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(80);
        sp.setGia(38000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        lst.add(sp);
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.productList(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<SanPhamEntity> actual = (ArrayList<SanPhamEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getTenSP(), actual.get(0).getTenSP());
    }

    @Test
    public void ProductList_Empty_UnitTest() {
        ArrayList<SanPhamEntity> lst = null;
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.productList(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<SanPhamEntity> actual = (ArrayList<SanPhamEntity>) map.get("list");
        Assert.assertEquals(lst, actual);

    }

    @Test
    public void ProductList_CheckView_UnitTest() {
        ArrayList<SanPhamEntity> lst = new ArrayList<>();
        Mockito.when(service.getAllPaging(1)).thenReturn(lst);
        ModelAndView model = controller.productList(1, null);
        String expect_view = "productList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);

    }

    @Test
    public void UpdateProduct_Success_UnitTest() {
        String messenge = "Cập nhập sản phẩm thành công";
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(1);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(80);
        sp.setGia(38000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        Mockito.when(service.updateSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.updateProduct(sp);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);

    }

    @Test
    public void UpdateProduct_Failed_UnitTest() {
        String messenge = "Cập nhập sản phẩm thất bại!";
        SanPhamEntity sp = new SanPhamEntity();
        // mã sản phẩm không có
        sp.setMaSP(1000);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(80);
        sp.setGia(38000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        Mockito.when(service.updateSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.updateProduct(sp);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void UpdateProduct_RedirectView_UnitTest() {
        String messenge = "Cập nhập sản phẩm thất bại!";
        SanPhamEntity sp = new SanPhamEntity();
        Mockito.when(service.updateSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.updateProduct(sp);
        String expect_view = "redirect:/product";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void AddNewProduct_Success_UnitTest() {
        String messenge = "Thêm sản phẩm thành công";
        SanPhamEntity sp = new SanPhamEntity();
        // Id sản phẩm mới
        sp.setMaSP(140);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(800);
        sp.setGia(40000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        Mockito.when(service.updateSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.updateProduct(sp);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void AddNewProduct_Failed_UnitTest() {
        String messenge = "Thêm sản phẩm thất bại!";
        SanPhamEntity sp = new SanPhamEntity();
        // Id sản phẩm đã tồn tại
        sp.setMaSP(1);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(800);
        sp.setGia(40000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        Mockito.when(service.updateSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.updateProduct(sp);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void AddNewProduct_RedirectView_UnitTest() {
        String messenge = "Thêm sản phẩm thất bại!";
        SanPhamEntity sp = new SanPhamEntity();
        Mockito.when(service.addSanPham(sp)).thenReturn(messenge);
        ModelAndView model = controller.addNewProduct(sp);
        String expect_view = "redirect:/product";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void SearchProduct_NonEmpty_UnitTest() {
        ArrayList<SanPhamEntity> lst = new ArrayList<>();
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(1);
        sp.setTenSP("Phân bón lá NPK hòa tan Sitto FoPro 10-52-10+TE");
        sp.setDonVi("Gói");
        sp.setLoai(2);
        sp.setSoLuong(100);
        sp.setGia(28000);
        sp.setGhiChu("SIÊU TẠO MẦM HOA. KÍCH RA HOA ĐỒNG LOẠT GIÚP BỘ RỄ PHÁT TRIỂN MẠNH, ĐẺ NHÁNH HỮU HIỆU CỦ NHIỀU, CỦ TO, TĂNG HÀM LƯỢNG TINH BỘT");
        lst.add(sp);
        Mockito.when(service.searchPaging("phân bón", 1)).thenReturn(lst);
        ModelAndView model = controller.searchProduct("phân bón", 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<SanPhamEntity> actual = (ArrayList<SanPhamEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst.get(0).getTenSP(), actual.get(0).getTenSP());
    }

    @Test
    public void SearchProduct_Empty_UnitTest() {
        ArrayList<SanPhamEntity> lst = null;
        Mockito.when(service.searchPaging("Quần áo", 1)).thenReturn(lst);
        ModelAndView model = controller.searchProduct("Quần áo", 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<SanPhamEntity> actual = (ArrayList<SanPhamEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void SearchProduct_CheckView_UnitTest() {
        ArrayList<SanPhamEntity> lst = new ArrayList<>();
        Mockito.when(service.searchPaging("phân bón", 1)).thenReturn(lst);
        ModelAndView model = controller.searchProduct("phân bón", 1);
        String expect_view = "productList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }
}
