package com.daryazalevskaya.CurrencyConverter.service;

import com.daryazalevskaya.CurrencyConverter.model.entity.Currency;
import lombok.Getter;
import lombok.Setter;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyService {

    final private String url = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Getter
    @Setter
    private List<Currency> currencyList = new ArrayList<>();

    private Document getDocument() throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection urlConnection = new URL(url).openConnection();
        return builder.parse(urlConnection.getInputStream());
    }


    public void setCurrencyList(String parentTagName) throws IOException, SAXException, ParserConfigurationException, ParseException {
        Document document = getDocument();
        NodeList nodeCurrencyList = document.getDocumentElement().getElementsByTagName(parentTagName);

        for (int i = 0; i < nodeCurrencyList.getLength(); ++i) {

            Node currencyNode = nodeCurrencyList.item(i);
            Element currencyElement = (Element) currencyNode;

            this.currencyList.add(Currency.builder()
                    .numCode(Integer.parseInt(currencyElement.getElementsByTagName("NumCode").item(0).getTextContent()))
                    .charCode(currencyElement.getElementsByTagName("CharCode").item(0).getTextContent())
                    .nominal(Integer.parseInt(currencyElement.getElementsByTagName("Nominal").item(0).getTextContent()))
                    .name(currencyElement.getElementsByTagName("Name").item(0).getTextContent())
                    .value(Double.parseDouble(currencyElement.getElementsByTagName("Value").item(0).getTextContent().replaceAll(",", ".")))
                    .currencyId(currencyNode.getAttributes().getNamedItem("ID").getNodeValue())
                    .courseDate(getDate())
                    .build());

        }
    }

    public Date getDate() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document document = getDocument();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.parse(document.getDocumentElement().getAttribute("Date"));
    }


}
