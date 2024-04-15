use concert;
insert into concert values(1, "iu");
insert into concert_show (id, price, concert_id) values(1, 1000, 1);
insert into concert_show (id, price, concert_id) values(2, 2000, 1);

insert into seat_info (id, seat_number, occupied_status, version, show_id) values(1, 1, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(2, 2, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(3, 3, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(4, 4, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(5, 5, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(6, 6, "EMPTY", 0, 1 );
insert into seat_info (id, seat_number, occupied_status, version, show_id) values(7, 7, "EMPTY", 0, 1 );
