package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.reward.GainRareCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 魔眼
 */
public class MagicEyePower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:MagicEyePower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/5.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;


    public MagicEyePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = -99;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]);
    }

    @Override
    public void onVictory() {
//        CombatRewardScreenPatch.magicEyePower = amount;
//        RewardItem item = new RewardItem(AbstractCard.CardColor.COLORLESS);
//        ArrayList<AbstractCard> cards = item.cards;
//        cards.clear();
//        cards.add(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.RARE));
//        AbstractDungeon.getCurrRoom().addCardReward(item);
        GainRareCard.receiveRewards(AbstractDungeon.getCurrRoom().rewards);
    }

}