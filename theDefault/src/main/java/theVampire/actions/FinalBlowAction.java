package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FinalBlowAction extends AbstractGameAction {
    public FinalBlowAction(AbstractCreature target, AbstractCreature source, int baseDamage) {
        setValues(target, source, baseDamage);
    }

    @Override
    public void update() {
        int damage = 0;
        if (target.hasPower("Vulnerable"))
            damage = amount * target.getPower("Vulnerable").amount;
        addToBot(new DamageAction(target, new DamageInfo(source, damage)));
        addToBot(new RemoveSpecificPowerAction(target, source, "Vulnerable"));
        isDone = true;
    }
}
