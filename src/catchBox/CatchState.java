package catchBox;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {

    protected int[][] matrix;
    private int lineCatch;
    private int columnCatch;
    private int numBox = 0;
    private int steps;
    private int lineDoor;
    private int columnDoor;

    public CatchState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        //percorrer a matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];

                //se a posição [i][j] for uma box
                if(this.matrix[i][j] == Properties.BOX){
                    //incrementamos o numero de boxes
                    numBox++;
                }

                //se a posição [i][j] for a posição onde o agente se encontra
                if (this.matrix[i][j] == Properties.CATCH) {
                    //guardamos as posições
                    lineCatch = i;
                    columnCatch = j;
                }

                //se a posição [i][j] for uma porta
                if(this.matrix[i][j] == Properties.DOOR){
                    //guardamos as posições
                    lineDoor = i;
                    columnDoor = j;
                }
            }
        }
    }

    public CatchState(int[][] matrix,int lineCatch,int columnCatch,int numBox) {

        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i],0,this.matrix[i],0,matrix[i].length);
        }
        this.lineCatch = lineCatch;
        this.columnCatch = columnCatch;
        this.numBox = numBox;
    }
    /*
    *
    * Estados que usem o A* e outros algoritmos deverá receber o lineCatch e o columnCatch
    * ou seja deverá existir outro construtor que não contenha aqueles if(...)
    *
    * */

    public void executeAction(Action action) {
        matrix[lineDoor][columnDoor]=Properties.DOOR;
        action.execute(this);

        steps++;
        fireUpdatedEnvironment();

    }

    public boolean canMoveUp() {
        //se não excedermos as posições da matriz e se a posição acima não for uma parede
        return (lineCatch != 0 &&(matrix[lineCatch-1][columnCatch] != Properties.WALL));
    }

    //se não excedermos as posições da matriz e se a posição à direita não for uma parede
    public boolean canMoveRight() {
        return (columnCatch != matrix.length-1 && (matrix[lineCatch][columnCatch+1] != Properties.WALL));
    }

    //se não excedermos as posições da matriz e se a posição abaixo não for uma parede
    public boolean canMoveDown() {
        return (lineCatch != matrix.length-1 && (matrix[lineCatch+1][columnCatch]!= Properties.WALL));
    }

    //se não excedermos as posições da matriz e se a posição à esquerda não for uma parede
    public boolean canMoveLeft() {
        return (columnCatch != 0 && (matrix[lineCatch][columnCatch-1]!=Properties.WALL));
    }

    public void moveUp() {
        //a posição atual fica vazia
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        //a posição do agente agora é esta
        matrix[--lineCatch][columnCatch] = Properties.CATCH;
    }

    public void moveRight() {
        //a posição atual fica vazia
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        //a posição do agente agora é esta
        matrix[lineCatch][++columnCatch] = Properties.CATCH;
    }

    public void moveDown() {
        //a posição atual fica vazia
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        //a posição do agente agora é esta
        matrix[++lineCatch][columnCatch] = Properties.CATCH;
    }

    public void moveLeft() {
        //a posição atual fica vazia
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        //a posição do agente agora é esta
        matrix[lineCatch][--columnCatch] = Properties.CATCH;
    }
    public int getNumBox() {

        return numBox;
    }

    public void setCellCatch(int line, int column) {
        //mudamos a posição do agente
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        matrix[line][column] = Properties.CATCH;

        lineCatch = line;
        columnCatch = column;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSteps() {

        return steps;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }


    public double compute (int lineGoal, int columnGoal){
        //heuristica
        return Math.abs(lineCatch-lineGoal) + Math.abs(columnCatch-columnGoal);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    public int getLineCatch() {
        return lineCatch;
    }

    public int getColumnCatch() {
        return columnCatch;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    //clone para quando já sabemos qual o número de caixas e onde está o catch
    public CatchState clone() {
        return new CatchState(matrix,lineCatch,columnCatch,numBox);
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

}
