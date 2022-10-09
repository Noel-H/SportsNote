package fr.noelh.sportsnoteapi.customexception;

public class RoleAlreadyExistException extends Exception{

    public RoleAlreadyExistException(String s){
        super(s);
    }
}
