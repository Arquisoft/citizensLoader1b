package es.uniovi.asw.letterWriter;

import java.io.IOException;

/**
 * Clase que implementa la interfaz WriterLetter para el envío de cartas con formato .doc
 * @author david
 *
 */
public class WordLetter extends AbstractWriteLetter {

	public WordLetter(String userName,String userPass) {
		super(userName,userPass);
	}

	@Override
	public void write(String mensaje) throws IOException {
		
	}

}
