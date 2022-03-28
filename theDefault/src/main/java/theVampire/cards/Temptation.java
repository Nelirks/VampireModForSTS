package theVampire.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;
import theVampire.powers.ThirstPower;

import static theVampire.DefaultMod.makeCardPath;

public class Temptation extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Temptation.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/


    public Temptation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = 3;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int addedStrength = 0;
        if (p.hasPower("theVampire:Thirst") && p.getPower("theVampire:Thirst").amount >= magicNumber) {
            addToBot(new ApplyPowerAction(p, p, new ThirstPower(p, -magicNumber)));
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
            addToBot(new MakeTempCardInDrawPileAction(new Blood(), magicNumber, true, true));
            addedStrength = magicNumber;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage + addedStrength)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage + addedStrength)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}