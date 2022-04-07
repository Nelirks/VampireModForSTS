package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class RedMistAction extends AbstractGameAction {
    public RedMistAction(AbstractPlayer source, int amount) {
        this.source = source;
        this.target = source;
        this.amount = amount;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        int finalAmount = 0;
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (finalAmount >= amount) break;
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                finalAmount += 1;
            }
        }
        for(AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (finalAmount >= amount) break;
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
                finalAmount += 1;
            }
        }
        for(AbstractCard card : AbstractDungeon.player.hand.group) {
            if (finalAmount >= amount) break;
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                finalAmount += 1;
            }
        }
        addToBot(new ApplyPowerAction(target, target, new IntangiblePlayerPower(target, finalAmount)));
        isDone = true;
    }
}
