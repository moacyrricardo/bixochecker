package br.com.kibutx.bixochecker.test.vestibulares;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;
import br.com.kibutx.bixochecker.vestibulares.FuvestProcessor;

public class FuvestProcessorTest extends TestCase {
	public void testGetResultadosPrimeiraFase() throws IOException {
		VestibularProcessor v = new FuvestProcessor();
		InputStream in = FuvestProcessorTest.class
				.getResourceAsStream("FuvestMockFile.txt");
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		List<String> list = v.getResults(new String(bytes));
		List<String> names = new ArrayList<String>();
		names.add("Aaron Marques Fre");
		names.add("Abel Scupeliti Artilheiro");
		names.add("Abimael de Albuquerque Pinto");
		names.add("Balthazar Borges Pereira");
		names.add("Barbara A Catalani Mellem Kairala");
		names.add("Barbara Alcantara Pereira");
		names.add("Cacilia Leonelli");
		names.add("Caetano Candal Sato");
		names.add("Caetano Mader Gisi");

		assertList(names, list);
	}

	public void assertList(List a, List b) {
		assertEquals(a.size(), b.size());

		for (int i = 0; i < a.size(); i++) {
			assertEquals(a.get(i), b.get(i));
		}
	}

}
