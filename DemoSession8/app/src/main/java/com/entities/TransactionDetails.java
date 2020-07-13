package com.entities;

import java.io.Serializable;
import java.util.Date;

public class TransactionDetails implements Serializable {
    private int transid;
    private int accid;
    private double transmoney;
    private int transtype;
    private Date dateoftrans;

    public int getTransid() {
        return transid;
    }
    public void setTransid(int transid) {
        this.transid = transid;
    }
    public int getAccid() {
        return accid;
    }
    public void setAccid(int accid) {
        this.accid = accid;
    }
    public double getTransmoney() {
        return transmoney;
    }
    public void setTransmoney(double transmoney) {
        this.transmoney = transmoney;
    }
    public int getTranstype() {
        return transtype;
    }
    public void setTranstype(int transtype) {
        this.transtype = transtype;
    }
    public Date getDateoftrans() {
        return dateoftrans;
    }
    public void setDateoftrans(Date dateoftrans) {
        this.dateoftrans = dateoftrans;
    }
}
