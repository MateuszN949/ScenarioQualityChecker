package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import mockito
import static org.mockito.Mockito.*;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioControllerTest {
    ScenarioController scenarioController;

    @BeforeEach
    void setUp() {
        scenarioController = new ScenarioController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetReturnsScenario() {
        Scenario scenario = new Scenario(null, null, null, null);
        scenarioController.scenario = scenario;
        assertEquals(scenario, scenarioController.get());
    }

    @Test
    void testPostScenario() {
        // scenario mock
        Scenario scenario = mock(Scenario.class);

        // test post
        scenarioController.post(scenario);
        assertEquals(scenario, scenarioController.scenario);
    }

    @Test
    void testGetStepNumberReturnsZeroWhenNull() throws CloneNotSupportedException {
        assertEquals(0, scenarioController.getStepsConditions());
    }

    @Test
    void testGetStepNumberAcceptCalled() throws CloneNotSupportedException {
        Scenario scenario = mock(Scenario.class);
        scenarioController.scenario = scenario;
        scenarioController.getStepsNumber();
        verify(scenario).Accept(any());
    }

    @Test
    void getScenarioPrunedCallsScenarioAccept() throws CloneNotSupportedException {
        Scenario scenario = mock(Scenario.class);
        scenarioController.scenario = scenario;
        scenarioController.getScenarioPruned(1);
        verify(scenario).Accept(any());
    }

    @Test
    void testGetScenarioPrunedPruns() throws CloneNotSupportedException {
        Step step1 = new Step(null, null, null);
        // array with step1 in it
        Step[] steps = { step1 };
        Step step2 = new Step(null, null, steps);

        Scenario scenario = new Scenario(null, null, null, new Step[] { step2 });
        scenarioController.scenario = scenario;

        assertEquals(2, scenarioController.getStepsNumber());

        scenarioController.getScenarioPruned(1);
        assertEquals(1, scenarioController.getStepsNumber());
    }

    @Test
    void testGetStepsConditionsReturnsZeroWhenNull() throws CloneNotSupportedException {
        assertEquals(0, scenarioController.getStepsConditions());
    }

    @Test
    void testGetStepsConditionsReturnsCorrectValue() throws CloneNotSupportedException {
        Step step1 = new Step(null, null, null);
        // array with step1 in it
        Step[] steps = { step1 };
        Step step2 = new Step("IF", null, steps);

        Scenario scenario = new Scenario(null, null, null, new Step[] { step2 });
        scenarioController.scenario = scenario;

        assertEquals(1, scenarioController.getStepsConditions());
    }

    @Test
    void testGetStepsConditionsReturnsZeroWhenNoIfs() throws CloneNotSupportedException {
        Step step1 = new Step(null, null, null);
        // array with step1 in it
        Step[] steps = { step1 };
        Step step2 = new Step("FOR", null, steps);

        Scenario scenario = new Scenario(null, null, null, new Step[] { step2 });
        scenarioController.scenario = scenario;

        assertEquals(0, scenarioController.getStepsConditions());
    }

    @Test 
    void testGetStepsConditionsCallsAccept() throws CloneNotSupportedException {
        Scenario scenario = mock(Scenario.class);

        scenarioController.scenario = scenario;

        scenarioController.getStepsConditions();

        verify(scenario).Accept(any());
    }

    @Test
    void testPostLogger() {
        Scenario scenario = mock(Scenario.class);
        ScenarioController.logger = mock(org.slf4j.Logger.class);
        scenarioController.post(scenario);
        verify(scenarioController.logger).info("Received new scenario");
    }

    @Test
    void testGetLogger() {
        Scenario scenario = mock(Scenario.class);
        scenarioController.scenario = scenario;
        ScenarioController.logger = mock(org.slf4j.Logger.class);
        scenarioController.get();
        verify(scenarioController.logger).info("Returning scenario");
    }

    @Test
    void testGetStepsConditionsLogger() throws CloneNotSupportedException {
        Scenario scenario = mock(Scenario.class);

        scenarioController.scenario = scenario;
        ScenarioController.logger = mock(org.slf4j.Logger.class);

        scenarioController.getStepsConditions();
        verify(scenarioController.logger).debug("0");
    }

}
