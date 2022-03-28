package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVampire.cards.Blood;

public class BloodTaxAction extends AbstractGameAction {
    public BloodTaxAction(AbstractCreature source, int damageAmount) {
        this.source = source;
        amount = damageAmount;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        for (AbstractCreature m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            m.damage(new DamageInfo(m, amount));
            if (!m.isDead && m.lastDamageTaken > 0) addToBot(new MakeTempCardInDrawPileAction(new Blood(), 1, true, true));
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        isDone = true;
    }
}
