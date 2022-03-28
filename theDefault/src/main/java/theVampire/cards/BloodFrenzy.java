package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;

import static theVampire.DefaultMod.makeCardPath;

public class BloodFrenzy extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BloodFrenzy.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 6;

    private static final int MAGIC_NUMBER = 4;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 1;

    // /STAT DECLARATION/


    public BloodFrenzy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int costForTurn = this.cost;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("theVampire:Blood")) --costForTurn;
            if (costForTurn == 0) break;
        }
        setCostForTurn(costForTurn);
    }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}