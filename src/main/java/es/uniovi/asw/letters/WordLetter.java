package es.uniovi.asw.letters;

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

	/**
	 * Sobreescritura del método write para escribir la carta 
	 * al usuario en formato Microsoft Word
	 */
	@Override
	public void write(String mensaje) throws IOException {
		
	}

}
