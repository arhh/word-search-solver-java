package arhh.wordsearchsolver;

import java.io.*;
import java.net.URL;
import java.util.List;
import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Packager {
    public static void main(String[] args) throws CompressorException, IOException, ArchiveException, ParseException {
        JSONParser parser = new JSONParser();
        URL is = Packager.class.getClassLoader().getResource("word-search-solver-linux.json");
        JSONObject configJson = (JSONObject) parser.parse(new FileReader(is.getFile()));

        PackrConfig config = new PackrConfig();
        for (PackrConfig.Platform platform : PackrConfig.Platform.values()) {
            if (platform.toString().toLowerCase().equals((String) configJson.get("platform"))) {
                config.platform = platform;
                break;
            }
        }
        config.executable = (String) configJson.get("executable");
        config.classpath = (JSONArray) configJson.get("classpath");
        config.mainClass = (String) configJson.get("mainclass");
        config.outDir = new File((String) configJson.get("output"));
        config.jdk = (String) configJson.get("jdk");
        config.vmArgs = List.of("");
        config.jrePath = "jre";

        new Packr().pack(config);
    }
}
