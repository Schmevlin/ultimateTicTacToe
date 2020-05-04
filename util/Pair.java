package util;

/**
 * Generic class to represent two objects
 * @param <A> Type of first object
 * @param <B> Type of second object
 * @author Andy Burris
 * @version 4 May 2020
 */
public class Pair<A, B> {

    public A first;
    public B second;

    public Pair(A first, B second){
        this.first = first;
        this.second = second;
    }
}