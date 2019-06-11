package com.wibowo.intellij.mdd;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "MDDConfig", storages = {
        @Storage("$APP_CONFIG$/mario_driven_development_config.xml")
})
public class Settings implements PersistentStateComponent<Settings> {
    public Boolean pluginEnabled;
    public boolean actionSoundsEnabled = true;
    public boolean backgroundMusicEnabled = true;

    public static Settings getInstance() {
        return ServiceManager.getService(Settings.class);
    }

    public void setActionSoundsEnabled(boolean actionSoundsEnabled) {
        this.actionSoundsEnabled = actionSoundsEnabled;
    }

    public void setBackgroundMusicEnabled(boolean backgroundMusicEnabled) {
        this.backgroundMusicEnabled = backgroundMusicEnabled;
    }

    @Nullable
    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Settings settings) {
        XmlSerializerUtil.copyBean(settings, this);
        if (pluginEnabled != null) {
            actionSoundsEnabled = pluginEnabled;
            backgroundMusicEnabled = pluginEnabled;
            pluginEnabled = null;
        }

    }

    public boolean isPluginEnabled() {
        return actionSoundsEnabled || backgroundMusicEnabled;
    }
}
