package test.com.designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taro on 06-Apr-15.
 */
public class ConcreteSubject implements  Subject {

    private List<Observer> listObserver;

    public void ConcreteSubject(){
        listObserver = new ArrayList<Observer>();
    }

    @Override
    public void attachObserver(Observer obs) {
        listObserver.add(obs);
    }

    @Override
    public void detachObserver(Observer obs) {
        listObserver.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : listObserver){
            observer.update();
        }
    }
}
