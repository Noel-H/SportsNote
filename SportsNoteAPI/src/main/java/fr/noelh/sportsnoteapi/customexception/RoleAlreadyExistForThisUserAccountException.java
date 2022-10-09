package fr.noelh.sportsnoteapi.customexception;

public class RoleAlreadyExistForThisUserAccountException extends Exception{

    public RoleAlreadyExistForThisUserAccountException(String s){
        super(s);
    }
}
