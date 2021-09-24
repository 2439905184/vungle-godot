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

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.UsedByGodot;

import androidx.annotation.NonNull;

public class VunglePlugin extends GodotPlugin
{
	private String tag = this.getClass().toString();
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
    public void init(String appId)
    {
		Vungle.init(appId, getActivity().getApplicationContext(),new InitCallback() {
		@Override
		public void onSuccess() {
			Log.i(tag,"初始化成功");
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
		});
    }
	//播放奖励广告
	@UsedByGodot
	public void show_ad(String placementId)
	{
		if(Vungle.isInitialized())
		{

			Vungle.loadAd("placementId",new  LoadAdCallback() {
			@Override
			public void onAdLoad(String placementReferenceId)
			{ 
				if(Vungle.canPlayAd(placementId))
				{
					Vungle.playAd("PLACEMENT_ID", null, new PlayAdCallback() {
						@Override
						public void creativeId(String s) {

						}

						@Override
					public void onAdStart(String placementReferenceId) { } 
					@Override
					public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) { }

						@Override
						public void onAdEnd(String s) {

						}

						@Override
						public void onAdClick(String s) {

						}

						@Override
						public void onAdRewarded(String s) {

						}

						@Override
						public void onAdLeftApplication(String s) {

						}

						@Override
					public void onError(String placementReferenceId, VungleException exception) { }

						@Override
						public void onAdViewed(String s) {

						}
					});
				}
			}

			@Override
			public void onError(String placementReferenceId, VungleException exception)
			{
				Log.e(placementReferenceId,exception.toString());
			}
		  });
		
		}
	}
	
	//播放bannder广告
	@UsedByGodot
	public void show_banner_ad()
	{
		 // Load Ad Implementation
  if (Vungle.isInitialized())
  {
      Banners.loadBanner("YOUR_MREC_PLACEMENT_REFERENCE_ID", AdConfig.AdSize.BANNER, new LoadAdCallback() {
        @Override
        public void onAdLoad(String placementReferenceId)
		{
            // id is placementReferenceId
        }
        @Override
        public void onError(String placementReferenceId, VungleException e)
		{
            // Load ad error occurred - e.getLocalizedMessage() contains error message
        }
    });
  }
	}
	}
