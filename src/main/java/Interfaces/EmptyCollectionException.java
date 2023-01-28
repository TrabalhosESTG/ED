package Interfaces;


public class EmptyCollectionException  extends RuntimeException{
    public EmptyCollectionException(String collection) {
        System.out.println("The " + collection + " is empty.");
    }

}
