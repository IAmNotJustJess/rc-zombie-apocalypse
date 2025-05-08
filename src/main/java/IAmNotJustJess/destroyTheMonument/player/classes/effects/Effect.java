package IAmNotJustJess.destroyTheMonument.player.classes.effects;

import org.bukkit.potion.PotionEffect;

public class Effect {

    public EffectType effectType;
    public EffectApplicationType effectApplicationType;
    public double strength;
    public double range;
    public int tickEveryServerTicks;
    public int longevity;
    public PotionEffect potionEffect;
    public double baseStrength;
    public double baseLongevity;

    Effect(EffectType effectType, EffectApplicationType effectApplicationType, double strength, double range, int tickEveryServerTicks, int longevity) {
        this.effectType = effectType;
        this.effectApplicationType = effectApplicationType;
        this.longevity = longevity;
        this.baseLongevity = longevity;
        this.strength = strength;
        this.baseLongevity = strength;
        this.range = range;
        this.tickEveryServerTicks = tickEveryServerTicks;
        this.potionEffect = null;
    }

    Effect(EffectType effectType, EffectApplicationType effectApplicationType, double strength, double range, int tickEveryServerTicks, int longevity, PotionEffect potionEffect) {
        this.effectType = effectType;
        this.effectApplicationType = effectApplicationType;
        this.longevity = longevity;
        this.baseStrength = longevity;
        this.strength = strength;
        this.baseStrength = strength;
        this.range = range;
        this.tickEveryServerTicks = tickEveryServerTicks;
        this.potionEffect = potionEffect;
    }
}
