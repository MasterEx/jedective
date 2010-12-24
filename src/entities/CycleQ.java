package entities;

/**
 *
 * @author Periklis Ntanasis
 */
public class CycleQ {
    
    private String[] elements;
    private int count = 0;
    private static final int bound = 12;
    
    public CycleQ() {
        elements = new String[bound];
        for(int i=0;i<bound;i++)
            elements[i] = "empty";
    }
    
    public CycleQ(int length) {
        elements = new String[length];
        for(int i=0;i<length;i++)
            elements[i] = "empty";
    }
    
    public void add(String in) {
        if(this.count==elements.length)
            this.count = 0;
        this.elements[count] = in;
        this.count++;
    }

    public String pick(int i) {
        return elements[i];
    }

    public int size() {
        return elements.length;
    }

    @Override
    public String toString() {
        String rval = "";
        for(int i=0;i<elements.length;i++)
            rval += " "+elements[i];
        return rval;
    }

}
