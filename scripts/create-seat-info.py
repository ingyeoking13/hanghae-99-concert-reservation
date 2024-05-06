
for i in range(1, 100000):
    print(
    "insert into seat_info (id, seat_number, occupied_status, version, show_id)" +
    f"values ({i}, {i}, \"EMPTY\", 0, 1);"
    )