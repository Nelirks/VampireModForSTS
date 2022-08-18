package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVampire.cards.Blood;

public class AimForTheVeinsAction extends AbstractGameAction {

    public AimForTheVeinsAction(AbstractCreature target, AbstractCreature source, int damageAmount) {
        setValues(target, source, damageAmount);
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        if (target.hasPower("Vulnerable"))
            addToBot(new MakeTempCardInDrawPileAction(new Blood(), 1, true, true));
        addToBot(new DamageAction(target, new DamageInfo(source, amount)));
        isDone = true;
    }
}
