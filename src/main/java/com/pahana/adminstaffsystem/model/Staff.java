package com.pahana.adminstaffsystem.model;


public class Staff extends User {
    
    protected String email; 
    protected int staff_id;
    
    public Staff(){}
    
    public Staff(String username, String password, String email, int staff_id){
        super(username, password);
        this.email = email;
        this.staff_id = staff_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStaff_id() {
        return staff_id;
}

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
}
    
    
}
