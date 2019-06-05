package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class RecombinationOrderCrossOver<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;

    private int cut1;
    private int cut2;

    public RecombinationOrderCrossOver(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];
        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        } while (cut1 == cut2);

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = cut1; i <= cut2; i++) {
            child1[i] = ind2.getGene(i);
            child2[i] = ind1.getGene(i);
        }

        createChild(ind1, child1);
        createChild(ind2, child2);

        System.arraycopy(child1, 0, ind1.getGenome(), 0, child1.length);
        System.arraycopy(child2, 0, ind2.getGenome(), 0, child2.length);

        return;
    }

    private void createChild(I ind, int[] child) {
        for (int i = cut1; i <= cut2; i++) {

            for (int j = 0; j < ind.getNumGenes(); j++) {

                if (child[i] == ind.getGene(j)) {

                    ind.setGene(j, -ind.getGene(j));
                    break;
                }
            }

        }

        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (ind.getGene(i) > 0) {
                for (int k = 0; k < child.length; k++) {
                    if (child[k] == 0) {
                        child[k] = ind.getGene(i);
                        break;
                    }
                }
            }
        }

        return;

    }

    @Override
    public String toString() {
        return "OX";
    }
}