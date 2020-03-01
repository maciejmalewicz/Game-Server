package macior.strategygame.service.utilities;

import java.util.Date;

public class IDData {

    private int id;
    private Date date;

    public IDData(){

    }

    @Override
    public String toString(){
        return id + " added at: " + date;
    }

    public IDData(int id){
        this.id = id;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void updateId(int newId){
        this.id = newId;
    }
}
