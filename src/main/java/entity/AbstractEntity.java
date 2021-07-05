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
public class AbstractEntity implements Serializable{

    private int makh;
    private int tiennap;

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public int getTiennap() {
        return tiennap;
    }

    public void setTiennap(int tiennap) {
        this.tiennap = tiennap;
    }
}
