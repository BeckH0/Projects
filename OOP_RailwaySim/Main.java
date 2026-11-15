import uk.ac.leedsbeckett.oop.*;
import java.util.Scanner;

public class Main extends MyRailway
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Please select an option\nOption 1 - Demo for project\nOption 2 - Game mode");
        choice = input.nextInt();

        if(choice == 1)
            new MyRailway();
        else if (choice == 2)
            new MyGame();
        else
            System.out.println("Try again");
    }
}