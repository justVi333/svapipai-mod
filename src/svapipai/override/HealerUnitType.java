package svapipai.override;

import arc.Core;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.type.UnitType;

import static arc.math.Mathf.rand;

public class HealerUnitType extends UnitType
{
    public float healPercent, healAmount;
    public Unit targetUnit;
    public boolean healing;
    public float laserWidth;
    public HealerUnitType(String name)
    {
        super(name);
    }

    @Override
    public void update(Unit unit)
    {
        laserWidth = Mathf.lerpDelta(laserWidth, healing ? 1f : 0.1f, 0.1f * Time.delta);
    }

    @Override
    public void draw(Unit unit)
    {
        super.draw(unit);
        if(healing)
            drawHealLaser(unit);
    }

    public void drawHealLaser(Unit unit)
    {
        Drawf.laser(Core.atlas.find(unit.type.name + "-laser"),
                Core.atlas.find(unit.type.name +"-laser-end"),
                unit.x + Angles.trnsx(unit.rotation, Mathf.absin(Time.time, 1.1f, 0.5f)), unit.y + Angles.trnsy(unit.rotation, Mathf.absin(Time.time, 1.1f, 0.5f)),
                targetUnit.x + Mathf.sin(Time.time + 48, 12, 1), targetUnit.y + Mathf.sin(Time.time + 48, 12, 1),
                laserWidth);
    }
}
