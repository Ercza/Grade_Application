package Controller;

import java.io.Serializable;

public enum Options implements Serializable {
    Admin, Teacher, Student;

    Options(){

    }
    public String value(){
        return name();
    }
    public static Options fromvalue (String v){
        return  valueOf(v);
    }
}
