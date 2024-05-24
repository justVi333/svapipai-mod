package svapipai;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import svapipai.content.*;

public class Svapipai extends Mod{

    public Svapipai()
    {
    }

    @Override
    public void loadContent()
    {
        SvapipaiStatuses.load();
        SvapipaiItems.load();
        SvapipaiUnits.load();
        SvapipaiBlocks.load();
    }

}
