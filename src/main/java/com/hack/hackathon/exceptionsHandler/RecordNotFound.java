package com.hack.hackathon.exceptionsHandler;

public class RecordNotFound extends Exception {
    public RecordNotFound(String addressNotFound) {
        super(addressNotFound);
    }
}
