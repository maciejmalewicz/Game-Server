package macior.strategygame.models.game.messages.event_messages;

import macior.strategygame.game.BoardManagement.Location;

public class ArmyTrainingEventMessage extends PostponedEventMessage {

    private int unitType;
    private int quantity;

    @Override
    protected String initializeLabel() {
        return "ARMY_TRAINING_EVENT";
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
