package pl.put.poznan.transformer.logic;

/**
 * This is the StepsPruner class.
 * It is used to prune the steps of a scenario.
 */
public class StepsPruner implements Visitor {
    /**
     * This is the depth of the pruning.
     */
    private int depth;
    /**
     * This is the result of the pruning.
     */
    Scenario result;
    /**
     * This is the constructor of the StepsPruner class.
     * @param depth This is the depth of the pruning.
     */
    public StepsPruner(int depth) {
        this.depth = depth;
    }
    /**
     * This is the prune method.
     * It is used to prune the steps of a step.
     * @param step This is the step in which the steps are pruned.
     * @param currDepth This is the current depth of the pruning.
     */
    private void prune(Step step, int currDepth) {
        if (step.steps != null) {
            if (currDepth == this.depth) {
                step.steps = null;
            }
            else {
                for (Step substep : step.steps) {
                    prune(substep, currDepth + 1);
                }
            }
        }
    }
    /**
     * This is the Visit method.
     * It is used to visit the scenario and prune the steps in it.
     * @param scenario This is the scenario that the visitor visits.
     */
    @Override
    public void Visit(Scenario scenario) {
        this.result = scenario;
        if (this.depth <= 0) {
            this.result.steps = null;
        }
        else if (this.result.steps != null) {
            for (Step step : this.result.steps) {
                prune(step, 1);
            }
        }
    }
    /**
     * This is the getResult method.
     * It is used to return the result of the pruning.
     * @return Scenario This returns the result of the pruning.
     */
    public Scenario getResult() {
        return this.result;
    }
}
