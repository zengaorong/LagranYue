package com.wsk.cards.power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.base.BloodyTemplePower;
import com.wsk.powers.base.BloodyTemplePowerUpgraded;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class PowerBloodyTempleCard extends CustomCard {
    public static final String ID = "LagranYue:PowerBloodyTempleCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final String UPGRADED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/PowerBloodyTempleCard.png";
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 1;

    public PowerBloodyTempleCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = false;
        this.isEthereal = true;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isInnate = false;
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new PowerBloodyTempleCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
            this.isEthereal = false;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,
//                new BloodyTemplePower(abstractPlayer, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                    new BloodyTemplePowerUpgraded(abstractPlayer, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                    new BloodyTemplePower(abstractPlayer, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}
