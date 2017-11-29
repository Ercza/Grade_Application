package Application;

public enum Options {
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
