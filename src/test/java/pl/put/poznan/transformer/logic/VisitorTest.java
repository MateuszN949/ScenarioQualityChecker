package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitorTest {
    private StepsCounter stepsCounter;
    private ConditionalDecisionsCounter conditionalDecisionsCounter;
    private StepsPruner stepsPruner;
    private Scenario scenario;

    @Test
    public void testVisitCounter() throws CloneNotSupportedException {
        StepsCounter mock = mock(StepsCounter.class);
        scenario = new Scenario(null, null, null, null);

        scenario.Accept(mock);
        verify(mock, times(1)).Visit(any(Scenario.class));
    }

    @Test
    public void testVisitConditions() throws CloneNotSupportedException {
        ConditionalDecisionsCounter mock = mock(ConditionalDecisionsCounter.class);
        scenario = new Scenario(null, null, null, null);

        scenario.Accept(mock);
        verify(mock, times(1)).Visit(any(Scenario.class));
    }

    @Test
    public void testVisitPruner() throws CloneNotSupportedException {
        StepsPruner mock = mock(StepsPruner.class);
        scenario = new Scenario(null, null, null, null);

        scenario.Accept(mock);
        verify(mock, times(1)).Visit(any(Scenario.class));
    }

    @Test
    public void testCounting() throws CloneNotSupportedException {
        stepsCounter = new StepsCounter();

        Step step1 = new Step(null, null, null);
        Step step2 = new Step(null, null, new Step[]{step1, step1});
        Step step3 = new Step(null, null, new Step[]{step2, step1});
        scenario = new Scenario(null, null, null, new Step[]{step1, step2, step3});

        scenario.Accept(stepsCounter);
        assertEquals(9, stepsCounter.getSumSteps());
    }

    @Test
    public void testCondition() throws CloneNotSupportedException {
        conditionalDecisionsCounter = new ConditionalDecisionsCounter();

        Step step0 = new Step("K", null, null);
        Step step1 = new Step("IF", null, new Step[]{});
        Step step2 = new Step(null, null, new Step[]{step1, step0});
        Step step3 = new Step("ELSE", null, new Step[]{step2, step1});
        scenario = new Scenario("ELSE", null, null, new Step[]{step1, step2, step3});

        scenario.Accept(conditionalDecisionsCounter);
        assertEquals(5, conditionalDecisionsCounter.getSumAllConditionalDecisions());
    }

    @Test
    public void testCompletePruner() throws CloneNotSupportedException {
        stepsPruner = new StepsPruner(0);

        Step step0 = new Step(null, null, null);
        scenario = new Scenario(null, null, null, new Step[]{step0});

        scenario.Accept(stepsPruner);
        assertNull(stepsPruner.getResult().steps);
    }

    @Test
    public void testPruner() throws CloneNotSupportedException {
        stepsPruner = new StepsPruner(1);

        Step step0 = new Step(null, null, null);
        Step step1 = new Step(null, null, new Step[]{step0});
        Step step2 = new Step(null, null, new Step[]{step1, step0});
        Step step3 = new Step(null, null, new Step[]{step2, step1});
        scenario = new Scenario(null, null, null, new Step[]{step0, step1, step2, step3});

        scenario.Accept(stepsPruner);
        assertNull(stepsPruner.getResult().steps[3].steps);
    }
}