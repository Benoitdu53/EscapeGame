package com.ocr.escapegame;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {

        public static final int NOMBRE_CHIFFRES;
        public static final int NOMBRE_ESSAIE;
        public static final boolean MODE_DEV;

        static {

            final Properties prop = new Properties();
            InputStream input = null;
            int nombreEssaie =4;
            int nombreChiffres =4;
            boolean modeDev=false;

            try {

                input = GameProperties.class.getClassLoader().getResourceAsStream("config.properties");

                // Load input
                prop.load(input);

                nombreChiffres = Integer.parseInt(prop.getProperty("nombre.chiffre.combinaison"));
                nombreEssaie = Integer.parseInt(prop.getProperty("nombre.essaie"));
                modeDev = Boolean.parseBoolean(prop.getProperty("mode.dev"));


            } catch (final IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            NOMBRE_ESSAIE=nombreEssaie;
            NOMBRE_CHIFFRES=nombreChiffres;
            MODE_DEV=modeDev;
        }
}
