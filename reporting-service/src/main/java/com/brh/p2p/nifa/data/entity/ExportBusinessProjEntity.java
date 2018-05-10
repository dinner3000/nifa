package com.brh.p2p.nifa.data.entity;

import java.math.BigDecimal;

public class ExportBusinessProjEntity {
    private String socialid;

    private String platformno;

    private String projectno;

    private String projecttype;

    private String projectname;

    private String establishdate;

    private BigDecimal businesssum;

    private String currency;

    private String putoutdate;

    private String maturitydate;

    private Short loanterm;

    private BigDecimal loanrate;

    private BigDecimal feerate;

    private BigDecimal plaformamt;

    private BigDecimal otherfeeamt;

    private String guarantee;

    private Short loanperiod;

    private String vouchtype;

    private String guaranteecompany;

    private String payschedule;

    private String actualpayrecord;

    private BigDecimal actualpaycorpusamt;

    private BigDecimal actualpayinteamt;

    private BigDecimal surpluspayinteamt;

    private BigDecimal surplusayinteamt;

    private String transferflag;

    private String projecctstatus;

    private String overduereason;

    private Short overduenum;

    private String repaymentmethod;

    private String loanusage;

    private Short investornum;
    private String inputdate;

    private String uniqueid;


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
    public String getSocialid() {
        return socialid;
    }

    public void setSocialid(String socialid) {
        this.socialid = socialid == null ? null : socialid.trim();
    }

    public String getPlatformno() {
        return platformno;
    }

    public void setPlatformno(String platformno) {
        this.platformno = platformno == null ? null : platformno.trim();
    }

    public String getProjectno() {
        return projectno;
    }

    public void setProjectno(String projectno) {
        this.projectno = projectno == null ? null : projectno.trim();
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype == null ? null : projecttype.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getEstablishdate() {
        return establishdate;
    }

    public void setEstablishdate(String establishdate) {
        this.establishdate = establishdate == null ? null : establishdate.trim();
    }

    public BigDecimal getBusinesssum() {
        return businesssum;
    }

    public void setBusinesssum(BigDecimal businesssum) {
        this.businesssum = businesssum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPutoutdate() {
        return putoutdate;
    }

    public void setPutoutdate(String putoutdate) {
        this.putoutdate = putoutdate == null ? null : putoutdate.trim();
    }

    public String getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(String maturitydate) {
        this.maturitydate = maturitydate == null ? null : maturitydate.trim();
    }

    public Short getLoanterm() {
        return loanterm;
    }

    public void setLoanterm(Short loanterm) {
        this.loanterm = loanterm;
    }

    public BigDecimal getLoanrate() {
        return loanrate;
    }

    public void setLoanrate(BigDecimal loanrate) {
        this.loanrate = loanrate;
    }

    public BigDecimal getFeerate() {
        return feerate;
    }

    public void setFeerate(BigDecimal feerate) {
        this.feerate = feerate;
    }

    public BigDecimal getPlaformamt() {
        return plaformamt;
    }

    public void setPlaformamt(BigDecimal plaformamt) {
        this.plaformamt = plaformamt;
    }

    public BigDecimal getOtherfeeamt() {
        return otherfeeamt;
    }

    public void setOtherfeeamt(BigDecimal otherfeeamt) {
        this.otherfeeamt = otherfeeamt;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee == null ? null : guarantee.trim();
    }

    public Short getLoanperiod() {
        return loanperiod;
    }

    public void setLoanperiod(Short loanperiod) {
        this.loanperiod = loanperiod;
    }

    public String getVouchtype() {
        return vouchtype;
    }

    public void setVouchtype(String vouchtype) {
        this.vouchtype = vouchtype == null ? null : vouchtype.trim();
    }

    public String getGuaranteecompany() {
        return guaranteecompany;
    }

    public void setGuaranteecompany(String guaranteecompany) {
        this.guaranteecompany = guaranteecompany == null ? null : guaranteecompany.trim();
    }

    public String getPayschedule() {
        return payschedule;
    }

    public void setPayschedule(String payschedule) {
        this.payschedule = payschedule == null ? null : payschedule.trim();
    }

    public String getActualpayrecord() {
        return actualpayrecord;
    }

    public void setActualpayrecord(String actualpayrecord) {
        this.actualpayrecord = actualpayrecord == null ? null : actualpayrecord.trim();
    }

    public BigDecimal getActualpaycorpusamt() {
        return actualpaycorpusamt;
    }

    public void setActualpaycorpusamt(BigDecimal actualpaycorpusamt) {
        this.actualpaycorpusamt = actualpaycorpusamt;
    }

    public BigDecimal getActualpayinteamt() {
        return actualpayinteamt;
    }

    public void setActualpayinteamt(BigDecimal actualpayinteamt) {
        this.actualpayinteamt = actualpayinteamt;
    }

    public BigDecimal getSurpluspayinteamt() {
        return surpluspayinteamt;
    }

    public void setSurpluspayinteamt(BigDecimal surpluspayinteamt) {
        this.surpluspayinteamt = surpluspayinteamt;
    }

    public BigDecimal getSurplusayinteamt() {
        return surplusayinteamt;
    }

    public void setSurplusayinteamt(BigDecimal surplusayinteamt) {
        this.surplusayinteamt = surplusayinteamt;
    }

    public String getTransferflag() {
        return transferflag;
    }

    public void setTransferflag(String transferflag) {
        this.transferflag = transferflag == null ? null : transferflag.trim();
    }

    public String getProjecctstatus() {
        return projecctstatus;
    }

    public void setProjecctstatus(String projecctstatus) {
        this.projecctstatus = projecctstatus == null ? null : projecctstatus.trim();
    }

    public String getOverduereason() {
        return overduereason;
    }

    public void setOverduereason(String overduereason) {
        this.overduereason = overduereason == null ? null : overduereason.trim();
    }

    public Short getOverduenum() {
        return overduenum;
    }

    public void setOverduenum(Short overduenum) {
        this.overduenum = overduenum;
    }

    public String getRepaymentmethod() {
        return repaymentmethod;
    }

    public void setRepaymentmethod(String repaymentmethod) {
        this.repaymentmethod = repaymentmethod == null ? null : repaymentmethod.trim();
    }

    public String getLoanusage() {
        return loanusage;
    }

    public void setLoanusage(String loanusage) {
        this.loanusage = loanusage == null ? null : loanusage.trim();
    }

    public Short getInvestornum() {
        return investornum;
    }

    public void setInvestornum(Short investornum) {
        this.investornum = investornum;
    }
}