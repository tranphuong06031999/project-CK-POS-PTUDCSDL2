/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.SanPhamEntity;
import java.util.ArrayList;

/**
 *
 * @author Trần Đinh Phương
 */
public interface ISanPhamRepository {

    public SanPhamEntity findById(int masp);

    public boolean create(SanPhamEntity sp);

    public boolean update(SanPhamEntity sp);

    public ArrayList<SanPhamEntity> search(String keyword);

    public ArrayList<SanPhamEntity> productsList();

    public int count();

    public ArrayList<SanPhamEntity> paging(int page);

    public ArrayList<SanPhamEntity> searchPaging(String keyword, int page);

    public int countSearch(String keyword);
    
    public boolean checkQuantity(int id, int quantity);

}
