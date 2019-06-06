package com.example.anil.hackathon;


public class order {
    String  pname, pprice,user;

    order() {}

    public order(String pname, String pprice, String user) {
        this.pname = pname;
        this.pprice = pprice;
        this.user = user;
    }

    @Override
    public String toString() {
        return "order{" +
                "pname='" + pname + '\'' +
                ", pprice='" + pprice + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
