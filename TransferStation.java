import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TransferStation extends Station {
    ArrayList<Station> otherStations;
    public TransferStation(String line, String name) {
        super(line, name);
        otherStations = new ArrayList<Station>();
    }

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
    public void addTransferStationPrev(Station s) {
        s.next = this;
        otherStations.add(s);
    }
    public void addTransferStationNext(Station s) {
        s.prev = this;
        otherStations.add(s);
    }

    public int tripLengthRec(Station s, ArrayList<Station> Visited) {
        if(equals(s)) {
            return 0;
        }
        if(!Visited.contains(next)) {
            Visited.add(next);
            int len = next.tripLengthRec(s, Visited);
            if (len >= 0)
                return len + 1;
        }
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
        return -1;

    }
}
