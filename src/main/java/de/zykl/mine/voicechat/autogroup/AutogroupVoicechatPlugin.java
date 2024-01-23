package de.zykl.mine.voicechat.autogroup;

import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.PlayerConnectedEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import de.maxhenkel.voicechat.api.*;

public final class AutogroupVoicechatPlugin implements VoicechatPlugin {

    public static Group AUTO_GROUP;
    public static VoicechatServerApi SERVER_API;

    @Override
    public String getPluginId() {
        return "voicechat-autogroup";
    }

    @Override
    public void initialize(VoicechatApi api) {

    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
        registration.registerEvent(PlayerConnectedEvent.class, this::onPlayerConnected);

    }

    private void onServerStarted(VoicechatServerStartedEvent event) {
        SERVER_API = event.getVoicechat();
        AUTO_GROUP = SERVER_API.createGroup("All", null);
    }

    public void onPlayerConnected(PlayerConnectedEvent event) {
        VoicechatConnection connection = event.getConnection();
        connection.setGroup(AUTO_GROUP);
    }

}

