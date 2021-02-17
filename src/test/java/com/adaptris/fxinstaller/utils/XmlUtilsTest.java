package com.adaptris.fxinstaller.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.net.MalformedURLException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

public class XmlUtilsTest {

  @Test
  public void testGetDocument() throws Exception {
    Document document = XmlUtils.getDocument(getClass().getResource("/interlok-json.xml").toString());

    assertNotNull(document);
    assertNotNull("project", document.getNodeName());
  }

  @Test
  public void testGetDocumentInvalidXml() throws Exception {
    assertThrows(SAXParseException.class, () -> {
      XmlUtils.getDocument(getClass().getResource("/invalid-xml.xml").toString());
    });
  }

  @Test
  public void testGetDocumentInvalidLocation() throws Exception {
    assertThrows(MalformedURLException.class, () -> {
      XmlUtils.getDocument("/invalid-location.xml");
    });
  }

}
