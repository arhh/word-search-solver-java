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

        File distFile = new File("./dist");
        deleteOutDir(distFile);
        for (PackrConfig packrConfig : packrConfigs) {
            if (new File(packrConfig.classpath.get(0)).exists()) {
                new Packr().pack(packrConfig);
            } else {
                throw new FileNotFoundException(String.format("The path specified for Packr classpath ('%s') does not exist", packrConfig.classpath.get(0)));
            }
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

        return config;
    }

    private static void deleteOutDir(final File outDir) {
        try {
            if (outDir.isFile() || outDir.isDirectory() && outDir.list().length == 0) {
                System.out.println("Deleting: " + outDir);
                outDir.delete();
            } else {
                for (File file : outDir.listFiles()) {
                    System.out.println("Stepping into directory: " + file);
                    deleteOutDir(file);
                }
                deleteOutDir(outDir);
            }
        } catch (NullPointerException e) {
            System.out.printf("Empty file at the following path: %s.\n Nothing to delete%n", outDir);
        }
    }
}
