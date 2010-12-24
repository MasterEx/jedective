package entities;

/**
 * Jedective - a text parser
 * Copyright (C) 2010-2011 Periklis Ntanasis
 *
 * Licensed under GPL license : http://www.gnu.org/licenses/gpl.html
 *
 */

/**
 *
 * @author Periklis Ntanasis
 */
public class Statistics {

    private int cellphones = 0;
    private int possiblematches = 0;
    private int totalwebpages = 0;

    public Statistics() {
        this.cellphones = 0;
        this.possiblematches = 0;
        this.totalwebpages = 0;
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

    public int gettotalwebpages() {
        return totalwebpages;
    }

    public void settotalwebpages(int totalwebpages) {
        this.totalwebpages = totalwebpages;
    }

    public void addtotalwebpages() {
        this.totalwebpages++;
    }

}
