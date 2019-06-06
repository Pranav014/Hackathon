package com.example.anil.hackathon;



public class lost {
    private String desc;
    private String pno;

    lost() {}

    public lost( String desc, String pno) {

        this.desc = desc;
        this.pno = pno;

    }

    @Override
    public String toString() {
        return "Item Found = " + desc + "\n" + "Please Contact the following Phone Number to Claim Your Item" + "\n" +
                "Phone Number = " + pno;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }
}
