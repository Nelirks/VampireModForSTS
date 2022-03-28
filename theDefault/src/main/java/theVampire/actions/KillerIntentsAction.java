package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class KillerIntentsAction extends AbstractGameAction{
    public KillerIntentsAction(AbstractCreature target, AbstractPlayer source, int amount) {
        setValues(target, source, amount);
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        addToBot(new DamageAction(target, new DamageInfo(source, amount)));
        if (target.hasPower("Vulnerable"))
            addToBot(new DamageAction(target, new DamageInfo(source, amount)));
        isDone = true;
    }
}
