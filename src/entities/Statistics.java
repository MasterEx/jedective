package entities;

/**
 *
 * @author Periklis Ntanasis
 */
public class Statistics {

    private int cellphones = 0;
    private int possiblematches = 0;
    private int totalcellphones = 0;

    public Statistics() {
        this.cellphones = 0;
        this.possiblematches = 0;
        this.totalcellphones = 0;
    }

    public float getSuccessRatio() {
        return (float)getPossiblematches()/(float)getCellphones();
    }
    
    public int getCellphones() {
        return cellphones;
    }

    public void setCellphones(int cellphones) {
        this.cellphones = cellphones;
    }

    public void addCellphone() {
        this.cellphones++;
    }

    public int getPossiblematches() {
        return possiblematches;
    }

    public void setPossiblematches(int possiblematches) {
        this.possiblematches = possiblematches;
    }

    public void addPossibleMatch() {
        this.possiblematches++;
    }

    public int getTotalCellphones() {
        return totalcellphones;
    }

    public void setTotalCellphones(int totalcellphones) {
        this.totalcellphones = totalcellphones;
    }

    public void addTotalCellphones() {
        this.totalcellphones++;
    }

}
