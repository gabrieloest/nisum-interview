package com.nisum.interview.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseError implements Serializable {
    private String message;

    public ResponseError(String message){
        this.message = message;
    }
}