package theVampire.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVampire.DefaultMod;
import theVampire.characters.TheDefault;

import static theVampire.DefaultMod.makeCardPath;

public class FoulBlood extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(FoulBlood.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 5;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/


    public FoulBlood() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        boolean consumed = false;
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                consumed = true;
                break;
            }
        }
        if (!consumed) {
            for(AbstractCard card : AbstractDungeon.player.discardPile.group) {
                if (card.cardID.equals("theVampire:Blood")) {
                    addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
                    consumed = true;
                    break;
                }
            }
        }
        if (!consumed) {
            for(AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.cardID.equals("theVampire:Blood")) {
                    addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                    consumed = true;
                    break;
                }
            }
        }
        if (consumed)  {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(p, magicNumber, false)));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}