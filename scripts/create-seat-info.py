from random import choice

for i in range(1, 200):
    print(f"insert into concert_show (id, price, concert_id) values({i}, 1000, 1);")

print("")
for i in range(1, 200):
    for j in range(1, 50):
        val = choice(["EMPTY", "RESERVED"])
        print(
        "insert into seat_info (id, seat_number, occupied_status, version, show_id)" +
        f"values ({i*50+j}, {j}, \"{val}\", 0, {i});"
        )