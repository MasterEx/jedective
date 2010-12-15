package entities;

/**
 *
 * @author Periklis Ntanasis
 */
public class CycleQ {
    
    private String[] elements;
    private int count = 0;
    
    public CycleQ() {
        elements = new String[10];
        for(int i=0;i<10;i++)
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
