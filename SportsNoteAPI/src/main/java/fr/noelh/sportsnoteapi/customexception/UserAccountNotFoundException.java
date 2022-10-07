package fr.noelh.sportsnoteapi.customexception;

public class UserAccountNotFoundException extends Exception{

    public UserAccountNotFoundException(String s){
        super(s);
    }
}
