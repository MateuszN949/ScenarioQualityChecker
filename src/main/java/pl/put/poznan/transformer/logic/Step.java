package pl.put.poznan.transformer.logic;

/**
 * This is the Step class.
 * It is used to represent a step in a scenario.
 */
public class Step {
    /**
     * This is the keyword of the step.
     */
    public String keyword;
    /**
     * This is the text of the step.
     */
    public String text;

    /**
     * This is the steps of the step.
     */
    public Step[] steps;
    /**
     * This is the constructor of the Step class.
     * @param keyword This is the keyword of the step.
     * @param text This is the text of the step.
     * @param steps This is the steps of the step.
     */
    public Step(String keyword, String text, Step[] steps) {
        this.keyword = keyword;
        this.text = text;
        this.steps = steps;
    }

    /**
     * This is the toString method.
     * It is used to return the string representation of the step.
     * @return String This returns the string representation of the step.
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

        return "Step{" +
                "keyword='" + keyword + '\'' +
                ", text='" + text + '\'' +
                ", steps=" + steps +
                '}';
    }
}
