package es.uniovi.asw.writer;


import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

/**
 * Clase que implementa la interfaz WriteLetter para el envío de cartas PDF
 * @author david
 *
 */
public class PDFLetter extends AbstractWriteLetter{

	
	public PDFLetter(String userName, String userPass) {
		super(userName, userPass);
	}

	@Override
	public void write(String mensaje) throws IOException {
		Document document;
		FileOutputStream pdfFile = new FileOutputStream("src/main/resources/letters/PDF/"+this.userName+".pdf");
	}
}
