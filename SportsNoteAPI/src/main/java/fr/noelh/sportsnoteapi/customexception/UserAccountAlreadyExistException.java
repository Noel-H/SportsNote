package fr.noelh.sportsnoteapi.customexception;

public class UserAccountAlreadyExistException extends Exception{

    public UserAccountAlreadyExistException(String s){
        super(s);
    }
}
