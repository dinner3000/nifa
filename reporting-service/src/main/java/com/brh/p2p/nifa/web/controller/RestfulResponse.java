package com.brh.p2p.nifa.web.controller;

import java.io.Serializable;

public class RestfulResponse implements Serializable {
    private boolean success;
    public RestfulResponse(boolean isSuccess){
        this.success = isSuccess;
    }
}