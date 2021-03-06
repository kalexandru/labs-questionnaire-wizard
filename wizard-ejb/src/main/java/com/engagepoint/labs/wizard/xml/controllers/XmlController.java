/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engagepoint.labs.wizard.xml.controllers;

import com.engagepoint.labs.wizard.bean.WizardDataModelGenerator;
import com.engagepoint.labs.wizard.bean.WizardDocument;
import com.engagepoint.labs.wizard.bean.WizardForm;
import com.engagepoint.labs.wizard.xml.parser.XmlCustomParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import super_binding.QuestionnaireForms;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 */
@Named
@SessionScoped
public class XmlController implements Serializable {

    private final XmlCustomParser parser;
    private List<String> xmlPathList;
    private static final Logger LOGGER = Logger.getLogger(XmlController.class.getName());

    public XmlController() {
        xmlPathList = new ArrayList<String>();
        xmlPathList.add("/DependentPages.xml");
        xmlPathList.add("/DependentTopics.xml");
        xmlPathList.add("/DependentQuestions.xml");
        xmlPathList.add("/TestAllComponents.xml");
        xmlPathList.add("/XMLforDemo.xml");
        this.parser = new XmlCustomParser();
    }

    public List<String> getXmlPathList() {
        return xmlPathList;
    }

    public WizardDocument readAllDeafultXmlFiles() {
        WizardDataModelGenerator generator = new WizardDataModelGenerator();
        List<QuestionnaireForms> formsList = new ArrayList<>();
        for (String xmlPath : xmlPathList) {
            try {
                formsList.add(parser.parseXML(xmlPath));
            } catch (SAXException e) {
                LOGGER.warn("SAXException", e);
            } catch (Exception e) {
                LOGGER.warn("Exception", e);
            }
        }
        return generator.getWizardDocument(formsList);
    }

    public File getExportFileFromWizardForm(WizardForm wizardForm) {
        return parser.parseWizardFormToXml(wizardForm);
    }
}
