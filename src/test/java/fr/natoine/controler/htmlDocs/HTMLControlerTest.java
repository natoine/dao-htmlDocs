package fr.natoine.controler.htmlDocs;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.natoine.dao.htmlDocs.DAOHtmlDocs;
import fr.natoine.model_htmlDocs.DocumentHTML;
import fr.natoine.model_htmlDocs.SelectionHTML;
import fr.natoine.model_htmlDocs.WebPage;
import fr.natoine.model_resource.Resource;
import fr.natoine.model_resource.URI;
import junit.framework.TestCase;

public class HTMLControlerTest extends TestCase 
{
	private EntityManagerFactory emf_html = Persistence.createEntityManagerFactory("htmlDocs");
	
	public HTMLControlerTest(String name) 
	{		    
		super(name);
	}

	public void testCreateDocumentHTML()
	{
		//CreateDocumentHTML _htmldoccontroler = new CreateDocumentHTML();
		DAOHtmlDocs _htmldoccontroler = new DAOHtmlDocs(emf_html);
		URI access = new URI();
		access.setEffectiveURI("http://efeectiveURI.access.dochtml.test");
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://efeectiveURI.representsResource.dochtml.test");
		_htmldoccontroler.createDocumentHTML("document HTML test", "HTMLControlerTest.testCreateDocumentHTML", "<html><body>test body</body></html>",
				access, representsResource);
	}

	public void testRetrieveDocumentHTML()
	{
		//RetrieveDocumentHTML _htmlcontroler = new RetrieveDocumentHTML();
		DAOHtmlDocs _htmlcontroler = new DAOHtmlDocs(emf_html);
		List _htmldocs = _htmlcontroler.retrieveDocumentHTML("document HTML test");
		for(int i=0; i<_htmldocs.size() ; i ++)
		{
			System.out.println("[HTMLControlerTest.testRetrieveDocumentHTML] label : " + ((DocumentHTML)_htmldocs.get(i)).getLabel()
					+ " id : " + ((DocumentHTML)_htmldocs.get(i)).getId()
					+ " contexte création : " + ((DocumentHTML)_htmldocs.get(i)).getContextCreation()
					+ " html : " + ((DocumentHTML)_htmldocs.get(i)).getHTMLContent());
		}
	}
	
	public void testCreateWebPage()
	{
		//CreateDocumentHTML _htmldoccontroler = new CreateDocumentHTML();
		DAOHtmlDocs _htmldoccontroler = new DAOHtmlDocs(emf_html);
		URI access = new URI();
		access.setEffectiveURI("http://efeectiveURI.access.WebPage.test");
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://efeectiveURI.representsResource.WebPage.test");
		_htmldoccontroler.createWebPage("WebPage HTML test", "HTMLControlerTest.testCreateWebPage", "<html><body>test body</body></html>",
				access, representsResource, access);
	}

	public void testRetrieveWebPage()
	{
		//RetrieveDocumentHTML _htmlcontroler = new RetrieveDocumentHTML();
		DAOHtmlDocs _htmlcontroler = new DAOHtmlDocs(emf_html);
		List _htmldocs = _htmlcontroler.retrieveWebPage("WebPage HTML test");
		for(int i=0; i<_htmldocs.size() ; i ++)
		{
			System.out.println("[HTMLControlerTest.testRetrieveWebPage] label : " + ((WebPage)_htmldocs.get(i)).getLabel()
					+ " id : " + ((WebPage)_htmldocs.get(i)).getId()
					+ " contexte création : " + ((WebPage)_htmldocs.get(i)).getContextCreation()
					+ " html : " + ((WebPage)_htmldocs.get(i)).getHTMLContent());
		}
	}
	
	public void testCreateSelectionHTML()
	{
		//CreateSelectionHTML _htmldoccontroler = new CreateSelectionHTML();
		DAOHtmlDocs _htmldoccontroler = new DAOHtmlDocs(emf_html);
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://effectiveURI.representsResource.selectionhtml.test");
		Resource selectionOrigin = new Resource();
		selectionOrigin.setContextCreation("HTMLControlerTest.testCreateSelectionHTML");
		selectionOrigin.setCreation(new Date());
		selectionOrigin.setLabel("selectionOrgigine");
		URI representsOrigine = new URI();
		representsOrigine.setEffectiveURI("http://effectiveURI.representsResource.selectionOrigine.selectionhtml.test");
		selectionOrigin.setRepresentsResource(representsOrigine);
		_htmldoccontroler.createSelectionHTML("selectionHTML de test", "HTMLControlerTest.testCreateSelectionHTML", "<body>test body selection</body>", "xpointerBegin", 
				"xpointerEnd", representsResource, selectionOrigin);
	}

	public void testRetrieveSelectionHTML()
	{
		//RetrieveSelectionHTML _htmlcontroler = new RetrieveSelectionHTML();
		DAOHtmlDocs _htmlcontroler = new DAOHtmlDocs(emf_html);
		List _htmldocs = _htmlcontroler.retrieveSelectionHTML("selectionHTML de test");
		for(int i=0; i<_htmldocs.size() ; i ++)
		{
			System.out.println("[HTMLControlerTest.testRetrieveSelectionHTML] label : " + ((SelectionHTML)_htmldocs.get(i)).getLabel()
					+ " id : " + ((SelectionHTML)_htmldocs.get(i)).getId()
					+ " contexte création : " + ((SelectionHTML)_htmldocs.get(i)).getContextCreation()
					+ " html : " + ((SelectionHTML)_htmldocs.get(i)).getHTMLContent());
		}
	}
}