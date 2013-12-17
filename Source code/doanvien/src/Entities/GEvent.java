package Entities;


public class GEvent {

    private String date;
    private int sum;

    public GEvent(String date, int sum) {
        this.date = date;
        this.sum = sum;
    }

    public GEvent(){
        }

    public String GDate() {
        return date;
    }

    public int GSum() {
        return sum;
    }

  

    public void setDate(String date) {
        this.date = date;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

    

