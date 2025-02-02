package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

public class RecombinationCycleCrossOver<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes
    int[] child1, child2;

    public RecombinationCycleCrossOver(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind1.getNumGenes()];

        cycleCompute(ind1, ind2, child1);

        //voltar a colocar o genoma a positivo
        vetorPositivo(ind1);
        vetorPositivo(ind2);
        cycleCompute(ind2, ind1, child2);

        System.arraycopy(child1, 0, ind1.getGenome(), 0, child1.length);
        System.arraycopy(child2, 0, ind2.getGenome(), 0, child2.length);

        return;
    }

    private void cycleCompute(I ind1, I ind2, int[] child) {

        int parentValue = ind1.getGene(0);
        int cycleNumber = 1;

        int[] childAux = new int[ind1.getNumGenes()];

        int genesPreenchidos = 0;

        while (genesPreenchidos != ind1.getNumGenes()) {
            //determinar se estamos num ciclo par ou num ciclo ímpar
            if (cycleNumber % 2 == 0) {

                //atribuir ao filho o valor do parentValue
                child[ind2.getIndexof(parentValue)] = parentValue;

                //vetor auxiliar que guarda na posição do parentValue-1 o valor do mesmo
                childAux[parentValue - 1] = parentValue;

                genesPreenchidos++;

                //guardar o index da posição em questão
                int indexParentValue = ind2.getIndexof(parentValue);

                //passar os valores desse index a negativo
                ind1.setGene(indexParentValue, -ind1.getGene(indexParentValue));
                ind2.setGene(indexParentValue, -ind2.getGene(indexParentValue));

                //obter o novo parent value
                parentValue = -ind1.getGene(indexParentValue);

                //if para caso exista uma quebra de ciclo
                if (childAux[parentValue - 1] == parentValue) {
                    cycleNumber++;

                    for (int i = 0; i < ind1.getNumGenes(); i++) {
                        //procurar o próximo parent value com valor positivo
                        if (ind1.getGene(i) > 0){
                            parentValue = ind1.getGene(i);
                            break;
                        }
                    }

                }

            } else {

                child[ind1.getIndexof(parentValue)] = parentValue;
                childAux[parentValue - 1] = parentValue;

                genesPreenchidos++;

                int indexParentValue = ind1.getIndexof(parentValue);

                ind1.setGene(indexParentValue, -ind1.getGene(indexParentValue));
                ind2.setGene(indexParentValue, -ind2.getGene(indexParentValue));

                parentValue = -ind2.getGene(indexParentValue);

                if (childAux[parentValue - 1] == parentValue) {
                    cycleNumber++;

                    for (int i = 0; i < ind2.getNumGenes(); i++) {
                        if (ind2.getGene(i) > 0){
                            parentValue = ind2.getGene(i);
                            break;
                        }
                    }

                }
            }

        }
    }

    private void vetorPositivo (I ind){
        for (int i = 0; i < ind.getNumGenes(); i++) {
            ind.setGene(i,Math.abs(ind.getGene(i)));
        }
    }

    @Override
    public String toString() {
        return "COX";
    }
}