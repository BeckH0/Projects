import sys

def split_player_and_points(line):
    name, points = line.split(", ")

    return name, int(points)

def add_player(table, player_name, points):
    with open(table, "a") as file:
        file.write(f"\n{player_name}, {points}")
    print(f"Added {player_name} with {points} points to {table_file}.")


def remove_player(table, player_name):
    with open(table) as file:
        in_content = False
        content = file.readlines()
        for line in content:
            name = split_player_and_points(line)[0]
            if name == player_name:
                content.remove(line)
                in_content = True
        
        if in_content == False:
            print(f"Player {player_name} not found in {table}.")
        else:
            print(f"Player {player_name} removed from {table}.")

    with open(table, 'w') as new_file:
        new_file.writelines(content)

def add_points(table, player_name, points):
    with open(table) as file:
        in_content = False
        content = file.readlines()
        for i in range(len(content)):
            name, current_points = split_player_and_points(content[i])
            if name == player_name:
                new_points = int(current_points) + int(points)
                new_points = str(new_points)
                content[i] = f"{name}, {new_points}\n"
                in_content = True
        
        if in_content == False:
            print(f"Player {player_name} not found in {table}.")
        else:
            print(f"Added {points} points to {player_name} in {table}.")

    with open(table, 'w') as new_file:
        new_file.writelines(content)

def remove_points(table, player_name, points):
    with open(table) as file:
        in_content = False
        content = file.readlines()
        for i in range(len(content)):
            name, current_points = split_player_and_points(content[i])
            if name == player_name:
                new_points = current_points - int(points)
                new_points = str(new_points)
                content[i] = f"{name}, {new_points}\n"
                in_content = True
        
        if in_content == False:
            print(f"Player {player_name} not found in {table}.")
        else:
            print(f"Removed {points} points from {player_name} in {table}.")

    with open(table, 'w') as new_file:
        new_file.writelines(content)

def sort_table(table):
    with open(table) as file:
        content = file.readlines()   
        content.sort(key=lambda x: int(x.split(", ")[1]), reverse=True)

    print("The table is now sorted")

    with open(table, 'w') as new_file:
        new_file.writelines(content)
    
def compare_points(table, player1, player2):
    with open(table) as file:
        content = file.readlines()
        in_content1 = False
        in_content2 = False
        for line in content:
            name, current_points = split_player_and_points(line)
            if name == player1:
                first_player = name
                first_points = current_points
                in_content1 = True
            elif name == player2:
                second_player = name
                second_points = current_points
                in_content2 = True
        
        if in_content1 == False and in_content2 == False:
            print("Neither player found in table")
        elif in_content2 == False:
            print(f"Player {player2} not found in {table}.")
        elif in_content1 == False:
            print(f"Player {player1} not found in {table}.")
        else:
            if first_points > second_points:
                difference = first_points - second_points
                print(f'{first_player} is ahead of {second_player} by {difference} points')
            elif first_points == second_points:
                print(f'{first_player} and {second_player} are tied on points')
            else:
                difference = second_points - first_points
                print(f'{second_player} is ahead of {first_player} by {difference} points')

def display_place (table, player):
    sort_table(table)

    with open(table) as file:
        content = file.readlines()
        in_content = False

        counter = 1
        for i in range(len(content)):
            name = split_player_and_points(content[i])[0]
            if name == player:
                in_content = True
                break
            counter += 1
        
        if in_content == False:
            print(f"Player {player} not found in {table}.")
        else:
            print(f"Player {player} is in position #{counter} in the table.")



if __name__ == "__main__":

    try: 
        table_file = sys.argv[1]
        # player_name = sys.argv[2]
        # points = sys.argv[3]

        # add_player(table_file, player_name, points)

        # remove_player(table_file, player_name)

        # add_points(table_file, player_name, points)

        # remove_points(table_file, player_name, points)

        # sort_table(table_file)


        # player1 = sys.argv[2]
        # player2 = sys.argv[3]
        # compare_points(table_file, player1, player2)

        # display_place(table_file, player_name)
    except IndexError:
        print('Not the right of arguments in command, please try again')
    except FileNotFoundError:
        print(f'The file {sys.argv[1]} cannot be found')
    
