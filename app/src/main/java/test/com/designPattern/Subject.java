package test.com.designPattern;

/**
 * Created by taro on 06-Apr-15.
 */
public interface Subject {
    public void attachObserver(Observer obs);
    public void detachObserver(Observer obs);
    public void notifyObserver();

}
