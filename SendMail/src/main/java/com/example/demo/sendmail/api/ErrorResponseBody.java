package com.example.demo.sendmail.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class ErrorResponseBody {
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private String error;
    @JsonProperty("message")
    private String message;
    @JsonProperty("path")
    private String path;
    @JsonProperty("errorDetail")
    private String errorDetail;
}
