package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.ConditionalDecisionsCounter;
import pl.put.poznan.transformer.logic.Scenario;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    public Scenario scenario;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Scenario get(){
        return this.scenario;
    }

    @RequestMapping(value = "/steps/number",method = RequestMethod.GET, produces = "application/json")
    public int getSteps(){
        if (scenario == null) {
            return 0;
        }

        ConditionalDecisionsCounter conditionalDecisionsCounter = new ConditionalDecisionsCounter();

        this.scenario.Accept(conditionalDecisionsCounter);

        return conditionalDecisionsCounter.getSumAllConditionalDecisions();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void post(@RequestBody Scenario scenario){
        this.scenario = scenario;
    }
}
