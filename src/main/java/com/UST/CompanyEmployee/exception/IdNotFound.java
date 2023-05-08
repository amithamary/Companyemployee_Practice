package com.UST.CompanyEmployee.exception;

public class IdNotFound extends RuntimeException {
    public IdNotFound(String idNotFound) {
        super(idNotFound);
    }
}