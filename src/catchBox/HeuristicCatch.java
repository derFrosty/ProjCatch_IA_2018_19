package catchBox;

import agentSearch.Heuristic;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        //chamamos a função que calcula a heuristica
        return state.compute(problem.getGoalPosition().getLine(),problem.getGoalPosition().getColumn());

    }

    @Override
    public String toString() {
        //TODO
        throw new UnsupportedOperationException();
    }
}