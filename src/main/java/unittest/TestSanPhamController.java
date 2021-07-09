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
}
