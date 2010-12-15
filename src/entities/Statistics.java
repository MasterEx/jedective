package entities;

/**
 *
 * @author Periklis Ntanasis
 */
public class Statistics {

    private int cellphones = 0;
    private int possiblematches = 0;

    public float getSuccessRatio() {
        return (float)getPossiblematches()/(float)getCellphones();
    }
    
    public int getCellphones() {
        return cellphones;
    }

    public void setCellphones(int cellphones) {
        this.cellphones = cellphones;
    }

    public int getPossiblematches() {
        return possiblematches;
    }

    public void setPossiblematches(int possiblematches) {
        this.possiblematches = possiblematches;
    }

}
