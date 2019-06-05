package ga;


import java.util.Arrays;

public abstract class IntVectorIndividual<P extends Problem, I extends IntVectorIndividual> extends Individual<P, I> {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[] genome;

    public IntVectorIndividual(P problem, int size) {
        super(problem);
        genome = new int[size];
        boolean f;
        int j;
        for (int i = 0; i < size; i++) {
            do {
                j = GeneticAlgorithm.random.nextInt(size) + 1;
                f = false;

                for (int k = 0; k < i; k++) {
                    if (genome[k] == j)
                        f = true;
                }
            } while (f);

            genome[i] = j;
        }
    }

    public IntVectorIndividual(IntVectorIndividual<P, I> original) {
        super(original);
        this.genome = new int[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
        System.out.println(Arrays.toString(genome));
    }

    @Override
    public int getNumGenes() {
        return genome.length;
    }

    public int getIndexof(int value) {
        for (int i = 0; i < genome.length; i++) {
            if (genome[i] == value)
                return i;
        }
        return -1;
    }

    public int getGene(int index) {
        return genome[index];
    }

    public void setGene(int index, int newValue) {
        genome[index] = newValue;
    }

    @Override
    public void swapGenes(IntVectorIndividual other, int index) {
        int aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;
    }

    public void swapGenes(int indexA, int indexB) {
        int aux = genome[indexA];
        genome[indexA] = genome[indexB];
        genome[indexB] = aux;
    }

    public int[] getGenome() {
        return genome;
    }
}
