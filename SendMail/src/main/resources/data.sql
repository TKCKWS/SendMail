/* 予約テーブルのデータ */
INSERT INTO reservation (reservation_id, user_id, shop_id, reservation_start, reservation_end, number, status)
VALUES(1, 1, 1, '2021-05-22 18:00:00','2021-05-22 19:00:00', 2, 0);

/* お店テーブルのデータ */
INSERT INTO shop (shop_id, name, mail_address)
VALUES(1, 'テスト店', 'test-ten@xxx.co.jp');
