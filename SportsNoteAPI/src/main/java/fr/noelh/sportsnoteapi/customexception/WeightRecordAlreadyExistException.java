package fr.noelh.sportsnoteapi.customexception;

public class WeightRecordAlreadyExistException extends Exception{

    public WeightRecordAlreadyExistException(String s){
        super(s);
    }
}
