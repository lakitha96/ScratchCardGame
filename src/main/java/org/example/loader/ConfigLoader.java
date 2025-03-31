package org.example.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.model.config.Config;

import java.io.File;

/**
 * @author lakithaprabudh
 */
public class ConfigLoader {
    public static Config loadConfig(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return mapper.readValue(new File(path), Config.class);
    }
}
