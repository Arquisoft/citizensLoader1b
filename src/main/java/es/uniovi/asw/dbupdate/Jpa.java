package es.uniovi.asw.dbupdate;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Jpa {

	private static EntityManagerFactory emf = null;
	private static ThreadLocal<EntityManager> emThread = 
		new ThreadLocal<EntityManager>();
	
	/**
	 * Método para crear la EntityManager
	 * @return la EntityManager creada
	 */
	public static EntityManager createEntityManager() {
		EntityManager entityManager = getEmf().createEntityManager();
		emThread.set(entityManager);
		return entityManager;
	}

	/**
	 * Método para obtener la EntityManager que está en uso
	 * @return La EntityManager
	 */
	public static EntityManager getManager() {
		return emThread.get();
	}

	/**
	 * Método para obtener la EntityManagerFactory asociada a la unidad 
	 * de persistencia actual. En el caso de que no halla ninguna, se crea
	 * una asociándola a la unidad de persistencia actual.
	 * @return La EntityManagerFactory
	 */
	private static EntityManagerFactory getEmf() {
		if (emf == null){
			String persistenceUnitName = loadPersistentUnitName();
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return emf;
	}

	/**
	 * Método para cargar la unidad de persistencia
	 * @return El primer elemento de la unidad de persistencia
	 */
	private static String loadPersistentUnitName() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(Jpa.class.getResourceAsStream("/META-INF/persistence.xml"));

			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("persistence-unit");
			
			return ((Element)nl.item(0)).getAttribute("name");

		} catch (ParserConfigurationException e1) {
			throw new RuntimeException(e1);
		} catch (SAXException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}


}
