package svapipai.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class SvapipaiItems
{
    public static Item palladium;

    public static void load()
    {
        palladium = new Item("palladium", Color.valueOf("f37f6f"))
        {{
            localizedName = "Palladium";
            hardness = 4;
            cost = 1.1f;
        }};

    }
}
