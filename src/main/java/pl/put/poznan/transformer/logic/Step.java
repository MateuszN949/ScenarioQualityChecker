package pl.put.poznan.transformer.logic;

public class Step {
    public String keyword;
    public String text;

    public Step[] steps;
    public Step(String keyword, String text, Step[] steps) {
        this.keyword = keyword;
        this.text = text;
        this.steps = steps;
    }

//    step to string
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
