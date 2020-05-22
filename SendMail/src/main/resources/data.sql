/* 予約テーブルのデータ */
INSERT INTO reservation (reservation_id, user_id, shop_id, reservation_start, reservation_end, number, status)
VALUES(1, 1, 1, '2020-05-22 18:00:00','2020-05-22 19:00:00', 2, 0);
INSERT INTO reservation (reservation_id, user_id, shop_id, reservation_start, reservation_end, number, status)
VALUES(2, 1, 1, '2020-05-23 18:00:00','2020-05-22 19:00:00', 3, 1);

/* お店テーブルのデータ */
INSERT INTO shop (shop_id, name, mail_address)
VALUES(1, 'テスト店', 'test-ten@xxx.co.jp');
