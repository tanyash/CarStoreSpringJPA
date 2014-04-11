package com.javacources.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
import javax.faces.bean.ReferencedBean;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by tanya on 3/9/14.
 */
public class XMLBinder {
    private static String dirPath;
    private final static String XML_NAME = "/store.xml";
    public Unmarshaller unmarshaller;
    public Marshaller marshaller;

    public XMLBinder() {
        XMLBinder.setDirPath(System.getProperty("user.home"));
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public static void setDirPath(String dirPath) {
        XMLBinder.dirPath = dirPath;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Object getDataFromXML(Class type) throws IOException {
        Object o = null;
        File xmlFile = new File(dirPath + XML_NAME);
        if (!(xmlFile.exists())){
            return o;
        }
        InputStream stream = null;

        try {
//            JAXBContext jc = JAXBContext.newInstance(type);
//            Unmarshaller unmarshaller = jc.createUnmarshaller();

            stream = new FileInputStream(xmlFile);
            o = unmarshaller.unmarshal(new StreamSource(stream));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if (stream != null) {
                stream.close();
            }
        }

        return o;
    }

    public void setDataToXML(Object obj) throws IOException {
        File xmlFile = new File(dirPath + XML_NAME);

        OutputStream os = null;
        try {
//            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
//            marshaller = jc.createMarshaller();

            //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            os = new FileOutputStream(xmlFile, false);
            getMarshaller().marshal("1111", new StreamResult(os));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if (os != null) {
                os.close();
            }
        }
    }
}
