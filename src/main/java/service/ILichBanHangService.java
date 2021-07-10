/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.LichBanHangEnity;
import java.util.List;

/**
 *
 * @author THAIHUYNH
 */
public interface ILichBanHangService {
    List<LichBanHangEnity> getAllPaging(int page);
    int totalPage();
}
