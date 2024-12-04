/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.connection;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 *
 * @author qubu
 */
public class Config {

    Properties configFile;

    public Config() {

        configFile = new java.util.Properties();
        try {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            String pathArr[] = fullPath.split("/classes/");
            fullPath = pathArr[0];
            configFile.load(new FileInputStream(fullPath + "/config.properties"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }

   

}
