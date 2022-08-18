package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class MultiplyVulnerableAction extends AbstractGameAction {
    public MultiplyVulnerableAction(AbstractCreature target, AbstractCreature source, int amount) {
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
    }

    public void update() {
        if (this.target != null && this.target.hasPower("Vulnerable")) {
            this.addToTop(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, this.target.getPower("Vulnerable").amount * (amount-1), false)));
        }

        this.isDone = true;
    }
}
