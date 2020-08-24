package com.daryazalevskaya.Currency.service;

import com.daryazalevskaya.Currency.model.entity.Currency;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    final private String url = "http://www.cbr.ru/scripts/XML_daily.asp";

    private NodeList getCurrencyNodeList(String parentTagName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection urlConnection = new URL(url).openConnection();
        Document document = builder.parse(urlConnection.getInputStream());
        NodeList currencyList = document.getDocumentElement().getElementsByTagName(parentTagName);
        return currencyList;
    }


    public List<Currency> getCurrencyList(String parentTagName) throws IOException, SAXException, ParserConfigurationException {
        List<Currency> currencyList = new ArrayList<>();
        NodeList nodeCurrencyList = this.getCurrencyNodeList(parentTagName);

        for (int i = 0; i < nodeCurrencyList.getLength(); ++i) {

            Node currencyNode = nodeCurrencyList.item(i);
            Element currencyElement = (Element) currencyNode;

            currencyList.add(Currency.builder()
                    .numCode(Integer.parseInt(currencyElement.getElementsByTagName("NumCode").item(0).getTextContent()))
                    .charCode(currencyElement.getElementsByTagName("CharCode").item(0).getTextContent())
                    .nominal(Integer.parseInt(currencyElement.getElementsByTagName("Nominal").item(0).getTextContent()))
                    .name(currencyElement.getElementsByTagName("Name").item(0).getTextContent())
                    .value(Double.parseDouble(currencyElement.getElementsByTagName("Value").item(0).getTextContent()))
                    .currencyId(currencyNode.getAttributes().getNamedItem("ID").getNodeValue())
                    .build());

        }

        return currencyList;

    }

}
