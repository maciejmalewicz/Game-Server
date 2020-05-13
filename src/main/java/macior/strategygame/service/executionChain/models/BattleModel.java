package macior.strategygame.service.executionChain.models;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.RatioSet;

public class BattleModel {
    public Attack ATTACK;

    public StrengthSet ATTACKERS_STRENGTH_SET;
    public RatioSet ATTACKERS_BONUS_ATTACK_RATIOS;
    public RatioSet ATTACKERS_BONUS_DEFENCE_RATIOS;


    public int ATTACKERS_BASE_DEFENCE;

    public int ATTACKERS_ATTACK;
    public int ATTACKERS_DEFENCE;

    public int DEFENDERS_BASE_DEFENCE;

    public int DEFENDERS_ATTACK;
    public int DEFENDERS_DEFENCE;

    public boolean ATTACKER_HAS_WON;
    public double BATTLE_RESULT;
    public double DAMAGE_RATIO;
}
