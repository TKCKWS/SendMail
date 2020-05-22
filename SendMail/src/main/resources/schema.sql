/* 予約テーブル */
CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT PRIMARY KEY,  /* 予約ID */
    user_id INT,                      /* ユーザID */
    shop_id INT,                      /* お店ID */
    reservation_start DATETIME,       /* 開始日時 */
    resevation_end DATETIME,          /* 終了日時 */
    number INT,                       /* 予約人数 */
    status INT                        /* 状態 0:通常 */
);

/* お店テーブル */
CREATE TABLE IF NOT EXISTS shop (
    shop_id INT PRIMARY KEY,  /* お店ID */
    name VARCHAR(50),          /* お店名 */
    mail_address VARCHAR(255)  /* メールアドレス */
);
