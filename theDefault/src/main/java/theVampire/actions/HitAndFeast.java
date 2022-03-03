package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theVampire.cards.Blood;
import theVampire.powers.ThirstPower;

import java.util.List;

public class HitAndFeast extends AbstractGameAction{
    private int feastAmount;
    private DamageInfo info;
    private int multiAttack;
    private List<AbstractGameAction> actions;
    private boolean applyIfEnd;

    public HitAndFeast(AbstractCreature target, DamageInfo info, int multiAttack, int feastAmount, List<AbstractGameAction> actions, boolean applyIfEnd) {
        this.info = info;
        this.setValues(target, info);
        this.multiAttack = multiAttack;
        this.feastAmount = feastAmount;
        this.actions = actions;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
        this.applyIfEnd = applyIfEnd;
    }

    public HitAndFeast(AbstractCreature target, DamageInfo info, int feastAmount, List<AbstractGameAction> actions) {
        this(target, info, 1, feastAmount, actions, false);
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractCreature player = AbstractDungeon.player;
            for(int attack = 0; attack < multiAttack; ++attack)
                this.target.damage(this.info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            if (player.hasPower("theVampire:Thirst") && player.getPower("theVampire:Thirst").amount >= feastAmount) {
                if (player.getPower("theVampire:Thirst").amount == feastAmount){
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, "theVampire:Thirst"));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new ThirstPower(player, 0), -feastAmount));
                }
                if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() || applyIfEnd)
                    for (AbstractGameAction action : actions)
                        AbstractDungeon.actionManager.addToBottom(action);
            }
        }

        this.tickDuration();
    }
}
