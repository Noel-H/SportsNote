package fr.noelh.sportsnoteapi.customexception;

public class RoleNotFoundForThisUserAccountException extends Exception{

    public RoleNotFoundForThisUserAccountException(String s){
        super(s);
    }
}
