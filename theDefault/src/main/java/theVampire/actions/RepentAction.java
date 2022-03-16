package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVampire.cards.Blood;

public class RepentAction extends AbstractGameAction {
    public RepentAction(AbstractPlayer owner, int amount) {
        this.target = owner;
        this.amount = amount;
    }

    @Override
    public void update() {
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            System.out.println(card.cardID);
            if (card.cardID.equals("theVampire:Blood")) {
                addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                addToTop(new HealAction(target, target, amount));
            }
        }
        isDone = true;
    }
}
