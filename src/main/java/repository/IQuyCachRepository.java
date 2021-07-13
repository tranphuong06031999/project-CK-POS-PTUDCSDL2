/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.QuyCachEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IQuyCachRepository {

    public ArrayList<QuyCachEntity> findAllByMaSP(int masp);

    public boolean create(QuyCachEntity quycach);

    public boolean update(QuyCachEntity quycach);

    public boolean delete(int id);
}
