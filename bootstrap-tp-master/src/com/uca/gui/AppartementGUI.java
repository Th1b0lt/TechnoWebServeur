package com.uca.gui;

import com.uca.core.*;

import com.uca.core.AppartementCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
public class AppartementGUI {
    public static String getAllAppartement() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("appartements", AppartementCore.getAllAppartement());
        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/appartement.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
    public static String modifappart() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();
    
        Map<String, Object> input = new HashMap<>();
    
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/modifappart.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }
    public static String getAppartementById(int id) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();
        input.put("appartement", AppartementCore.getOneAppartement(id));
        input.put("locataires",PersonneCore.getLocatairesByAppartement(id));
        input.put("propriaitaires",PersonneCore.getProprietairesByAppartement(id));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/appartement_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
    
}
