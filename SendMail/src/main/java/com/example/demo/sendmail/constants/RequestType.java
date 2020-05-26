package com.example.demo.sendmail.constants;

public enum RequestType
{
    USER_NEW("USER_NEW"),
    USER_UPDATE("USER_UPDATE"),
    USER_CANCEL("USER_CANCEL"),
    SHOP_NEW("SHOP_NEW"),
    SHOP_UPDATE("SHOP_UPDATE"),
    SHOP_CANCEL("SHOP_CANCEL"),
    ;

    private String requestType;

    private RequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public static boolean isCancel(String requestType) {
        return (RequestType.valueOf(requestType) == RequestType.USER_CANCEL ||
                RequestType.valueOf(requestType) == RequestType.SHOP_CANCEL) ? true : false;
    }
}
