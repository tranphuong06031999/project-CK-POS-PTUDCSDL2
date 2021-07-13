/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author pmtu
 */
public class TestRunner {

    public static void main(String[] args) {
        Result resultSanPham = JUnitCore.runClasses(TestSanPhamController.class);
        Result resultGioHang = JUnitCore.runClasses(TestGioHangController.class);
        Result resultLichBanHang = JUnitCore.runClasses(TestLichBanHangController.class);
        Result resultKhachHang = JUnitCore.runClasses(TestKhachHangController.class);
        Result resultPhieuThu = JUnitCore.runClasses(TestPhieuThuController.class);
        Result resultHoaDon = JUnitCore.runClasses(TestHoaDonController.class);
        Result resultQuyCach = JUnitCore.runClasses(TestQuyCachController.class);

        for (Failure failure : resultSanPham.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : resultGioHang.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : resultLichBanHang.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : resultKhachHang.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : resultPhieuThu.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : resultHoaDon.getFailures()) {
            System.out.println(failure.toString());
        }
        
         for (Failure failure : resultQuyCach.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(resultSanPham.wasSuccessful());
        System.out.println(resultGioHang.wasSuccessful());
        System.out.println(resultLichBanHang.wasSuccessful());
        System.out.println(resultKhachHang.wasSuccessful());
        System.out.println(resultPhieuThu.wasSuccessful());
        System.out.println(resultHoaDon.wasSuccessful());
        System.out.println(resultQuyCach.wasSuccessful());
    }
}
