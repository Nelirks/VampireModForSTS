package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theVampire.cards.Blood;

public class ExsanguinateAction extends AbstractGameAction {
    int magicNumber;
    public ExsanguinateAction(AbstractCreature target, AbstractCreature source, int baseDamage, int magicNumber) {
        setValues(target, source, baseDamage);
        this.magicNumber = magicNumber;
        this.actionType = ActionType.DAMAGE;
    }
    @Override
    public void update() {
        int x = 0;
        for (int n = 0; n < magicNumber; ++n) {
            target.damage(new DamageInfo(target, amount));
            if (this.target.lastDamageTaken > 0) {
                ++x;
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                this.addToTop(new WaitAction(0.1F));
            }
            tickDuration();
        }
        addToBot(new MakeTempCardInDrawPileAction(new Blood(), x, true, true));
        this.isDone = true;
    }
}