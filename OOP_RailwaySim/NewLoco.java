import uk.ac.leedsbeckett.oop.*;

public class NewLoco extends Locomotive
{
    protected int newLocoID;
    protected static int nextLocoID = 1;

    public NewLoco(GameWorld world, int x, int y)
    {
        super(world, x, y, inferOrientationFromTrack(world, new Position(x, y)));
        setAnimationStepPixels(4);
        newLocoID = nextLocoID;
        incrementNewLocoID();
    }

    private void incrementNewLocoID()
    {
        nextLocoID++;
    }

    @Override
    public String toString()
    {
        return "New Loco, New Loco ID: " + this.newLocoID + " New Loco Position " + getCellPosition();
    }

    public int getNewLocoID()
    {
        return newLocoID;
    }

    public void setNewLocoID(int newLocoID)
    {
        this.newLocoID = newLocoID;
    }
}
