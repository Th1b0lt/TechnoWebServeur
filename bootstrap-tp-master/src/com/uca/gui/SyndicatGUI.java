package com.uca.gui;


import com.uca.core.SyndicatCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
public class SyndicatGUI {
  /*   public static String getAllSyndicat() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("syndicats", SyndicatCore.getAllSyndicat());
        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/syndicat.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
    */
}
