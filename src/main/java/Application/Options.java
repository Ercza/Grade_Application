package Application;

public enum Options {
    Admin, Teacher, Student;

    private Options(){

    }
    public String value(){
        return name();
    }
    public static Options fromvalue (String v){
        return  valueOf(v);
    }
}
