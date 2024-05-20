package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.ConditionalDecisionsCounter;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.StepsCounter;
import pl.put.poznan.transformer.logic.StepsPruner;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    public Scenario scenario;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Scenario get(){
        return this.scenario;
    }

    @RequestMapping(value = "/steps/conditions",method = RequestMethod.GET, produces = "application/json")
    public int getStepsConditions(){
        if (scenario == null) {
            return 0;
        }

        ConditionalDecisionsCounter conditionalDecisionsCounter = new ConditionalDecisionsCounter();

        this.scenario.Accept(conditionalDecisionsCounter);

        return conditionalDecisionsCounter.getSumAllConditionalDecisions();
    }

    @RequestMapping(value = "/steps/counter",method = RequestMethod.GET, produces = "application/json")
    public int getStepsNumber(){
        if (scenario == null) {
            return 0;
        }
        StepsCounter stepsCounter = new StepsCounter();

        this.scenario.Accept(stepsCounter);

        return stepsCounter.getSumSteps();
    }

    @RequestMapping(value = "/{level}",method = RequestMethod.GET, produces = "application/json")
    public Scenario getScenarioPruned(@PathVariable int level) {

        StepsPruner stepsPruner = new StepsPruner(level);
        this.scenario.Accept(stepsPruner);

        return stepsPruner.getResult();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void post(@RequestBody Scenario scenario){
        this.scenario = scenario;
    }
}
