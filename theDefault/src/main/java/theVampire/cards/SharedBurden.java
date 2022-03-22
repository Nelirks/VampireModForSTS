package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;
import theVampire.powers.ThirstPower;

import static theVampire.DefaultMod.makeCardPath;

public class SharedBurden extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SharedBurden.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;

    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 2;

    // /STAT DECLARATION/


    public SharedBurden() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC_NUMBER;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.baseMagicNumber, false), this.baseMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThirstPower(p, this.baseMagicNumber)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}