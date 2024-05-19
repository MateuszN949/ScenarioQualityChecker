package pl.put.poznan.transformer.logic;

public interface Element {
    public void Accept(Visitor visitor);
}
