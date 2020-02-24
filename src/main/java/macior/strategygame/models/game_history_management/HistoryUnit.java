package macior.strategygame.models.game_history_management;

import java.util.Date;

public class HistoryUnit {

    private String player1Login;
    private String player2Login;
    private String player3Login;
    private String player4Login;

    private double player1Result;
    private double player2Result;
    private double player3Result;
    private double player4Result;

    private Date date;

    public String getPlayer1Login() {
        return player1Login;
    }

    public void setPlayer1Login(String player1Login) {
        this.player1Login = player1Login;
    }

    public String getPlayer2Login() {
        return player2Login;
    }

    public void setPlayer2Login(String player2Login) {
        this.player2Login = player2Login;
    }

    public String getPlayer3Login() {
        return player3Login;
    }

    public void setPlayer3Login(String player3Login) {
        this.player3Login = player3Login;
    }

    public String getPlayer4Login() {
        return player4Login;
    }

    public void setPlayer4Login(String player4Login) {
        this.player4Login = player4Login;
    }

    public double getPlayer1Result() {
        return player1Result;
    }

    public void setPlayer1Result(double player1Result) {
        this.player1Result = player1Result;
    }

    public double getPlayer2Result() {
        return player2Result;
    }

    public void setPlayer2Result(double player2Result) {
        this.player2Result = player2Result;
    }

    public double getPlayer3Result() {
        return player3Result;
    }

    public void setPlayer3Result(double player3Result) {
        this.player3Result = player3Result;
    }

    public double getPlayer4Result() {
        return player4Result;
    }

    public void setPlayer4Result(double player4Result) {
        this.player4Result = player4Result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
