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
	
	//播放bannder广告
	@UsedByGodot
	public void show_banner_ad()
	{
		 // Load Ad Implementation
  if (Vungle.isInitialized()) {
      Banners.loadBanner("YOUR_MREC_PLACEMENT_REFERENCE_ID", AdConfig.AdSize.BANNER, new LoadAdCallback() {
        @Override
        public void onAdLoad(String placementReferenceId) {
            // id is placementReferenceId
        }
		 if (Banners.canPlayAd("YOUR_MREC_PLACEMENT_REFERENCE_ID", AdConfig.AdSize.BANNER)) {
        VungleBanner vungleBanner = Banners.getBanner("YOUR_MREC_PLACEMENT_REFERENCE_ID", AdConfig.AdSize.BANNER, new PlayAdCallback() {
        @Override
        public void onAdStart(String id) { 
            // Ad experience started
        }
        
        @Override
  			public void onAdViewed(String id) { 
    			// Ad has rendered
        }

        @Override
        public void onAdEnd(String id) {
            // Ad experience ended
        }

        @Override
        public void onAdClick(String id) {
            // User clicked on ad
        }

        @Override
        public void onAdRewarded(String id) {
            // User earned reward for watching an ad
        }

        @Override
        public void onAdLeftApplication(String id) {
            // User has left app during an ad experience
        }

        @Override
        public void onError(String id, VungleException exception) { 
            // Ad failed to play
        }
        });
        container.addView(vungleBanner);
    }
        @Override
        public void onError(String placementReferenceId, VungleException e) {
            // Load ad error occurred - e.getLocalizedMessage() contains error message
        }
    });
  }
	}
	}
