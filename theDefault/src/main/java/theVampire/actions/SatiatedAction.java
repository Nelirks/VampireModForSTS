package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SatiatedAction extends AbstractGameAction {
    public SatiatedAction(AbstractPlayer source, int amount) {
        this.target = source;
        this.source = source;
        this.amount = amount;
        this.actionType = ActionType.BLOCK;
    }

    @Override
    public void update() {
        int blockAmount = 0;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                blockAmount += amount;
            }
        }
        addToBot(new GainBlockAction(source, target, blockAmount));
        isDone = true;
    }
}
