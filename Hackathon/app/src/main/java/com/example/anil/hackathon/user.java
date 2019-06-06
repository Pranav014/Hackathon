package com.example.anil.hackathon;


public class user {
    String rollno, email, password, phone;

    user() {}

    public user(String rollno, String email, String password, String phone) {
        this.rollno = rollno;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Roll Number: " + rollno + "\n" +
                "Email Id: " + email + "\n" +
                "Password: " + password + "\n" +
                "Contact No.: " + phone ;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
