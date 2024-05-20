package pl.put.poznan.transformer.logic;

import java.util.Arrays;

/**
    * This is the Scenario class.
    * It is used to represent the scenario.
    * The scenario consists of the title, actors, system actor and steps.
 */
public class Scenario implements Element {
    /**
        * This is the title of the scenario.
     */
    public String title;

    /**
        * This is the actors of the scenario.
     */
    public String[] actors;

    /**
        * This is the system actor of the scenario.
     */
    public String systemActor;

    /**
        * This is the steps of the scenario.
     */
    public Step[] steps;

    /**
        * This is the constructor of the Scenario class.
        * @param title This is the title of the scenario.
        * @param actors This is the actors array of the scenario.
        * @param systemActor This is the system actor of the scenario.
        * @param steps This is the steps of the scenario.
     */
    public Scenario(String title, String[] actors, String systemActor, Step[] steps) {
        this.title = title;
        this.actors = actors;
        this.systemActor = systemActor;
        this.steps = steps;
    }

    /**
        * This is the toString method.
        * It is used to return the string representation of the scenario.
        * @return String This returns the string representation of the scenario.
     */
    @Override
    public String toString() {
        String steps = "";
        if (this.steps != null) {
            steps = "{";
            for (Step step : this.steps) {
                steps += step.toString();
                steps += ", ";
            }
            steps += "}";
        }
        return "Scenario{" +
                "title='" + title + '\'' +
                ", actors=" + Arrays.toString(actors) +
                ", systemActor='" + systemActor + '\'' +
                ", steps=" + steps +
                '}';
    }

    /**
        * This is the Accept method.
        * It is used to accept the visitor.
        * @param visitor This is the visitor that the scenario accepts.
     */
    @Override
    public void Accept(Visitor visitor){
        visitor.Visit(this);
    }
}
