package com.wsk.powers.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 月镜盾
 */
public class MoonMirrorShieldPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:MoonMirrorShieldPower";

    private static final String IMG = "powers/11.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private AbstractCard card;

    public MoonMirrorShieldPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount * 3 + DESCRIPTIONS[1] + amount * 3 + DESCRIPTIONS[2]);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        card = c;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.card = card;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.card = null;
    }

    @Override
    public void atStartOfTurn() {
        card = null;
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        return sum(damage);
    }

    @Override
    public void onGainedBlock(float a) {

    }

    @Override
    public float modifyBlock(float a) {
        return sum(a);
    }

    private float sum(float a) {
        if (card != null) {
            flash();
            if (card.rarity == AbstractCard.CardRarity.UNCOMMON) {
                a += 3 * amount;
            } else if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.BASIC) {
                a -= 3 * amount;
            }
        }
        return a >= 0 ? a : 0;
    }
}