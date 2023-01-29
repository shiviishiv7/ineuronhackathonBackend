package com.hack.hackathon.exceptionsHandler;

public class RecordFound extends Exception {
    public RecordFound(String addressNotFound) {
        super(addressNotFound);
    }
}
