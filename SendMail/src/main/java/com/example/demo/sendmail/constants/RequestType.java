package com.example.demo.sendmail.constants;

public enum RequestType
{
    USER_NEW("USER_NEW"),               // ユーザ向け新規
    USER_UPDATE("USER_UPDATE"),         // ユーザ向け更新
    USER_CANCEL("USER_CANCEL"),         // ユーザ向けキャンセル
    SHOP_NEW("SHOP_NEW"),               // お店向け新規
    SHOP_UPDATE("SHOP_UPDATE"),         // お店向け変更
    SHOP_CANCEL("SHOP_CANCEL"),         // お店向けキャンセル
    ;

    // リクエスト種別
    private String requestType;

    private RequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * リクエスト種別取得
     */
    public String getRequestType() {
        return this.requestType;
    }

    /**
     * キャンセル判定
     */
    public static boolean isCancel(String requestType) {
        return (RequestType.valueOf(requestType) == RequestType.USER_CANCEL ||
                RequestType.valueOf(requestType) == RequestType.SHOP_CANCEL) ? true : false;
    }
}
