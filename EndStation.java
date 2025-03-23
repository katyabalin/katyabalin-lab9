public class EndStation extends Station {

    public EndStation(String line, String name) {
        super(line, name);
    }

    public String toString()
    {
        return "ENDSTATION " + toStringCommon();
    }

    public void makeEnd() {
        if((next == null) & (prev == null))
            return;
        if(next != null)
            prev = next;
        else
            next = prev;
    }
}
