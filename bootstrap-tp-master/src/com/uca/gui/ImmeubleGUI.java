package com.uca.gui;

import com.uca.core.AppartementCore;
import com.uca.core.ImmeubleCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ImmeubleGUI {
    public static String getAllImmeuble() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("immeubles", ImmeubleCore.getAllImmeuble());
        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/immeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
    
    public static String modifImmeuble() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/modifimmeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getImmeubleById(int id) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();
        input.put("immeuble", ImmeubleCore.getImmeubleById(id));
        input.put("appartements",AppartementCore.getAppartementByImmeuble(id));
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/immeuble_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
    
}

