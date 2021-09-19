package arhh.wordsearchsolver;

import java.io.*;
import java.util.List;
import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

public class Packager {
    public static void main(String[] args) throws CompressorException, IOException, ArchiveException {
        PackrConfig[] packrConfigs = {generatePackrConfig(PackrConfig.Platform.Linux64), generatePackrConfig(PackrConfig.Platform.MacOS), generatePackrConfig(PackrConfig.Platform.Windows64)};

        for (PackrConfig packrConfig : packrConfigs) {
            new Packr().pack(packrConfig);
        }
    }

    private static PackrConfig generatePackrConfig(PackrConfig.Platform platform) {
        PackrConfig config = new PackrConfig();
        config.platform = platform;
        config.executable = "word-search-solver-java";
        config.classpath = List.of("./target/word-search-solver-java-1.0-SNAPSHOT.jar");
        config.mainClass = "arhh.wordsearchsolver.Main";
        config.outDir = new File(String.format("./dist/%s", platform));

        switch (platform) {
            case Linux64:
                config.jdk = "https://corretto.aws/downloads/latest/amazon-corretto-11-x64-linux-jdk.tar.gz";
                break;
            case MacOS:
                config.jdk = "https://corretto.aws/downloads/latest/amazon-corretto-11-x64-macos-jdk.tar.gz";
                break;
            case Windows64:
                config.jdk = "https://corretto.aws/downloads/latest/amazon-corretto-11-x64-windows-jdk.zip";
                break;
        }

        // NullPointerException if these not set (as of Packr v4.0.0)
        config.vmArgs = List.of("");
        config.jrePath = "jre";
        config.verbose = true;
        config.cacheJre = new File("./cache");

        return config;
    }
}
