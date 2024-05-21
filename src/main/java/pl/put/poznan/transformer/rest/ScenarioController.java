package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.ConditionalDecisionsCounter;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.StepsCounter;
import pl.put.poznan.transformer.logic.StepsPruner;

/**
 * This is the ScenarioController class.
 * It is used to handle the REST API requests.
 */
@RestController
@RequestMapping("/scenario")
public class ScenarioController {
    /**
     * This is the scenario.
     */
    public Scenario scenario;
    /**
     * This is the logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioController.class);

    /**
     * This is the get method.
     * It is used to return the scenario.
     * @return Scenario This returns the scenario.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Scenario get(){
        logger.info("Returning scenario");
        return this.scenario;
    }
    /**
     * This is the getStepsConditions method.
     * It is used to return the number of conditional decisions in the scenario.
     * @return int This returns the number of conditional decisions in the scenario.
     */
    @RequestMapping(value = "/steps/conditions",method = RequestMethod.GET, produces = "application/json")
    public int getStepsConditions() throws CloneNotSupportedException {
        if (scenario == null) {
            return 0;
        }

        ConditionalDecisionsCounter conditionalDecisionsCounter = new ConditionalDecisionsCounter();

        this.scenario.Accept(conditionalDecisionsCounter);

        logger.debug(String.valueOf(conditionalDecisionsCounter.getSumAllConditionalDecisions()));

        return conditionalDecisionsCounter.getSumAllConditionalDecisions();
    }
    /**
     * This is the getStepsNumber method.
     * It is used to return the number of steps in the scenario.
     * @return int This returns the number of steps in the scenario.
     */
    @RequestMapping(value = "/steps/counter",method = RequestMethod.GET, produces = "application/json")
    public int getStepsNumber() throws CloneNotSupportedException {
        if (scenario == null) {
            return 0;
        }
        StepsCounter stepsCounter = new StepsCounter();

        this.scenario.Accept(stepsCounter);

        logger.debug(String.valueOf(stepsCounter.getSumSteps()));

        return stepsCounter.getSumSteps();
    }
    /**
     * This is the getScenarioPruned method.
     * It is used to return the scenario with pruned steps.
     * @param level This is the level of the pruning.
     * @return Scenario This returns the scenario with pruned steps.
     */
    @RequestMapping(value = "/{level}",method = RequestMethod.GET, produces = "application/json")
    public Scenario getScenarioPruned(@PathVariable int level) throws CloneNotSupportedException {

        logger.debug(String.valueOf(level));
        StepsPruner stepsPruner = new StepsPruner(level);
        this.scenario.Accept(stepsPruner);

        return stepsPruner.getResult();
    }
    /**
     * This is the post method.
     * It is used to receive a new scenario.
     * @param scenario This is the new scenario.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void post(@RequestBody Scenario scenario){
//        logger.info("Received new scenario");
        logger.info("Received new scenario");
        this.scenario = scenario;
        logger.debug(this.scenario.toString());
    }
}
