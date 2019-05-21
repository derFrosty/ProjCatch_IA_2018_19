package catchBox;

import ga.IntVectorIndividual;

public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {

    public CatchIndividual(CatchProblemForGA problem, int size) {
        super(problem, size);
    }

    public CatchIndividual(CatchIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {

        this.fitness = 0;

        Cell cell1 = problem.getCellCatch();

        Cell cell2 = problem.getCellsBoxes().get(genome[0]-1);

        for (Pair p: problem.getPairs()) {
            if(p.getCell1() == cell1 && p.getCell2()==cell2){
                fitness= p.getValue();
                break;
            }
        }


        for (int i = 0; i < genome.length - 1; i++) {

            cell1 =  problem.getCellsBoxes().get(genome[i]-1);

            cell2 = problem.getCellsBoxes().get(genome[i+1]-1);

            for (Pair p: problem.getPairs()) {
                if(p.getCell1() == cell1 && p.getCell2()==cell2 ||p.getCell1() == cell2 && p.getCell2()==cell1 ){
                    fitness+= p.getValue();
                    break;
                }
            }
        }


        cell1 =  problem.getCellsBoxes().get(genome[genome.length-1]-1);

        cell2 = problem.getDoor();

        for (Pair p: problem.getPairs()) {
            if(p.getCell1() == cell1 && p.getCell2()==cell2){
                fitness+= p.getValue();
                break;
            }
        }
        return fitness;
    }

    public int[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i <genome.length ; i++) {
            sb.append(genome[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(CatchIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }
}
