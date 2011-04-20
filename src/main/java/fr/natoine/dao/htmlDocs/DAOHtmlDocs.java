/*
 * Copyright 2010 Antoine Seilles (Natoine)
 *   This file is part of dao-htmlDocs.

    controler-user is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    controler-user is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with dao-htmlDocs.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.natoine.dao.htmlDocs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.natoine.model_htmlDocs.DocumentHTML;
import fr.natoine.model_htmlDocs.SelectionHTML;
import fr.natoine.model_htmlDocs.WebPage;
import fr.natoine.model_resource.Resource;
import fr.natoine.model_resource.URI;
import fr.natoine.stringOp.StringOp;

public class DAOHtmlDocs 
{
	private EntityManagerFactory emf = null ;
	
	public DAOHtmlDocs(EntityManagerFactory _emf)
	{
		emf = _emf ;
	}
	
	//CreateDocumentHTML
	/**
	 * Creates a DocumentHTML
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param access
	 * @param representsResource
	 * @return
	 */
	public boolean createDocumentHTML(String label, String context_creation, String hTMLContent, URI access, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label) && !StringOp.isNull(hTMLContent))
		{
			DocumentHTML _dochtml = new DocumentHTML();
			_dochtml.setContextCreation(context_creation);
			_dochtml.setCreation(new Date());
			_dochtml.setHTMLContent(hTMLContent);
			_dochtml.setLabel(label);
			_dochtml.setAccess(access);
			_dochtml.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _dochtml.setRepresentsResource(_synchro_represents_resource);
				}
		        if(access.getId() != null)
				{
					URI _synchro_access = em.find(access.getClass(), access.getId());
					if(_synchro_access != null) _dochtml.setAccess(_synchro_access);
				}
		        em.persist(_dochtml);
		        tx.commit();
		        //em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	tx.rollback();
	        	System.out.println("[CreateDocumentHTML.createDocumentHTML] fails to create docHTML"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " html : " + hTMLContent
	        			+ " cause : " + e.getMessage());
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreateDocumentHTML.createDocumentHTML] unable to persist docHTML"
					+ " not a valid label : " + label
					+ " or not a valid html content : " + hTMLContent);
			return false;
		}
	}
	/**
	 * Creates a WebPAge
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param access
	 * @param representsResource
	 * @param principalURL
	 * @return
	 */
	public boolean createWebPage(String label, String context_creation, String hTMLContent, URI access, URI representsResource, URI principalURL)
	{
		label = StringOp.deleteBlanks(label);
		//if(!StringOp.isNull(label) && !StringOp.isNull(hTMLContent))
		if(!StringOp.isNull(label))
		{
			WebPage _wp = new WebPage();
			_wp.setContextCreation(context_creation);
			_wp.setCreation(new Date());
			_wp.setHTMLContent(hTMLContent);
			_wp.setLabel(label);
			_wp.setAccess(access);
			_wp.setRepresentsResource(representsResource);
			_wp.setPrincipalURL(principalURL);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _wp.setRepresentsResource(_synchro_represents_resource);
				}
		        if(access.getId() != null)
				{
					URI _synchro_access = em.find(access.getClass(), access.getId());
					if(_synchro_access != null) _wp.setAccess(_synchro_access);
				}
		        if(principalURL.getId() != null)
				{
					URI _synchro_principalURL = em.find(principalURL.getClass(), principalURL.getId());
					if(_synchro_principalURL != null) _wp.setPrincipalURL(_synchro_principalURL);
				}
		        em.persist(_wp);
		        tx.commit();
		        //em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	tx.rollback();
	        	System.out.println("[CreateDocumentHTML.createWebPage] fails to create WebPage"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " html : " + hTMLContent
	        			+ " cause : " + e.getMessage());
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreateDocumentHTML.createWebPage] unable to persist WebPage"
					+ " not a valid label : " + label
					+ " or not a valid html content : " + hTMLContent);
			return false;
		}
	}
	/**
	 * Creates and returns a WebPage
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param access
	 * @param representsResource
	 * @param principalURL
	 * @return
	 */
	public WebPage createAndGetWebPage(String label, String context_creation, String hTMLContent, URI access, URI representsResource, URI principalURL)
	{
		label = StringOp.deleteBlanks(label);
		//if(!StringOp.isNull(label) && !StringOp.isNull(hTMLContent))
		if(!StringOp.isNull(label))
		{
			WebPage _wp = new WebPage();
			_wp.setContextCreation(context_creation);
			_wp.setCreation(new Date());
			_wp.setHTMLContent(hTMLContent);
			_wp.setLabel(label);
			_wp.setAccess(access);
			_wp.setRepresentsResource(representsResource);
			_wp.setPrincipalURL(principalURL);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
	        	 tx.begin();
			        if(representsResource.getId() != null)
					{
						URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
						if(_synchro_represents_resource != null) _wp.setRepresentsResource(_synchro_represents_resource);
					}
			        if(access.getId() != null)
					{
						URI _synchro_access = em.find(access.getClass(), access.getId());
						if(_synchro_access != null) _wp.setAccess(_synchro_access);
					}
			        if(principalURL.getId() != null)
					{
						URI _synchro_principalURL = em.find(principalURL.getClass(), principalURL.getId());
						if(_synchro_principalURL != null) _wp.setPrincipalURL(_synchro_principalURL);
					}
			        em.persist(_wp);
		        tx.commit();
		        //em.close();
		        return _wp ;
	        }
	        catch(Exception e)
	        {
	        	//tx.rollback();
	        	System.out.println("[CreateDocumentHTML.createAndGetWebPage] fails to create WebPage"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " cause : " + e.getMessage());
	        	//em.close();
	        	return new WebPage();
	        }
		}
		else
		{
			System.out.println("[CreateDocumentHTML.createAndGetWebPage] unable to persist WebPage"
					+ " not a valid label : " + label);
			return new WebPage();
		}
	}
	
	//RetrieveDocumentHTML
	/**
	 * Retrieves all the DocumentHTML in the database with the specified label.
	 * @param label
	 * @return a List of DocumentHTML that may be empty
	 */
	public List<DocumentHTML> retrieveDocumentHTML(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<DocumentHTML> docs = em.createQuery("from DocumentHTML where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return docs;
		}
		catch(Exception e)
		{
			tx.rollback();
			System.out.println("[RetrieveDocumentHTML.retrieveDocumentHTML] unable to retrieve DocumentHTML"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<DocumentHTML>();
		}
	}
	
	/**
	 * Retrieves all the WebPage in the database with the specified label.
	 * @param label
	 * @return a List of WebPage that may be empty
	 */
	public List<WebPage> retrieveWebPage(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<WebPage> docs = em.createQuery("from WebPage where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return docs;
		}
		catch(Exception e)
		{
			tx.rollback();
			System.out.println("[RetrieveDocumentHTML.retrieveWebPage] unable to retrieve WebPage"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<WebPage>();
		}
	}
	
	//CreateSelectionHTML
	/**
	 * Creates a SelectionHTML
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param xpointerBegin
	 * @param xpointerEnd
	 * @param representsResource
	 * @param selectionOrigin
	 * @return
	 */
	public boolean createSelectionHTML(String label, String context_creation, String hTMLContent, String xpointerBegin, String xpointerEnd, URI representsResource, Resource selectionOrigin)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label) && !StringOp.isNull(hTMLContent))
		{
			SelectionHTML _selectionhtml = new SelectionHTML();
			_selectionhtml.setContextCreation(context_creation);
			_selectionhtml.setCreation(new Date());
			_selectionhtml.setHTMLContent(hTMLContent);
			_selectionhtml.setLabel(label);
			_selectionhtml.setRepresentsResource(representsResource);
			_selectionhtml.setSelectionOrigin(selectionOrigin);
			_selectionhtml.setXpointerBegin(xpointerBegin);
			_selectionhtml.setXpointerEnd(xpointerEnd);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _selectionhtml.setRepresentsResource(_synchro_represents_resource);
				}
		        if(selectionOrigin.getId() != null)
				{
					Resource _synchro_selectionOrigin = em.find(selectionOrigin.getClass(), selectionOrigin.getId());
					if(_synchro_selectionOrigin != null) _selectionhtml.setSelectionOrigin(_synchro_selectionOrigin);
				}
		        em.persist(_selectionhtml);
		        tx.commit();
		       // em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	tx.rollback();
	        	System.out.println("[CreateSelectionHTML.createSelectionHTML] fails to create selectionHTML"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " html : " + hTMLContent
	        			+ " cause : " + e.getMessage());
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreateSelectionHTML.createSelectionHTML] unable to persist selectionHTML"
					+ " not a valid label : " + label
					+ " or not a valid html content : " + hTMLContent);
			return false;
		}
	}
	/**
	 * Creates and Returns a SelectionHTML
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param xpointerBegin
	 * @param xpointerEnd
	 * @param representsResource
	 * @param selectionOrigin
	 * @return
	 */
	public SelectionHTML createAndGetSelectionHTML(String label, String context_creation, String hTMLContent, String xpointerBegin, String xpointerEnd, URI representsResource, Resource selectionOrigin)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label) && !StringOp.isNull(hTMLContent))
		{
			SelectionHTML _selectionhtml = new SelectionHTML();
			_selectionhtml.setContextCreation(context_creation);
			_selectionhtml.setCreation(new Date());
			_selectionhtml.setHTMLContent(hTMLContent);
			_selectionhtml.setLabel(label);
			_selectionhtml.setRepresentsResource(representsResource);
			_selectionhtml.setSelectionOrigin(selectionOrigin);
			_selectionhtml.setXpointerBegin(xpointerBegin);
			_selectionhtml.setXpointerEnd(xpointerEnd);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _selectionhtml.setRepresentsResource(_synchro_represents_resource);
				}
		        if(selectionOrigin.getId() != null)
				{
					Resource _synchro_selectionOrigin = em.find(selectionOrigin.getClass(), selectionOrigin.getId());
					if(_synchro_selectionOrigin != null) _selectionhtml.setSelectionOrigin(_synchro_selectionOrigin);
				}
		        em.persist(_selectionhtml);
		        tx.commit();
		        em.close();
		        return _selectionhtml ;
	        }
	        catch(Exception e)
	        {
	        	tx.rollback();
	        	System.out.println("[CreateSelectionHTML.createAndGetSelectionHTML] fails to create SelectionHTML"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " cause : " + e.getMessage());
	        	em.close();
	        	return new SelectionHTML();
	        }
		}
		else
		{
			System.out.println("[CreateSelectionHTML.createAndGetSelectionHTML] unable to persist SelectionHTML"
					+ " not a valid label : " + label);
			return new SelectionHTML();
		}
	}
	
	//RetrieveSelectionHTML
	/**
	 * Retrieves all the SelectionHTML in the database with the specified label.
	 * @param label
	 * @return a List of SelectionHTML that may be empty
	 */
	public List<SelectionHTML> retrieveSelectionHTML(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<SelectionHTML> selections = em.createQuery("from SelectionHTML where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return selections;
		}
		catch(Exception e)
		{
			tx.rollback();
			System.out.println("[RetrieveSelectionHTML.retrieveSelectionHTML] unable to retrieve SelectionHTML"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<SelectionHTML>();
		}
	}
}