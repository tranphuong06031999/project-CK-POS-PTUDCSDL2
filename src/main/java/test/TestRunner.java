/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author NguyenThanhDat
 */
public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(KhachHangApiTest.class);
        Result result1 = JUnitCore.runClasses(PhieuThuApiTest.class);
        Result result2 = JUnitCore.runClasses(GioHangApiTest.class);        
        Result result3 = JUnitCore.runClasses(SanPhamAPITest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : result1.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : result2.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : result3.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println(result1.wasSuccessful());
        System.out.println(result2.wasSuccessful());
        System.out.println(result3.wasSuccessful());

    }
}
