package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class KingTreasurePower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:KingTreasurePower";
    public static final String NAME = "王之财宝";

    public static String[] DESCRIPTIONS = {"每打出2张 兵器 ，抽1张牌。已打出", "张兵器"};

    private static final String IMG = "powers/w21.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public KingTreasurePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            this.flash();
            AbstractDungeon.player.addPower(new KingTreasurePower(this.owner, 1));
            int amount = this.owner.getPower(KingTreasurePower.POWER_ID).amount;
            if (amount >= 2) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, 1));
                //重置为0
                this.amount = 0;
                updateDescription();
//                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, KingTreasurePower.POWER_ID, amount));
            }
        }
    }
}