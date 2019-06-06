package com.example.anil.hackathon;

/**
 * Created by Anil on 04/10/2018.
 */

public class Books {

    private String bname, bsem, bownername, bcontact;
    private int bPrice;

    Books() {}

    public Books(String bname, String bsem, String bownername, String bcontact, int bPrice) {
        this.bname = bname;
        this.bsem = bsem;
        this.bownername = bownername;
        this.bcontact = bcontact;
        this.bPrice = bPrice;
    }

    @Override
    public String toString() {
        return "Book is: " + bname + "\n" +
                "Semester " + bsem + "\n" +
                "Owner is  " + bownername + "\n" +
                "Contact Owner: " + bcontact + "\n" +
                "Price of Book: " + bPrice;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBsem() {
        return bsem;
    }

    public void setBsem(String bsem) {
        this.bsem = bsem;
    }

    public String getBownername() {
        return bownername;
    }

    public void setBownername(String bownername) {
        this.bownername = bownername;
    }

    public String getBcontact() {
        return bcontact;
    }

    public void setBcontact(String bcontact) {
        this.bcontact = bcontact;
    }

    public int getbPrice() {
        return bPrice;
    }

    public void setbPrice(int bPrice) {
        this.bPrice = bPrice;
    }
}
