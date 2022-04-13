package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BasicBlockAction extends AbstractGameAction {

    public BasicBlockAction(AbstractCreature source, int amount) {
        this.source = source;
        this.target = source;
        this.amount = amount;
        this.actionType = ActionType.BLOCK;
    }

    @Override
    public void update() {
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
        if (consumed) {
            addToBot(new GainBlockAction(target, amount));
        }
        isDone = true;
    }
}
