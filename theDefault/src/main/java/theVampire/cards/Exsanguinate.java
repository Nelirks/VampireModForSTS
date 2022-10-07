package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.ExsanguinateAction;
import theVampire.characters.TheDefault;

import static theVampire.DefaultMod.makeCardPath;

public class Exsanguinate extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Exsanguinate.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 2;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    // /STAT DECLARATION/


    public Exsanguinate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { this.addToBot(new ExsanguinateAction(m, p, baseDamage, magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}