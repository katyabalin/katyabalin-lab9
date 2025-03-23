import java.util.ArrayList;
import java.util.concurrent.Callable;

//a station that connects multiple metro lines through transfers 
public class TransferStation extends Station {
    //stores all of the extra transfer connections from this station
    ArrayList<Station> otherStations;
    //sets up transfer station with its name, line, and transfer list 
    public TransferStation(String line, String name) {
        super(line, name);
        otherStations = new ArrayList<Station>();
    }
    //returns a string with the station's main info and all transfer connections
    public String toString()
    {
       String s = "TRANSFERSTATION " + toStringCommon() + "\n\tTransfers: \n";
       if(otherStations.isEmpty())
         return s;

        for(Station st : otherStations) {
            s += "\t" + st.toString() + "\n";
        }
       return s;
    }
    //adds a station that transfers into this one 
    public void addTransferStationPrev(Station s) {
        s.next = this;
        otherStations.add(s);
    }
    //adds a station that transfers out from this one 
    public void addTransferStationNext(Station s) {
        s.prev = this;
        otherStations.add(s);
    }

    //recursively calculates how many stops from this station to the destination 
    public int tripLengthRec(Station s, ArrayList<Station> Visited) {
        if(equals(s)) {
            return 0;
        }
        //try to go to the next station if it hasn't been visited yet 
        if(!Visited.contains(next)) {
            Visited.add(next);
            int len = next.tripLengthRec(s, Visited);
            if (len >= 0)
                return len + 1;
        }
        //explore all transfer stations 
        for(Station st : otherStations) {
            if(st.equals(s)) {
                return 1;
            }
            if(!Visited.contains(st)) {
                Visited.add(st);
                if(!this.equals(st.next)) {
                    int lenSt = st.next.tripLengthRec(s,Visited);
                    if(lenSt >=0)
                        return lenSt +2;
                }
            }
        }
        //no path to the destation was found 
        return -1;

    }
}
