/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PhieuThuEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IPhieuThuService {

    public ArrayList<PhieuThuEntity> getAll();

    public ArrayList<PhieuThuEntity> searchReceipt(String keyword);

    public PhieuThuEntity getOne(int id);

    public ArrayList<PhieuThuEntity> getAllPaging(int page);

    public int totalPage();

    public ArrayList<PhieuThuEntity> searchPaging(String keyword, int page);

    public int totalPageSearch(String keyword);
}
