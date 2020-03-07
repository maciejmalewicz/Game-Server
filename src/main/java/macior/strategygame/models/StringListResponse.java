package macior.strategygame.models;

import java.util.List;

public class StringListResponse extends ResponseBase{

    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}
