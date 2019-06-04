package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class RecombinationOrderCrossOver<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2, segment1, segment2;

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

        segment1 = new int[cut2 - cut1];
        segment2 = new int[cut2 - cut1];

        for (int i = 0; i < cut2 - cut1; i++) {
            segment1[i] = ind1.getGene(i + cut1);
        }
        for (int i = 0; i < cut2 - cut1; i++) {
            segment2[i] = ind2.getGene(i + cut1);
        }

        System.arraycopy(segment1, 0, child2, cut1, segment1.length);
        System.arraycopy(segment2, 0, child1, cut1, segment2.length);

        child1 = createChild(ind1, child1);
        child2 = createChild(ind2, child2);


        System.arraycopy(child1, 0, ind1.getGenome(), 0, child1.length);
        System.arraycopy(child2, 0, ind2.getGenome(), 0, child2.length);

        return;
    }

    private int[] createChild(I ind, int[] child) {
        for (int i = 0; i < child.length; i++) {

            if (child[i] != 0) {

                for (int j = 0; j < ind.getNumGenes(); j++) {

                    if (child[i] == ind.getGene(j)) {

                        ind.setGene(j, -ind.getGene(j));
                    }
                }
            }
        }

       /* int x = 0, y = 0;
        while (true) {
            if (x < child.length && child[x] > 0) {
                x++;
            } else if (y < ind.getNumGenes() && ind.getGene(y) < 0) {
                ind.setGene(y, -ind.getGene(y));
                y++;
            } else if (x < child.length && y < ind.getNumGenes()) {
                child[x] = ind.getGene(y);
                x++;
                y++;
            } else {
                break;
            }
        }

        return child;
*/


        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (ind.getGene(i) > 0) {
                for (int k = 0; k < child.length; k++) {
                    if (child[k] == 0) {
                        child[k] = ind.getGene(i);
                    }
                }
            }else{
                ind.setGene(i, -ind.getGene(i));
            }
        }

        return child;

    }

    @Override
    public String toString() {
        return "OX";
    }
}