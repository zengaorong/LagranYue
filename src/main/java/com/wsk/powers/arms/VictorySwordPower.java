package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.actions.ActionUtil;
import com.wsk.powers.base.VictoryPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：誓约胜利之剑
 */
public class VictorySwordPower extends AbstractSwordPower {
    public static final String POWER_ID = "LagranYue:VictorySwordPower";
    public static final String NAME = "兵器：誓约胜利之剑";

    public static String[] DESCRIPTIONS = {"获得", "点力量。每回合开始，获得", "点 胜利誓约 。"};

    private static final String IMG = "powers/w5.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    //计算该能力从使用到移除期间，由于该能力所增加的层数，目的是移除能力的时候，顺便移除由于该能力获取到的胜利契约点数.
    private static int startEnd = 0;

    public VictorySwordPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms(){
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] +  this.amount + DESCRIPTIONS[2]);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    //触发时机：当玩家回合开始时触发。
    public void atStartOfTurn() {
        if (this.amount <= 0) {
            return;
        }
        startEnd += this.amount;
        ActionUtil.victoryPower(owner, this.amount);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new VictoryPower(AbstractDungeon.player, this.amount), this.amount, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
            if (AbstractDungeon.player.hasPower(VictoryPower.POWER_ID)) {
                int num = AbstractDungeon.player.getPower(VictoryPower.POWER_ID).amount;
                if (num - startEnd <= 0) {
                    //如果层数不够减，直接移除能力
                    ActionUtil.removePower(owner,VictoryPower.POWER_ID);
                } else {
                    ActionUtil.victoryPower(owner, -startEnd);
                }
                startEnd = 0;
            }
        }
    }
}
