/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.LichBanHangEnity;
import java.util.List;

/**
 *
 * @author THAIHUYNH
 */
public interface ILichBanHangRepository {

    public List<LichBanHangEnity> paging(int page);

    public int count();

    public List<LichBanHangEnity> pagingSearch(int page, int makh);

    public int countSearch(int makh);
}
