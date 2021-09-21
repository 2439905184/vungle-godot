package com.littlesandbox.vungle;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.UsedByGodot;

public class VunglePlugin extends GodotPlugin
{
    public VunglePlugin(Godot godot)
    {
        super(godot);
    }
    @Override
    public String getPluginName() {
        return "VunglePlugin";
    }
    @UsedByGodot
    public void init()
    {

    }
}
