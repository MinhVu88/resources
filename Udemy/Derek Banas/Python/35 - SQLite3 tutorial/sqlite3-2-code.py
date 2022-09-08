import random

# I'll use these lists to generate random first and last names

f_names = ["Michael", "Christopher", "Joshua", "Matthew", "David",
           "Daniel", "Andrew", "Joseph", "Justin", "James",
           "Jessica", "Ashley", "Brittany", "Amanda", "Melissa",
           "Stephanie", "Jennifer", "Samantha", "Sarah", "Megan", "Lauren"]
l_names = ["Smith", "Johnson", "Williams", "Jones", "Brown",
           "Davis", "Miller", "Wilson", "Moore", "Taylor",
           "Anderson", "Thomas", "Jackson", "White", "Harris",
           "Martin", "Thompson", "Garcia", "Martinez", "Robinson"]


# Creates random student insert statements
def create_students(how_many):
    insert = "INSERT INTO student (f_name, l_name, sex) VALUES ('{}', '{}', '{}');"

    # randint returns a random value from the 1st value entered to the next
    for i in range(how_many):
        f_name = f_names[random.randint(0, len(f_names) - 1)]
        l_name = l_names[random.randint(0, len(l_names) - 1)]

        # choice returns a random value from the list provided
        sex = random.choice(['M', 'F'])
        print(f"INSERT INTO student (f_name, l_name, sex) VALUES ('{f_name}', '{l_name}', '{sex}');")


create_students(10)

'''
TEST INSERTS

INSERT INTO test VALUES ('2018-10-1', 'T');
INSERT INTO test VALUES ('2018-10-2', 'Q');
INSERT INTO test VALUES ('2018-10-4', 'Q');
INSERT INTO test VALUES (date('now'), 'T');
'''


# Generate insert statements for test_scores with a random score
def create_test_scores(num_tests, num_studs):
    for i in range(1, num_tests+1):
        for j in range(1, num_studs+1):
            score = random.randrange(1, 25)
            print(f"INSERT INTO test_score VALUES ({j}, {i}, {score});")


create_test_scores(4, 10)

# Give students 1, 2, and 3 a -1 score because they were absent for that test
# Insert absences in the absence table
# INSERT INTO absence VALUES (1, '2018-10-1');
# INSERT INTO absence VALUES (2, '2018-10-1');
# INSERT INTO absence VALUES (3, '2018-10-1');

# In the next video I'll show you numerous select queries, joins and much more
