/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;

    public boolean equals (Location p){
        return (xCoord==p.xCoord && yCoord==p.yCoord);
    }

    public int hashcode(Location p){
    final int Prime=31;
    int result = 1;
    result = result * Prime + xCoord;
    result = result * Prime + yCoord;
    return result;
    }

    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}