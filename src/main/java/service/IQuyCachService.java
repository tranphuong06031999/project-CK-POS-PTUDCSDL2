/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.QuyCachEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IQuyCachService {

    public ArrayList<QuyCachEntity> getAllByMaSP(int masp);

    public String addSpecifications(QuyCachEntity qc);

    public String updateSpecifications(QuyCachEntity qc);

    public String deleteSpecifications(int id);
}
