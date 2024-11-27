package com.hasim.springboot.neo4j.example.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(){
        super();
    }
    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(Throwable t){
        super(t);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
