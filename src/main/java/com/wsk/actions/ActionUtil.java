package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.wsk.powers.ImprintPower;
import com.wsk.powers.NextEnergizedPower;
import com.wsk.powers.VictoryPower;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 一句话说明
 */
public class ActionUtil {

    //给予死亡印记
    public static void imprintPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new ImprintPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //给予中毒
    public static void poisonPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new PoisonPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //获得格挡
    public static void gainBlockAction(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(from, from, amount));
    }

    //给予虚弱
    public static void weakPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new WeakPower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //改变力量
    public static void strengthPower(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(from, from,
                new StrengthPower(from, amount), amount, AbstractGameAction.AttackEffect.POISON));
    }

    //获得敏捷
    public static void dexterityPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, amount), amount));
    }

    //获得壁垒
    public static void barricadePower(AbstractCreature p) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
    }

    //给予易伤
    public static void vulnerablePower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new VulnerablePower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //获得荆棘
    public static void thornsPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ThornsPower(p, amount), amount, AbstractGameAction.AttackEffect.POISON));
    }

    //获得多层护甲
    public static void platedArmorPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PlatedArmorPower(p, amount), amount, AbstractGameAction.AttackEffect.POISON));
    }

    //下回合抽牌
    public static void nextEnergizedPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextEnergizedPower(p, amount), amount));
    }

    //获得胜利契约
    public static void victoryPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new VictoryPower(p, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //锻造
    public static void forgingAction(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ForgingAction(from, amount));
    }

}
