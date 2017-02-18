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

	public WordLetter(List<CitizenDB> citizens,String message) {
		super(citizens,message);
		for(CitizenDB citizen : citizens)
			this.write(citizen);
	}

	/**
	 * Sobreescritura del método write para escribir la carta 
	 * al usuario en formato Microsoft Word
	 */
	@Override
	public void write(CitizenDB citizen) {
		XWPFDocument document = new XWPFDocument();
		FileOutputStream wordFile;
		try {
			wordFile = new FileOutputStream("src/main/resources/letters/doc/"+citizen.getName()+".doc");
			this.writeParagraph(document,"Estimado usuario");
			this.writeParagraph(document,this.message);
			this.writeParagraph(document,"Usuario: "+citizen.getMail());
			this.writeParagraph(document,"Contraseña: "+citizen.getPassword());
			document.write(wordFile);
			wordFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error al generar el documento Word, no se encuentra el fichero");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al generar el documento Word");
		}
	}
	
	/**
	 * Método que se encarga de escribir un texto en un párrafo 
	 * de un documento Microsoft Word
	 * @param document
	 * @param text
	 */
	private void writeParagraph(XWPFDocument document,String text){
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.createRun().setText(text);
		paragraph.createRun().setFontSize(12);
		paragraph.createRun().addCarriageReturn();
	}
}
