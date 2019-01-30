package artifreload.api.Config;


import java.io.File;

import artifreload.api.Config.Property.ConfigPropertyBoolean;


@SuppressWarnings("WeakerAccess")
public class ArtifactsReloadedConfig extends Config {

    public final ConfigPropertyBoolean ENABLE_DEBUGGING;

    public artifactsReloadedConfig(File configFile) {

        super(configFile);

        ENABLE_DEBUGGING = new ConfigPropertyBoolean(
          "Enable Debugging",
          "Debug",
          "WARNING: This should only be enabled if oyu know what you're doing.",
          false
        );
        this.addProperty(ENABLE_DEBUGGING);
    }
}
