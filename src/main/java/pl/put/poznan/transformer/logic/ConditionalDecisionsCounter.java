package pl.put.poznan.transformer.logic;

/**
    * This is the ConditionalDecisionsCounter class.
    * It is used to count the number of conditional decisions in a scenario.
    * It implements the Visitor interface.
 */
public class ConditionalDecisionsCounter implements Visitor {
    /**
     * This is the sum of all conditional decisions in the scenario.
     */
    private int sumAllConditionalDecisions;

    /**
     * This is the constructor of the ConditionalDecisionsCounter class.
     */
    public ConditionalDecisionsCounter() {
        this.sumAllConditionalDecisions = 0;
    }
    /**
     * This is the CountDecisions method.
     * It is used to count the number of conditional decisions in a step.
     * @param step This is the step in which the conditional decisions are counted.
     * @return int This returns the number of conditional decisions in the step.
     */
    private int CountDecisions(Step step) {
        int decisionsCount = 0;

        if (step.keyword != null && (step.keyword.equals("IF") || step.keyword.equals("ELSE"))) {
            decisionsCount += 1;
        }

        if (step.steps != null) {
            for (Step subStep : step.steps) {
                decisionsCount += CountDecisions(subStep);
            }
        }

        return decisionsCount;
    }
    /**
     * This is the Visit method.
     * It is used to visit the scenario and count the number of conditional decisions in it.
     * @param scenario This is the scenario that the visitor visits.
     */
    @Override
    public void Visit(Scenario scenario) {
        this.sumAllConditionalDecisions = 0;
        if (scenario.steps != null) {
            for (Step step : scenario.steps) {
                this.sumAllConditionalDecisions += CountDecisions(step);
            }
        }
    }
    /**
     * This is the getSumAllConditionalDecisions method.
     * It is used to return the sum of all conditional decisions in the scenario.
     * @return int This returns the sum of all conditional decisions in the scenario.
     */
    public int getSumAllConditionalDecisions() {
        return sumAllConditionalDecisions;
    }
}
