package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;
import theVampire.powers.OpenWoundPower;

import static theVampire.DefaultMod.makeCardPath;

public class Retreat extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Retreat.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 1;

    private static final int BLOCK = 7;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;



    // /STAT DECLARATION/


    public Retreat() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p,  AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        for (AbstractCreature ms : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (ms.hasPower("Vulnerable")) {
                this.addToBot(new ApplyPowerAction(ms, p, new WeakPower(ms, this.magicNumber, false)));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}