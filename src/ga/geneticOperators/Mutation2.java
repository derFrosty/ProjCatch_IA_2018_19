package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int g,j;

        j = GeneticAlgorithm.random.nextInt(ind.getNumGenes());

        do {
            g = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (j==g);

        ind.swapGenes(j,g);
    }

    @Override
    public String toString() {
        return "SWAP";
    }
}