package com.dextea.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Staff {

    @Id
    private int id;


    private String account;
    private String password;
    private int role;
    private int store_id;
    private String auth;


    public Staff(String account, String password, int role, int store_id, String auth) {
        this.account = account;
        this.password = password;
        this.role = role;
        this.store_id = store_id;
        this.auth = auth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getAuth() {
        return auth;
    }

    public String toString(){
        return "Staff[ "+"id="+id+" account="+account+" password="+password+" role="+role+" store_id="
                +" auth="+auth+" ]";
    }
}
