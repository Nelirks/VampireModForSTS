package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BasicBlockAction extends AbstractGameAction {

    public BasicBlockAction(AbstractCreature target, int amount) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void update() {
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                addToBot(new GainBlockAction(target, amount));
                break;
            }
        }
        isDone = true;
    }
}
