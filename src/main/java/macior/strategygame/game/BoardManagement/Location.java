package macior.strategygame.game.BoardManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Location {

    private int row;
    private int column;

    public Location(@JsonProperty("row") int row, @JsonProperty("col") int column){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row &&
                column == location.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
