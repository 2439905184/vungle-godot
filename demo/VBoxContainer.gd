extends VBoxContainer
#广告类型 视频 横幅 插屏
export (String,"video","banner","static","mrec")var ad_type
var plugin
func _on_getPlugin_pressed():
	plugin = Engine.get_singleton("VunglePlugin")
	
func _on_init_pressed():
	plugin.init("61494446ae943b230a3ac051")
	
func _on_playad_pressed():
	print_debug(ad_type)
	match ad_type:
		"video":
			plugin.show_ad("VIDEO-2471727")
		"banner":
			plugin.show_ad("BANNER-6138927")
		"static":
			plugin.show_ad("DEFAULT-8249918")
