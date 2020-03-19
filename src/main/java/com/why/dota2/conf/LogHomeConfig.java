package com.why.dota2.conf;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class LogHomeConfig extends PropertyDefinerBase {
    public static final String LOG_DIR = File.separator +  "logs";

    @Override
    public String getPropertyValue() {
        String path = System.getProperty("user.dir");
        log.info("====={}=====", path);
        File logPath = new File(path);
        return logPath.getParent() + LOG_DIR;
    }
}
