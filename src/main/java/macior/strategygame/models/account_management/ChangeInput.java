package macior.strategygame.models.account_management;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeInput {

    private String text;

    public ChangeInput(@JsonProperty("text") String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
