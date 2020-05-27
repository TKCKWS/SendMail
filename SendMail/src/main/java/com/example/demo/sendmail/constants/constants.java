package com.example.demo.sendmail.constants;

public final class constants {
    /* フォーマット */
    // 日付フォーマット
    public static final String DATE_FORMAT_YMD = "yyyy/MM/dd";
    // 時間フォーマット
    public static final String TIME_FORMAT_HM = "HH:mm";

    /* メールテンプレートリゾルバ用 */
    // メールテンプレート定義
    public static final String MAIL_TEMPLATE_PATH_ROOT = "templates/mail/";
    // メールテンプレートHTML用
    public static final String MAIL_TEMPLATE_HTML = "html";
    // メール文字コード
    public static final String MAIL_CHARACTER_CODE = "UTF-8";

    /* URL */
    // ユーザ予約URL
    public static final String USER_RESERVE_URL = "/user/reserve/";
    // お店予約URL
    public static final String SHOP_RESERVE_URL = "/shop/reserve/";
}
