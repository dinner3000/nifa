package com.brh.p2p.nifa.data.entity;

import java.math.BigDecimal;

public class ExportBusinessBorEntity {
    private String usertype;

    private String certtype;

    private String certid;

    private String sexual;

    private String incomelevel;

    private String incomesource;

    private String position;

    private String city;

    private BigDecimal paymentfund;

    private BigDecimal registfund;

    private String guildsec;

    private String foundtime;

    private String bankname;

    private String bankarea;

    private String creditrating;

    private Short borrownum;

    private String inputdate;

    private String uniqueid;

    private String userid;

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate == null ? null : inputdate.trim();
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid == null ? null : uniqueid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getCerttype() {
        return certtype;
    }

    public void setCerttype(String certtype) {
        this.certtype = certtype == null ? null : certtype.trim();
    }

    public String getCertid() {
        return certid;
    }

    public void setCertid(String certid) {
        this.certid = certid == null ? null : certid.trim();
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual == null ? null : sexual.trim();
    }

    public String getIncomelevel() {
        return incomelevel;
    }

    public void setIncomelevel(String incomelevel) {
        this.incomelevel = incomelevel == null ? null : incomelevel.trim();
    }

    public String getIncomesource() {
        return incomesource;
    }

    public void setIncomesource(String incomesource) {
        this.incomesource = incomesource == null ? null : incomesource.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public BigDecimal getPaymentfund() {
        return paymentfund;
    }

    public void setPaymentfund(BigDecimal paymentfund) {
        this.paymentfund = paymentfund;
    }

    public BigDecimal getRegistfund() {
        return registfund;
    }

    public void setRegistfund(BigDecimal registfund) {
        this.registfund = registfund;
    }

    public String getGuildsec() {
        return guildsec;
    }

    public void setGuildsec(String guildsec) {
        this.guildsec = guildsec == null ? null : guildsec.trim();
    }

    public String getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(String foundtime) {
        this.foundtime = foundtime == null ? null : foundtime.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankarea() {
        return bankarea;
    }

    public void setBankarea(String bankarea) {
        this.bankarea = bankarea == null ? null : bankarea.trim();
    }

    public String getCreditrating() {
        return creditrating;
    }

    public void setCreditrating(String creditrating) {
        this.creditrating = creditrating == null ? null : creditrating.trim();
    }

    public Short getBorrownum() {
        return borrownum;
    }

    public void setBorrownum(Short borrownum) {
        this.borrownum = borrownum;
    }
}