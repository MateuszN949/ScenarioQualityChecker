package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.ConditionalDecisionsCounter;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.StepsCounter;
import pl.put.poznan.transformer.logic.StepsPruner;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    public Scenario scenario;
    private static final Logger logger = LoggerFactory.getLogger(ScenarioController.class);


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Scenario get(){
        logger.info("Returning scenario");
        return this.scenario;
    }

    @RequestMapping(value = "/steps/conditions",method = RequestMethod.GET, produces = "application/json")
    public int getStepsConditions(){
        if (scenario == null) {
            return 0;
        }

        ConditionalDecisionsCounter conditionalDecisionsCounter = new ConditionalDecisionsCounter();

        this.scenario.Accept(conditionalDecisionsCounter);

        logger.debug(String.valueOf(conditionalDecisionsCounter.getSumAllConditionalDecisions()));

        return conditionalDecisionsCounter.getSumAllConditionalDecisions();
    }

    @RequestMapping(value = "/steps/counter",method = RequestMethod.GET, produces = "application/json")
    public int getStepsNumber(){
        if (scenario == null) {
            return 0;
        }
        StepsCounter stepsCounter = new StepsCounter();

        this.scenario.Accept(stepsCounter);

        logger.debug(String.valueOf(stepsCounter.getSumSteps()));

        return stepsCounter.getSumSteps();
    }

    @RequestMapping(value = "/{level}",method = RequestMethod.GET, produces = "application/json")
    public Scenario getScenarioPruned(@PathVariable int level) {

        logger.debug(String.valueOf(level));
        StepsPruner stepsPruner = new StepsPruner(level);
        this.scenario.Accept(stepsPruner);

        return stepsPruner.getResult();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void post(@RequestBody Scenario scenario){
//        logger.info("Received new scenario");
        logger.info("Received new scenario");
        this.scenario = scenario;
        logger.debug(this.scenario.toString());
    }
}
