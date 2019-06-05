package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {

        int a = 3;
        int cycleValue = a;
        int cycleNumber = 1;

        int[] child1 = new int[ind1.getNumGenes()];

        int genesPreenchidos = 0;

        while (genesPreenchidos != ind1.getNumGenes()) {

            for (int j = 0; j < ind1.getNumGenes(); j++) {
                if (ind1.getGene(j) == a){
                    if (cycleNumber%2 == 0){

                    }else{
                        child1[j] = a;
                        a = ind2.getGene(j);
                        genesPreenchidos++;
                        ind1.setGene(j,-ind1.getGene(j));

                        if(cycleValue == a){
                            cycleNumber++;
                        }
                    }

                    break;
                }
            }
        }

    }

    @Override
    public String toString(){
        //TODO
        throw new UnsupportedOperationException();
    }    
}