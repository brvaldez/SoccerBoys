package Model;

public class Coach implements Observer{
    private String name;

    public Coach(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public void update(String event) {
        System.out.println(name + ", new deal available " + event);
    }
    @Override
    public String toString() {
        return name;
    }
}
