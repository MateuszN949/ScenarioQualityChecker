package pl.put.poznan.transformer.logic;

public class ConditionalDecisionsCounter implements Visitor {
    private int sumAllConditionalDecisions;

    ConditionalDecisionsCounter() {
        this.sumAllConditionalDecisions = 0;
    }

    private int CountDecisions(Step step) {
        int decisionsCount = 0;

        if (step.text.startsWith("IF") || step.text.startsWith("ELSE")) {
            decisionsCount += 1;
        }

        for (Step subStep : step.steps) {
            decisionsCount += CountDecisions(subStep);
        }

        return decisionsCount;
    }

    @Override
    public void Visit(Scenario scenario) {
        for (Step step : scenario.steps) {
            this.sumAllConditionalDecisions += CountDecisions(step);
        }
    }

    public int getSumAllConditionalDecisions() {
        return sumAllConditionalDecisions;
    }
}
