package br.com.kibutx.bixochecker.test.vestibulares;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;
import br.com.kibutx.bixochecker.vestibulares.ComvestProcessor;

public class ComvestProcessorTest extends TestCase {
	public void testGetResultadosPrimeiraFase() throws IOException {
		VestibularProcessor v = new ComvestProcessor();
		InputStream in = ComvestProcessorTest.class
				.getResourceAsStream("ComvestMockFile.html");
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		List<String> list = v.getResults(new String(bytes));
		List<String> names = new ArrayList<String>();
		names.add("Abes Mahmed Am");
		names.add("Aurelio Ryu Komorita");
		names.add("Auro Augusto de Araujo");
		names.add("Barbara Emili Perazolli");
		names.add("Barbara Ferrarezi");
		names.add("Barbara Fontanelli Grigolli");
		names.add("Zeino de Oliveira Avila");
		names.add("Zenilda Ledo dos Santos");
		names.add("Zilda Conceicao da Silva");

		assertList(names, list);
	}

	public void assertList(List a, List b) {
		assertEquals(a.size(), b.size());

		for (int i = 0; i < a.size(); i++) {
			assertEquals(a.get(i), b.get(i));
		}
	}
}
