package com.example.guojialin.worker.bean;

public class AccessPoint {
    private String ssid;
    private String pwd;
    private String encryption;

    public AccessPoint(){}

    public AccessPoint(String ssid,String pwd ,String encryptionType){
        this.ssid = ssid;
        this.pwd = pwd;
        this.encryption = encryptionType;
    }
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }
}
