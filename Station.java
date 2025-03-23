import java.util.ArrayList;
public class Station {
   protected String Line; //metro line
    protected String Name; // name of station
    protected Station next; // true if station is in service 
    protected Station prev; // previous station in line
    protected Boolean isOpen; // next station on line
   //constructor
    public Station(String line, String name) {
        Line = line;
        Name = name;
        next = null;
        prev = null;
        isOpen =true;
    }

   //connect this station to another as next
    public void addNext(Station Next) {
      next = Next;
        Next.prev = this;
    }

   //connect this station to another as previous
    public void addPrev(Station Prev) {
        prev = Prev;
        Prev.next = this;
    }

   //two-way connect
    public void connect(Station s) {
        if(s.prev == null && next == null) {
            addNext(s);
        } else {
            if(s.next ==null && prev == null) {
                addPrev(s);
            }
        }
 // when Next != null and Prev != null ??????????
    }
   //check if station is in service 
    public Boolean isAvailable() {
        return isOpen;
    }

   //service availability 
    public void switchAvailable() {
        isOpen = !isOpen;
    }

    public String toString()
    {
        return "STATION " + toStringCommon();
    }
    public String toStringCommon() {
        String s = Name + ": " + Line + " line, in service: ";
        if(isOpen)
            s += "true, ";
        else
            s += "false, ";

        s += "previous station: ";
        if(prev == null)
            s += "none";
        else
            s += prev.Name;

        s+= ", next station: ";
        if(next == null)
            s += "none";
        else
            s += next.Name;

        return s;
    }
   //checks equality based on name and line
    public Boolean equals(Station other) {
        if(other == null) return false;
        if(Name.equals(other.Name) & Line.equals(other.Line))
            return true;
        else
            return false;
    }
   //recursive tripLength method 
    public int tripLength(Station s) {
       // if(equals(s)) {
       //     return 0;
       // }
        ArrayList<Station> Visited = new ArrayList<Station>();
        Visited.add(this);
        return tripLengthRec(s,Visited);
    }

   /*helper method to avoid cycles
    public int tripLengthRec(Station s, ArrayList<Station> Visited) {
        if(equals(s)) {
           return 0;
       }
        if(next.equals(next.prev) || Visited.contains(next) ) { // end station
            return -1;
        }
        Visited.add(next);
        int len = next.tripLengthRec(s,Visited);
        if(len >= 0)
            return len +1;
        return -1;
    }*/
}
