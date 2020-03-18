package macior.strategygame.game.BoardManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private int row;
    private int column;

    public Location(@JsonProperty("row") int row, @JsonProperty("column") int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString(){
        return "row: " + row + ", column: " + column;
    }
}
