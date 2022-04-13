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
    public RepentAction(AbstractPlayer source, int amount) {
        this.source = source;
        this.target = source;
        this.amount = amount;
        this.actionType = ActionType.HEAL;
    }

    @Override
    public void update() {
        int healAmount = 0;
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                healAmount += amount;
            }
        }
        for(AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
                healAmount += amount;
            }
        }
        for(AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                healAmount += amount;
            }
        }
        addToBot(new HealAction(target, target, healAmount));
        isDone = true;
    }
}
