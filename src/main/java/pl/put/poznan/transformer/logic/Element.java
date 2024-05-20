package pl.put.poznan.transformer.logic;

/**
    * This is the Element interface.
    * It is used to implement the Visitor design pattern.
    * The element accepts the visitor and performs some operations on it.
 */
public interface Element {
    /**
        * This is the Accept method.
        * It is used to accept the visitor and perform some operations on it.
        * @param visitor This is the visitor that the element accepts.
     */
    public void Accept(Visitor visitor);
}
