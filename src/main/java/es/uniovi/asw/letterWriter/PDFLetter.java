package es.uniovi.asw.letterWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfWriter;
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
	
	public PDFLetter(String userName, String userPass) {
		super(userName, userPass);
		document = this.createDocument();
	}

	/**
	 * Sobreescritura del método write para escribir la carta 
	 * al usuario en formato PDF
	 */
	@Override
	public void write(String mensaje) throws IOException {
		if(document != null){
			try{
				document.add(new Paragraph("Estimado "+this.userName));
				document.add(new Paragraph(mensaje));
				document.add(new Paragraph("Contraseña: "+this.userPass));
			}
			catch(DocumentException e){
				System.out.println("Error al crear el documento: "+this.userName+".pdf");
				e.printStackTrace();
			}
			this.document.close();
		}
	}
	
	/**
	 * Se encarga de crear un Documento pdf con el nombre del usuario para su posterior edición
	 */
	private Document createDocument(){
		Document document = new Document();
		try{
		FileOutputStream pdfFile;
			pdfFile = new FileOutputStream("src/main/resources/letters/PDF/"+this.userName+".pdf");
			PdfWriter.getInstance(document, pdfFile).setInitialLeading(20);
			document.open();
		} catch (DocumentException e) {
			System.out.println("Error al crear el documento: "+this.userName+".pdf");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		}
		return document;
	}
}
