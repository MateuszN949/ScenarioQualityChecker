package pl.put.poznan.transformer.logic;

public class StepsCounter implements Visitor {
    private int sumAllSteps;

    StepsCounter() {
        this.sumAllSteps = 0;
    }

    private int CountSteps(Step step) {
        int stepCount = 1;
        for (Step subStep : step.steps) {
            stepCount += CountSteps(subStep);
        }

        return stepCount;
    }

    @Override
    public void Visit(Scenario scenario) {
        for (Step step : scenario.steps) {
            this.sumAllSteps += CountSteps(step);
        }
    }

    public int getSumSteps() {
        return sumAllSteps;
    }
}
