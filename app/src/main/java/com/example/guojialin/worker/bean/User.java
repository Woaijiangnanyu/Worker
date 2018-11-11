package com.example.guojialin.worker.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    /***
     * 序列化该类
     */
    private long userNumber;
    private String userName;

    public User(){}

    public User(long userNumber,String userName){
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public User(Parcel in) {

        //反序列化：必须按照序列化声明变量顺序读取
        this.userName = in.readString();
        this.userNumber = in.readLong();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(long userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeLong(userNumber);

    }

    @Override
    public String toString() {
        return "user: " + userName + " ,id: " + userNumber;
    }
}
