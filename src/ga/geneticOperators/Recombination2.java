package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2, segment1, segment2;

    private int cut1;
    private int cut2;

    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];
        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        }while(cut1 == cut2);

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = cut1; i < cut2; i++) {
            segment1[i] = ind1.getGene(i);
        }
        for (int i = cut1; i < cut2; i++) {
            segment2[i] = ind2.getGene(i);
        }

        System.arraycopy(segment1,0,child2,cut1,segment1.length);
        System.arraycopy(segment2,0,child1,cut1,segment2.length);

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            for (int j = 0; j < ; j++) {
                
            }

        }



        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        //TODO
        throw new UnsupportedOperationException();
    }    
}