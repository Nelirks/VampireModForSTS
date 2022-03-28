package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVampire.powers.ThirstPower;

import java.util.ArrayList;
import java.util.List;

public class RejuvenatingDrinkAction extends AbstractGameAction {
    private int thirstAmount;
    private boolean isUpgraded;
    public RejuvenatingDrinkAction(AbstractPlayer source, int amount1, int amount2, boolean isUpgraded) {
        this.source = source;
        this.target = source;
        this.amount = amount1;
        this.actionType = AbstractGameAction.ActionType.HEAL;
        this.thirstAmount = amount2;
        this.isUpgraded = isUpgraded;
    }

    @Override
    public void update() {
        List<AbstractCard> bloodToExhaust= new ArrayList<>();
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.cardID.equals("theVampire:Blood")) {
                bloodToExhaust.add(card);
                if (bloodToExhaust.size() == amount) break;
            }
        }
        if (bloodToExhaust.size() == amount) {
            for (AbstractCard card: bloodToExhaust) {
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
            }
            addToBot(new GainEnergyAction(amount));
            if (isUpgraded) addToBot(new ApplyPowerAction(target, source, new ThirstPower(target, -4)));
        }
        isDone = true;
    }
}