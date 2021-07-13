/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import controller.QuyCachController;
import entity.QuyCachEntity;
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
import service.IQuyCachService;
import service.ISanPhamService;

/**
 *
 * @author NguyenThanhDat
 */
@RunWith(MockitoJUnitRunner.class)
public class TestQuyCachController {

    @InjectMocks
    QuyCachController controller = new QuyCachController();

    @Mock
    IQuyCachService qcService;

    @Mock
    ISanPhamService spService;

    @Test
    public void AddSpecifications_Success_UnitTest() {
        String messenge = "Thêm thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        qc.setMasp(1);
        qc.setQuycach(1000);
        Mockito.when(qcService.addSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.addSpecifications(qc, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void AddSpecifications_Failed_UnitTest() {
        String messenge = "Thêm thất bại";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        //Mã sản phẩm không tồn tại
        qc.setMasp(1000);
        qc.setQuycach(1000);
        Mockito.when(qcService.addSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.addSpecifications(qc, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void AddSpecifications_RedirectView_UnitTest() {
        String messenge = "Thêm thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        //Mã sản phẩm không tồn tại
        qc.setMasp(1);
        qc.setQuycach(1000);
        Mockito.when(qcService.addSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.addSpecifications(qc, 1);
        String expect_view = "redirect:/specifications/" + 1;
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void UpdateSpecifications_Success_UnitTest() {
        String messenge = "Cập nhập thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        qc.setQuycach(1000);
        Mockito.when(qcService.updateSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.updateSpecifications(qc, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void UpdateSpecifications_Failed_UnitTest() {
        String messenge = "Cập nhập thất bại";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        //Id không tồn tại
        qc.setId(1000);
        qc.setQuycach(1000);
        Mockito.when(qcService.updateSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.updateSpecifications(qc, 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void UpdateSpecifications_RedirectView_UnitTest() {
        String messenge = "Cập nhập thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        qc.setQuycach(1000);
        Mockito.when(qcService.updateSpecifications(qc)).thenReturn(messenge);
        ModelAndView model = controller.updateSpecifications(qc, 1);
        String expect_view = "redirect:/specifications/" + 1;
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void DeleteSpecifications_Success_UnitTest() {
        String messenge = "Xóa thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setId(1);
        Mockito.when(qcService.deleteSpecifications(qc.getId())).thenReturn(messenge);
        ModelAndView model = controller.deleteSpecifications(qc.getId(), 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void DeleteSpecifications_Failed_UnitTest() {
        String messenge = "Xóa thất bại";
        QuyCachEntity qc = new QuyCachEntity();
        //Id không tồn tại
        qc.setId(1000);
        Mockito.when(qcService.deleteSpecifications(qc.getId())).thenReturn(messenge);
        ModelAndView model = controller.deleteSpecifications(qc.getId(), 1);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        String actual = (String) map.get("message");
        Assert.assertEquals(messenge, actual);
    }

    @Test
    public void DeleteSpecifications_RedirectView_UnitTest() {
        String messenge = "Xóa thành công";
        QuyCachEntity qc = new QuyCachEntity();
        qc.setId(1);
        Mockito.when(qcService.deleteSpecifications(qc.getId())).thenReturn(messenge);
        ModelAndView model = controller.deleteSpecifications(qc.getId(), 1);
        String expect_view = "redirect:/specifications/" + 1;
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void GetAll_Empty_UnitTest() {
        ArrayList<QuyCachEntity> lst = null;
        Mockito.when(qcService.getAllByMaSP(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<QuyCachEntity> actual = (ArrayList<QuyCachEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }

    @Test
    public void GetAll_CheckView_UnitTest() {
        ArrayList<QuyCachEntity> lst = new ArrayList<>();
        Mockito.when(qcService.getAllByMaSP(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        String expect_view = "specificationsList";
        String view_actual = model.getViewName();
        Assert.assertEquals(expect_view, view_actual);
    }

    @Test
    public void GetAll_NonEmpty_UnitTest() {
        ArrayList<QuyCachEntity> lst = new ArrayList<>();
        QuyCachEntity qc = new QuyCachEntity();
        qc.setGiagoc(10000000);
        qc.setId(1);
        qc.setMasp(1);
        qc.setQuycach(1000);
        lst.add(qc);
        Mockito.when(qcService.getAllByMaSP(1)).thenReturn(lst);
        ModelAndView model = controller.getAll(1, null);
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        ArrayList<QuyCachEntity> actual = (ArrayList<QuyCachEntity>) map.get("list"); //Tên object gán cho ModelAndView
        Assert.assertEquals(lst, actual);
    }
}
