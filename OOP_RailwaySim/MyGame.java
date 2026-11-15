import uk.ac.leedsbeckett.oop.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MyGame extends OOPrailwaySim
{
    private ArrayList<NewLoco> newLocos = new ArrayList<>();

    public MyGame()
    {
        Scanner sc = new Scanner(System.in);
        String choice, layout;
        System.out.println("Welcome to MyGame, please choose your track layout. Your options are -\ncross_junction\ndouble_vertical_loop\nfigure8\ngrid_network\nsimple_oval");
        choice = sc.nextLine();
        layout = "layouts/" + choice +".txt";

        super(layout); //ensure OOPrailwaySim is setup
        GameWorld world = getWorld(); //get the game world that our vehicles will go on
        setVisible(true); //make Frame visible
        System.out.println("starting");
        about();

        Scanner input = new Scanner(System.in);
        while(true)
        {
            String choice2 = input.nextLine();
            if (choice2.equalsIgnoreCase("about"))
                about();
        }
    }

    @Override
    public void about()
    {
        displayMessage("You are now in game mode, when entering this mode you can choose the track layout you want to play on.\nYour score is based upon how many locos you can have running in the sim. If a vehicle is derailed, your score will reset.\nYou are free to add a loco wherever you want to start the game. See how long you can go for. Good luck!", 2);
    }

    @Override
    public void onTick()
    {
        ArrayList<Locomotive> locomotives = getLocomotives();
        int x = 0;
        boolean derailed;


        for (Locomotive l : locomotives)
        {
            derailed = isDerailed(x);
            if (derailed)
            {
                stopSimulation();
                displayMessage("Locomotive " + (x) + " has been derailed, you must now reset the simulation", 0);
            }
            x++;
        }
    }

    public void processCommand(String panelInput)
    {
        GameWorld world = getWorld();
        DebugLevel.setLevel(0);

        System.out.println("Command typed into text field was: " + panelInput);
        panelInput = panelInput.toLowerCase();

        String[] split;
        split = panelInput.split(" ");

        int[] parameters = new int[split.length - 1];

        for (int i = 0; i < parameters.length; i++)
        {
            try
            {
                parameters[i] = Integer.parseInt(split[i + 1]);
                if (parameters[i] < 0)
                {
                    System.out.println("Cannot have a negative parameter ");
                    return;
                }
            }
            catch (NumberFormatException a)
            {
                System.out.println("Must enter an integer for the parameters");
                return;
            }
        }

        String command = split[0];
        switch (command)
        {
            case "about":
            {
                about();
                break;
            }

            case "score":
            {
                ArrayList<Locomotive> locomotives = getLocomotives();
                displayMessage("Your current score is " + locomotives.size() + "\n", 2);
                break;
            }

            case "start":
            {
                try
                {
                    if (split.length == 1)
                        startSimulation();
                    else if (split.length == 2)
                    {
                        if (isSimulationRunning())
                        {
                            startLocomotive(parameters[0]);
                            break;
                        } else
                            System.out.println("Simulation not running, cannot start this locomotive");
                    } else
                        System.out.println("Invalid number of parameters");
                } catch (GameWorldException a)
                {
                    System.out.println("Locomotive not found");
                }

                break;
            }

            case "stop":
            {
                try
                {
                    if (split.length == 1)
                        stopSimulation();
                    else if (split.length == 2)
                        stopLocomotive(parameters[0]);
                    else
                        System.out.println("Invalid number of parameters");
                } catch (GameWorldException a)
                {
                    System.out.println("Locomotive not found");
                }

                break;
            }

            case "addloco":
            {
                try
                {
                    if (split.length == 3)
                    {
                        addLocomotive(new Locomotive(world, parameters[0], parameters[1]));
                        break;
                    } else
                        System.out.println("Incorrect number of parameters");
                } catch (GameWorldException a)
                {
                    displayMessage("Cannot add locomotive at this place", 0);
                }

                break;
            }

            case "attachcarriage":
            {
                try
                {
                    if (split.length == 1)
                        System.out.println("Specify which locomotive to add a carriage");
                    else if (split.length == 2)
                    {
                        addCarriageToLocomotive(parameters[0], new Carriage(world));
                        break;
                    } else
                        System.out.println("Too many parameters");
                } catch (GameWorldException b)
                {
                    System.out.println("Locomotive not found");
                }

                break;
            }

            case "detachcarriage":
            {
                try
                {
                    if (split.length == 1)
                        System.out.println("Specify which locomotive to remove a carriage from");
                    else if (split.length == 2)
                    {
                        deleteCarriage(parameters[0]);
                        break;
                    } else
                        System.out.println("Too many parameters");
                } catch (GameWorldException b)
                {
                    System.out.println("Locomotive not found");
                }

                break;
            }

            case "speed":
            {
                try
                {
                    if (split.length == 3)
                    {
                        if (parameters[1] > 0)
                        {
                            setLocomotiveSpeed(parameters[0], parameters[1]);
                            break;
                        } else
                            System.out.println("Speed must be positive");
                    } else
                        System.out.println("Incorrect number of parameters");
                } catch (GameWorldException b)
                {
                    System.out.println("Locomotive not found");
                }

                break;
            }

            case "crossing":
            {
                try
                {
                    if (split.length == 2)
                    {
                        toggleCrossing(parameters[0]);
                    } else
                        System.out.println("Incorrect number of parameters");
                }
                catch (GameWorldException c)
                {
                    System.out.println("Crossing not found");
                }

                break;
            }

            case "reset":
            {
                if (split.length == 1)
                {
                    ArrayList<Locomotive> locomotives = getLocomotives();

                    while (!(locomotives.isEmpty()))
                    {
                        deleteCompleteLoco(1);
                    }

                    resetSimulation();
                }
                else
                    System.out.println("Incorrect number of parameters");

                break;
            }


            default:
            {
                System.out.println("Unknown command, try again");
            }
        }
    }
}
