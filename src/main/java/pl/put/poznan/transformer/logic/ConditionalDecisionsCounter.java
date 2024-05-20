package pl.put.poznan.transformer.logic;

import org.springframework.boot.autoconfigure.web.servlet.JspTemplateAvailabilityProvider;

public class ConditionalDecisionsCounter implements Visitor {
    private int sumAllConditionalDecisions;

    public ConditionalDecisionsCounter() {
        this.sumAllConditionalDecisions = 0;
    }

    private int CountDecisions(Step step) {
        int decisionsCount = 0;

        if (step.keyword.equals("IF") || step.keyword.equals("ELSE")) {
            decisionsCount += 1;
        }

        if (step.steps != null) {
            for (Step subStep : step.steps) {
                decisionsCount += CountDecisions(subStep);
            }
        }

        return decisionsCount;
    }

    @Override
    public void Visit(Scenario scenario) {
        if (scenario.steps != null) {
            for (Step step : scenario.steps) {
                this.sumAllConditionalDecisions += CountDecisions(step);
            }
        }
    }

    public int getSumAllConditionalDecisions() {
        return sumAllConditionalDecisions;
    }
}
