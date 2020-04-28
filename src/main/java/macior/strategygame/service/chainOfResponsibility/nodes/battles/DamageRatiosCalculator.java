package macior.strategygame.service.chainOfResponsibility.nodes.battles;

import macior.strategygame.game.RatioSet;

public class DamageRatiosCalculator {

    public RatioSet getDamageRatios(RatioSet defenceRatios, double damageDone){
        //if nothing would be done, this would be damage ratios
        RatioSet damageRatios = defenceRatios.getMultiplyBy(damageDone);
        applyCannonsAbsorption(defenceRatios, damageRatios);
        applyDroidsAbsorbtion(defenceRatios, damageRatios);
        applyTanksAbsorbtion(defenceRatios, damageRatios);
        return damageRatios;
    }

    private void applyCannonsAbsorption(RatioSet defenceRatios, RatioSet damageRatios){
        //how much damage could cannons possibly take
        double cannonsDamageCapacity = defenceRatios.thirdRatio - damageRatios.thirdRatio;
        //how much damage could be possibly taken from tanks and droids
        double remainingDamageToAbsorb = damageRatios.firstRatio + damageRatios.secondRatio;

        double maxCapacity = Math.min(cannonsDamageCapacity, remainingDamageToAbsorb);
        double cannonsDamageAbsorbtion = generateUnder(maxCapacity); //randomize damage taking

        double damageAbsorbedFromDroids = cannonsDamageAbsorbtion*(damageRatios.firstRatio/
                (damageRatios.firstRatio+damageRatios.secondRatio));
        double damageAbsorbedFromTanks = cannonsDamageAbsorbtion*(damageRatios.secondRatio /
                (damageRatios.firstRatio+damageRatios.secondRatio));

        damageRatios.firstRatio -= damageAbsorbedFromDroids;
        damageRatios.secondRatio -= damageAbsorbedFromTanks;
        damageRatios.thirdRatio += (damageAbsorbedFromDroids + damageAbsorbedFromTanks);
    }

    private void applyDroidsAbsorbtion(RatioSet defenceRatios, RatioSet damageRatios){
        //how much damage could droids possibly take
        double droidsDamageCapacity = defenceRatios.firstRatio - damageRatios.firstRatio;
        //how much damage could be possibly taken from tanks and cannons
        double remainingDamageToAbsorb = damageRatios.thirdRatio + damageRatios.secondRatio;

        double maxCapacity = Math.min(droidsDamageCapacity, remainingDamageToAbsorb);
        double droidsDamageAbsorbtion = generateUnder(maxCapacity); //randomize damage taking

        double damageAbsorbedFromTanks = droidsDamageAbsorbtion*(damageRatios.secondRatio /
                (damageRatios.secondRatio +damageRatios.thirdRatio));
        double damageAbsorbedFromCannons = droidsDamageAbsorbtion*(damageRatios.thirdRatio/
                (damageRatios.thirdRatio+damageRatios.secondRatio));

        damageRatios.secondRatio -= damageAbsorbedFromTanks;
        damageRatios.thirdRatio -= damageAbsorbedFromCannons;
        damageRatios.firstRatio += (damageAbsorbedFromCannons + damageAbsorbedFromTanks);
    }

    private void applyTanksAbsorbtion(RatioSet defenceRatios, RatioSet damageRatios){
        //how much damage could tanks possibly take
        double tanksDamageCapacity = defenceRatios.secondRatio - damageRatios.secondRatio;
        //how much damage could be possibly taken from droids and cannons
        double remainingDamageToAbsorb = damageRatios.thirdRatio + damageRatios.firstRatio;

        double maxCapacity = Math.min(tanksDamageCapacity, remainingDamageToAbsorb);
        double tanksDamageAbsorbtion = generateUnder(maxCapacity); //randomize damage taking

        double damageAbsorbedFromDroids = tanksDamageAbsorbtion*(damageRatios.firstRatio /
                (damageRatios.firstRatio +damageRatios.thirdRatio));
        double damageAbsorbedFromCannons = tanksDamageAbsorbtion*(damageRatios.thirdRatio/
                (damageRatios.thirdRatio+damageRatios.firstRatio));

        damageRatios.firstRatio -= damageAbsorbedFromDroids;
        damageRatios.thirdRatio -= damageAbsorbedFromCannons;
        damageRatios.secondRatio += (damageAbsorbedFromCannons + damageAbsorbedFromDroids);
    }

    public double generateUnder(double max){
        return Math.random()*max;
    }
}
