package com.naval.qwerto.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class User {
    @Id String Phone;
    String Uname;
    String RegID;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String id) {
        this.Phone = id;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String who) {
        this.Uname = who;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String id) {
        this.RegID = id;
    }
}
