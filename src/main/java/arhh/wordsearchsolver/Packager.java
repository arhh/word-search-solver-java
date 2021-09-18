package arhh.wordsearchsolver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

public class Packager {
    public static void main(String[] args) throws CompressorException, IOException, ArchiveException {
        PackrConfig config = new PackrConfig();
        config.platform = PackrConfig.Platform.Linux64;
        config.executable = "word-search-solver-java";
        config.classpath = List.of("target/word-search-solver-java-1.0-SNAPSHOT.jar");
        config.mainClass = "arhh.wordsearchsolver.Main";
        config.outDir = new File("./dist");
        config.jdk = "https://corretto.aws/downloads/latest/amazon-corretto-11-x64-macos-jdk.tar.gz";
        config.vmArgs = List.of("");
        config.jrePath = "jre";
        new Packr().pack(config);
    }
}
