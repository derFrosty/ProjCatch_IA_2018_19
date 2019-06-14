package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class MutationScramble<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    int cut1,cut2;

    public MutationScramble(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (cut1 == cut2);

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = 0; i < Math.round((cut2-cut1)/2); i++) {
            int g,j;

            j = GeneticAlgorithm.random.nextInt(cut2-cut1+1)+cut1;

            do {
                g = GeneticAlgorithm.random.nextInt(cut2-cut1+1)+cut1;
            }while (j==g);

            ind.swapGenes(g,j);
        }



    }

    @Override
    public String toString() {
        return "Scramble";
    }
}