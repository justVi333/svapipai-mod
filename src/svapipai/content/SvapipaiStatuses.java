package svapipai.content;

import arc.graphics.Color;
import mindustry.type.StatusEffect;

import static mindustry.content.StatusEffects.*;

public class SvapipaiStatuses
{
    public static StatusEffect
    cloggedMechanism;

    public static void load()
    {
        cloggedMechanism = new StatusEffect("clogged-mechanism")
        {{
            //localizedName = "Clogged Mechanism";
            reloadMultiplier = 0.8f;
            speedMultiplier = 0.65f;
            color = Color.valueOf("c8a58f");

            init(() ->
            {
                opposite(wet);
            });
        }};
    }
}
