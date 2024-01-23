package de.zykl.mine.voicechat.autogroup;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;

public final class VoicechatAutogroup extends JavaPlugin {

    public static final String PLUGIN_ID = "voicechat-autogroup";
    public static final Logger LOGGER = LogManager.getLogger(PLUGIN_ID);

    @Nullable
    private AutogroupVoicechatPlugin voicechatPlugin;

    @Override
    public void onEnable() {
        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
        if (service != null) {
            voicechatPlugin = new AutogroupVoicechatPlugin();
            service.registerPlugin(voicechatPlugin);
            LOGGER.info("Successfully registered voice chat broadcast plugin");
        } else {
            LOGGER.info("Failed to register voice chat broadcast plugin");
        }
    }

    @Override
    public void onDisable() {
        if (voicechatPlugin != null) {
            getServer().getServicesManager().unregister(voicechatPlugin);
            LOGGER.info("Successfully unregistered voice chat broadcast plugin");
        }
    }
}
