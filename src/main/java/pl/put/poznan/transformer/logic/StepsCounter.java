package pl.put.poznan.transformer.logic;

/**
    * This is the Element interface.
    * It is used to implement the Visitor design pattern.
    * The element accepts the visitor and performs some operations on it.
 */
public class StepsCounter implements Visitor {
    /**
        * This is the sum of all steps.
     */
    private int sumAllSteps;
    /**
        * This is the constructor of the StepsCounter class.
     */
    public StepsCounter() {
        this.sumAllSteps = 0;
    }

    /**
     * This is the CountSteps method.
     * @param step This is the step in which the steps are counted.
     * @return int This returns the number of steps in the step.
     */
    private int CountSteps(Step step) {
        int stepCount = 1;

        if (step.steps != null) {
            for (Step subStep : step.steps) {
                stepCount += CountSteps(subStep);
            }
        }

        return stepCount;
    }
    /**
     * This is the Visit method.
     * It is used to visit the scenario and count the number of steps in it.
     * @param scenario This is the scenario that the visitor visits.
     */
    @Override
    public void Visit(Scenario scenario) {
        this.sumAllSteps = 0;
        if (scenario.steps != null) {
            for (Step step : scenario.steps) {
                this.sumAllSteps += CountSteps(step);
            }
        }
    }
    /**
     * This is the getSumSteps method.
     * It is used to return the sum of all steps in the scenario.
     * @return int This returns the sum of all steps in the scenario.
     */
    public int getSumSteps() {
        return sumAllSteps;
    }
}
