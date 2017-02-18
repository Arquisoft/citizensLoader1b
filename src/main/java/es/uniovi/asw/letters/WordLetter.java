package es.uniovi.asw.letters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import es.uniovi.asw.CitizenDB;

/**
 * Clase que implementa la interfaz WriterLetter para el envío de cartas con formato .doc
 * @author david
 *
 */
public class WordLetter extends AbstractWriteLetter {

	private XWPFDocument document;
	
	public WordLetter(List<CitizenDB> citizens,String message) {
		super(citizens,message);
		for(CitizenDB citizen : citizens){
			this.document = new XWPFDocument();
			this.write(citizen);
		}
	}

	/**
	 * Sobreescritura del método write para escribir la carta 
	 * al usuario en formato Microsoft Word
	 */
	@Override
	public void write(CitizenDB citizen) {
		this.writeParagraph("Estimado usuario");
		this.writeParagraph(this.message);
		this.writeParagraph("Usuario: "+citizen.getMail());
		this.writeParagraph("Contraseña: "+citizen.getPassword());
		this.saveDocument("src/main/resources/letters/doc/"+citizen.getName());
	}
	
	
	/**
	 * Método que se encarga de escribir un texto en un párrafo 
	 * de un documento Microsoft Word
	 * @param document
	 * @param text
	 */
	private void writeParagraph(String text){
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.createRun().setText(text);
		paragraph.createRun().setFontSize(12);
		paragraph.createRun().addCarriageReturn();
	}
	
	/**
	 * Método que se encarga de escribir en 
	 * disco el documento. Recibe la ruta y el nombre del documento
	 * como parámetro
	 * @param title
	 */
	private void saveDocument(String title){
		try {
			FileOutputStream output = new FileOutputStream(title+".doc");
			document.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		}
	}
}
