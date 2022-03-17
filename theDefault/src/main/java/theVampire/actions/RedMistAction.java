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
    public RedMistAction(AbstractPlayer owner, int amount) {
        this.target = owner;
        this.amount = amount;
    }

    @Override
    public void update() {
        int finalAmount = 0;
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
                finalAmount += 1;
                if (finalAmount >= amount) break;
            }
        }
        addToBot(new ApplyPowerAction(target, target, new IntangiblePlayerPower(target, finalAmount)));
        isDone = true;
    }
}
