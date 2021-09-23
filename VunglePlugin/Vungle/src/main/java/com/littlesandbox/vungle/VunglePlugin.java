package com.littlesandbox.vungle;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.UsedByGodot;

public class VunglePlugin extends GodotPlugin
{
	private String tag = this.class.toString();
    public VunglePlugin(Godot godot)
    {
        super(godot);
    }
    @Override
    public String getPluginName() {
        return "VunglePlugin";
    }
	//初始化sdk appid由vungle平台提供
    @UsedByGodot
    public void init(appId)
    {
		Vungle.init(appId, getApplicationContext(), new InitCallback() {
		@Override
		public void onSuccess() {
			Log.i(tag,"初始化成功")
		// SDK has successfully initialized
		}

		  @Override
		  public void onError(VungleException exception) {
			// SDK has failed to initialize
			Log.e(tag,"初始化失败"+exception.toString());
		  }

		  @Override
		  public void onAutoCacheAdAvailable(String placementId) {
			// Ad has become available to play for a cache optimized placement
			//当广告缓存好时触发。
		  }
		};
    }
	//播放奖励广告
	@UsedByGodot
	public void show_ad()
	{
		if(Vungle.isInitialized())
		{

			Vungle.loadAd("placementId",new  LoadAdCallback() {
			@Override
			public void onAdLoad(String placementReferenceId)
			{ 
				if(Vungle.canPlayAd(@NonNull String id))
				{
					Vungle.playAd("PLACEMENT_ID", null, new PlayAdCallback() { 
					@Override 
					public void onAdStart(String placementReferenceId) { } 
					@Override
					public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) { } 
					@Override
					public void onError(String placementReferenceId, VungleException exception) { } 
				  });
				}
			}

			@Override
			public void onError(String placementReferenceId, VungleException exception)
			{
				Log.e(placementId,exception.toString());
			}
		  });
		
		}
	}
	
}
