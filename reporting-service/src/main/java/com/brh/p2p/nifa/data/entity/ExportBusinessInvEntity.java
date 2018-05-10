package com.brh.p2p.nifa.data.entity;

import java.math.BigDecimal;

public class ExportBusinessInvEntity {
    private String usertype;

    private String certtype;

    private String certid;

    private String industrialtype;

    private String city;

    private String guildsec;

    private BigDecimal businesssum;

    private String investstatus;

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

    public String getIndustrialtype() {
        return industrialtype;
    }

    public void setIndustrialtype(String industrialtype) {
        this.industrialtype = industrialtype == null ? null : industrialtype.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getGuildsec() {
        return guildsec;
    }

    public void setGuildsec(String guildsec) {
        this.guildsec = guildsec == null ? null : guildsec.trim();
    }

    public BigDecimal getBusinesssum() {
        return businesssum;
    }

    public void setBusinesssum(BigDecimal businesssum) {
        this.businesssum = businesssum;
    }

    public String getInveststatus() {
        return investstatus;
    }

    public void setInveststatus(String investstatus) {
        this.investstatus = investstatus == null ? null : investstatus.trim();
    }
}