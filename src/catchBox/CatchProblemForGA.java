package catchBox;

import ga.Problem;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes

    private LinkedList<Cell> cellsBoxes;
    private LinkedList<Pair> pairs;
    private Cell cellCatch;
    private Cell door;

    public CatchProblemForGA(
            LinkedList<Cell> cellsBoxes,
            LinkedList<Pair> pairs,
            Cell cellCatch,
            Cell door) {

        if(cellsBoxes == null || cellCatch == null || door == null){
            throw new IllegalArgumentException();
        }

        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;
    }

    public LinkedList<Cell> getCellsBoxes() {
        return cellsBoxes;
    }

    public Cell getCellCatch() {
        return cellCatch;
    }

    public Cell getDoor() {
        return door;
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }

    @Override
    public CatchIndividual getNewIndividual() {
        return new CatchIndividual(this,this.cellsBoxes.size());
    }

    @Override
    public String toString() {
        //TODO
        throw new UnsupportedOperationException();
    }
}
