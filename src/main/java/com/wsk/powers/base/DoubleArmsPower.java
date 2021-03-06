package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 双持能力
 */
public class DoubleArmsPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:DoubleArmsPower";
    public static final String NAME = "武器大师";

    public static String[] DESCRIPTIONS = {"装备 兵器 的数量上限为", "。已装备数量:"};

    private static final String IMG = "powers/w28.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public DoubleArmsPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + (this.amount + 1) + DESCRIPTIONS[1] + ArmsUtil.getArmsNum());
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            this.flash();
        }
        updateDescription();
    }

    @Override
    public void onVictory() {
        ArmsUtil.setArms();
    }
}