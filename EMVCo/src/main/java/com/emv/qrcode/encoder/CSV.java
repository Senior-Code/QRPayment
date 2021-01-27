package com.emv.qrcode.encoder;

public class CSV {
    private String AccquiredD;
    private String MerchantID;
    private String StartAmount;
    private String EndAmount;
    private String FeeAmount;
    private String FeePercent;
    private String Currecy;
    public String getAccquiredD() {
        return AccquiredD;
    }

    public void setAccquiredD(String accquiredD) {
        AccquiredD = accquiredD;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getStartAmount() {
        return StartAmount;
    }

    public void setStartAmount(String startAmount) {
        StartAmount = startAmount;
    }

    public String getEndAmount() {
        return EndAmount;
    }

    public void setEndAmount(String endAmount) {
        EndAmount = endAmount;
    }

    public String getFeeAmount() {
        return FeeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        FeeAmount = feeAmount;
    }

    public String getFeePercent() {
        return FeePercent;
    }

    public void setFeePercent(String feePercent) {
        FeePercent = feePercent;
    }

    public String getCurrecy() {
        return Currecy;
    }

    public void setCurrecy(String currecy) {
        Currecy = currecy;
    }
}
