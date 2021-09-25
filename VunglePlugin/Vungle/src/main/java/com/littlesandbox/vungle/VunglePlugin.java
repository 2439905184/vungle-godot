package com.littlesandbox.vungle;

import android.util.Log;

import com.vungle.warren.AdConfig;
import com.vungle.warren.Banners;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleBanner;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.network.VungleApi;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

public class VunglePlugin extends GodotPlugin
{
	private String tag = this.getClass().toString();
    public VunglePlugin(Godot godot)
    {
        super(godot);
    }
    public SignalInfo vungle_init_ok = new SignalInfo("VungleOK");
    public SignalInfo vungle_init_failed = new SignalInfo("VungleFailed");
    public SignalInfo vungle_ad_ok = new SignalInfo("VungleAdOk");
    public SignalInfo vungle_ad_error = new SignalInfo("VungleAdError");
    @Override
    public String getPluginName() {
        return "VunglePlugin";
    }
    class VungleInitCallback implements InitCallback {
		@Override
		public void onSuccess() {
			Log.i(tag, "初始化成功");
			emitSignal(vungle_init_ok.getName());
		}

		@Override
		public void onError(VungleException exception) {
			Log.e(tag, "初始化失败" + exception.toString());
			emitSignal(vungle_init_failed.getName());
		}

		//当广告缓存好时触发。
		@Override
		public void onAutoCacheAdAvailable(String placementId) {
			Log.i(tag, "广告缓存完毕" + placementId);
		}
	}
	class VungleLoadAdCallback implements  LoadAdCallback
	{
		@Override
		public void onAdLoad(String s)
		{
			Log.i(tag,"广告加载成功"+s);
			emitSignal(vungle_ad_ok.getName());
		}
		@Override
		public void onError(String s, VungleException e) {
			Log.e(tag,"广告加载失败");
			Log.e(s,e.toString());
			emitSignal(vungle_ad_error.getName());
		}
	}
	//初始化sdk appid由vungle平台提供
    @UsedByGodot
    public void init(String appId)
    {
		Vungle.init(appId, getActivity().getApplicationContext(),new VungleInitCallback());
    }
	//播放奖励广告
	@UsedByGodot
	public void show_ad(String placementId)
	{
		if(Vungle.isInitialized())
		{
			Vungle.loadAd("placementId", new VungleLoadAdCallback());
		}
	}
	//播放bannder广告
	@UsedByGodot
	public void show_banner()
	{
		if(Vungle.isInitialized())
		{

		}
	}
	}
