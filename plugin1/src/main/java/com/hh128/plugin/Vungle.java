package com.hh128.plugin;

import android.app.Activity;
import android.widget.Toast;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 11111 on 2021/8/30.
 */
public class Plugin extends GodotPlugin
{
    Activity activity;
    SignalInfo i= new SignalInfo("a");
    public Plugin(Godot godot) {
        super(godot);
    }
    @Override
    public String getPluginName() {
        return "Plugin";
    }
    //注册信号
    @Override
    public Set<SignalInfo> getPluginSignals() {
        HashSet<SignalInfo> signals = new HashSet<SignalInfo>();
        signals.add(i);
        return signals;
    }
    @UsedByGodot
    public void sig()
    {
        //发送信号
        emitSignal(i.getName());
    }
    //注册方法
    @UsedByGodot
    public void test()
    {
        activity=getActivity();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getGodot().mView.getContext(), "test",Toast.LENGTH_LONG).show();
            }
        });
    }
}
