package pl.put.poznan.transformer.logic;

/*
    * This is the Visitor interface.
    * It is used to implement the Visitor design pattern.
    * The visitor visits the scenario and performs some operations on it.
 */
public interface Visitor {
    /*
        * This is the Visit method.
        * It is used to visit the scenario and perform some operations on it.
        * @param scenario This is the scenario that the visitor visits.
     */
    public void Visit(Scenario scenario);
}
