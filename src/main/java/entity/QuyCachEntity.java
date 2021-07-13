/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author NguyenThanhDat
 */
public class QuyCachEntity implements Serializable {

    private int id;
    private int masp;
    private int quycach;
    private int giagoc;

    public QuyCachEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getQuycach() {
        return quycach;
    }

    public void setQuycach(int quycach) {
        this.quycach = quycach;
    }

    public int getGiagoc() {
        return giagoc;
    }

    public void setGiagoc(int giagoc) {
        this.giagoc = giagoc;
    }

}
