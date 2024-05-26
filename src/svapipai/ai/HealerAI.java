package svapipai.ai;

import arc.math.*;
import mindustry.entities.*;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.RepairFieldAbility;
import mindustry.entities.units.*;
import mindustry.gen.*;
import svapipai.override.HealerUnitType;

public class HealerAI extends AIController
{
    @Override
    public void updateMovement()
    {
        unloadPayloads();

        HealerUnitType healerUnitType = (HealerUnitType) unit.type;
        Unit targetUnit = null;
        boolean healing = false;

        if(target != null)
        {
            if(target.within(unit, unit.type.range))
            {
                if(target instanceof Unit)
                {
                    targetUnit = ((Unit)target);
                    if(healerUnitType.healing)
                        targetUnit.heal(healerUnitType.healPercent / 100f * targetUnit.maxHealth + healerUnitType.healAmount);
                    healing = true;
                    healerUnitType.targetUnit = targetUnit;
                }
                unit.aim(target);
            }
            moveTo(target, (target instanceof Sized s ? s.hitSize() / 2f * 1.1f : 0f) + unit.hitSize / 2f + 15f, 50f);
            unit.lookAt(target);
        }
        healerUnitType.healing = healing;
    }

    @Override
    public void updateTargeting()
    {
        if(retarget())
            target = findTarget(unit.x, unit.y, unit.range(), true, true);
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground)
    {
        var result = Units.closest(unit.team, x, y, 1000, u -> !u.dead() && u.type != unit.type && u.targetable(unit.team) && u.health < u.maxHealth,
                (u, tx, ty) -> -(u.health / u.maxHealth) + Mathf.dst2(u.x, u.y, tx, ty) / 6400f);
        if(result != null) return result;

        /*var core = unit.closestCore();
        if(core != null) return core;

        if(state.rules.waves && unit.team == state.rules.waveTeam)
        {
            return unit.closestEnemyCore();
        }*/

        return null;
    }
}
