package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;
import theVampire.powers.ThirstPower;

import static theVampire.DefaultMod.makeCardPath;

public class GiveIn extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(GiveIn.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;


    // /STAT DECLARATION/


    public GiveIn() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 0;
    }

    private void updateValues() {
        if (AbstractDungeon.player.hasPower("theVampire:Thirst"))
            baseMagicNumber = AbstractDungeon.player.getPower("theVampire:Thirst").amount*(upgraded ? 75 : 50)/100;
        else
            baseMagicNumber = 0;
        baseDamage = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        updateValues();
        this.calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThirstPower(p, 0), -baseDamage));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn)));
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void applyPowers() {
        updateValues();
        super.applyPowers();
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION);
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }

    }
}