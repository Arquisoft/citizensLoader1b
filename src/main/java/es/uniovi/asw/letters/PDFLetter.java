package es.uniovi.asw.letters;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.CitizenDB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

/**
 * Clase que implementa la interfaz WriteLetter para el envío de cartas PDF
 * @author david
 *
 */
public class PDFLetter extends AbstractWriteLetter{

	private Document document;
	
	public PDFLetter(List<CitizenDB> citizens,String message) {
		super(citizens,message);
		for(CitizenDB citizen : citizens){
			document = this.createDocument(citizen);
			this.write(citizen);
		}
	}

	/**
	 * Sobreescritura del método write para escribir la carta 
	 * al usuario en formato PDF
	 */
	@Override
	public void write(CitizenDB citizenDB){
		if(document != null){
			try{
				document.add(new Paragraph("Estimado "+citizenDB.getName()));
				document.add(new Paragraph(this.message));
				document.add(new Paragraph("Usuario: "+citizenDB.getMail()));
				document.add(new Paragraph("Contraseña: "+citizenDB.getPassword()));
			}
			catch(DocumentException e){
				System.out.println("Error al crear el documento: "+citizenDB.getName()+".pdf");
				e.printStackTrace();
			}
			this.document.close();
		}
	}
	
	/**
	 * Se encarga de crear un Documento pdf con el nombre del usuario para su posterior edición
	 */
	private Document createDocument(CitizenDB citizen){
		Document document = new Document();
		try{
		FileOutputStream pdfFile;
			pdfFile = new FileOutputStream("src/main/resources/letters/PDF/"+citizen.getName()+".pdf");
			PdfWriter.getInstance(document, pdfFile).setInitialLeading(20);
			document.open();
		} catch (DocumentException e) {
			System.out.println("Error al crear el documento: "+citizen.getName()+".pdf");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		}
		return document;
	}
}
