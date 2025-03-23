// represents a metro station at the end of a line 
public class EndStation extends Station {

    // sets the name and line for the end station 
    public EndStation(String line, String name) {
        super(line, name);
    }

    //returns string summary of the station
    public String toString()
    {
        return "ENDSTATION " + toStringCommon();
    }

    //updates the connections so that both prev and next refer to the same station
    public void makeEnd() {
        if((next == null) & (prev == null))
            return;
        if(next != null)
            prev = next;
        else
            next = prev;
    }
}
