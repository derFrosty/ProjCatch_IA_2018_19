package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.LinkedList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    private Cell goalPosition;
    private List<Action> actions;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);
        this.goalPosition = goalPosition;

        //lista de ações
        actions = new LinkedList<Action>(){{
           add(new ActionUp());
           add(new ActionLeft());
           add(new ActionDown());
           add(new ActionRight());
        }};

    }

    //qual a posição que queremos alcançar
    public Cell getGoalPosition() {
        return goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {

        List<S> successors = new LinkedList<>();

        for (Action action: actions) {
            if (action.isValid(state)){
                S successor = (S) state.clone();
                action.execute(successor);
                successors.add(successor);
            }
        }
        return successors;
    }

    //ssaber se posição onde o agente está (linha e coluna) é a goal position
    public boolean isGoal(S state) {
        return state.getLineCatch()==getGoalPosition().getLine() && state.getColumnCatch()==getGoalPosition().getColumn();
    }
}
