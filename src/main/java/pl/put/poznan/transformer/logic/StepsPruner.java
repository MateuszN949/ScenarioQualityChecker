package pl.put.poznan.transformer.logic;

public class StepsPruner implements Visitor {
    private int depth;
    Scenario result;

    public StepsPruner(int depth) {
        this.depth = depth;
    }

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

    public Scenario getResult() {
        return this.result;
    }
}
