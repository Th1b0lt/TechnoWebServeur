package com.uca.gui;

import com.uca.core.PersonneCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PersonneGUI {

    public static String getAllPersonnes() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("personnes", PersonneCore.getAllPersonnes());
        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/personne.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }


    public static String modifPersonne() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/modifpersonne.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }


}
