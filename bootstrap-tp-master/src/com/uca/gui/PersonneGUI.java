package com.uca.gui;

import com.uca.core.PersonneCore;
import com.uca.core.AppartementCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.uca.core.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PersonneGUI {

    public static String getAllPersonnes(int role) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("personnes", PersonneCore.getAllPersonnes());
        input.put("role",role);
        

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
    public static String getPersonneById(int id,int role) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();
        input.put("personne", PersonneCore.getOnePersonne(id));
        input.put("appartements",AppartementCore.getAppartementsByPersonne(id));
        input.put("locataires",PersonneCore.getLocatairesByProprietaire(id));
        input.put("statglobal",ImmeubleCore.pourcentageAppartementsLouesEtNonLouesPourPersonne(id));
        input.put("statLocal",ImmeubleCore.pourcentageAppartementsLouesEtNonLouesPourPersonneEtImmeuble(id));
        input.put("role",role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/personne_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

}
