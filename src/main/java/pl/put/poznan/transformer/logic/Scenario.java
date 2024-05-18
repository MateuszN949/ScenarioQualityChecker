package pl.put.poznan.transformer.logic;

import java.util.Arrays;

public class Scenario {
    public String title;

    public String[] actors;

    public String systemActor;

    public Step[] steps;
    public Scenario(String title, String[] actors, String systemActor, Step[] steps) {
        this.title = title;
        this.actors = actors;
        this.systemActor = systemActor;
        this.steps = steps;
    }

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


}
