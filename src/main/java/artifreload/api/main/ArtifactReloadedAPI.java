package artifreload.api.main;


import java.nio.file.Path;

import artifreload.api.Config.ArtifactsReloadedConfig;


public class ArtifactReloadedAPI {

    public static final String Artifact_ID = "artifactsreloaded";

    private static Path configPath;
    private static ArtifactsReloadedConfig artifactConfig;

    private ArtifactReloadedAPI() {

    }

    public static void setConfig(ArtifactsReloadedConfig config) {

        if (artifactConfig == null) {
            artifactConfig = config;
        }
    }

    public static Path getConfigPath() {

        return configPath;
    }

    public static void setConfigPath(Path path) {

        if (configPath == null) {
            configPath = path;
        }
    }

    public static ArtifactsReloadedConfig config() {
        return artifactConfig;
    }
}
