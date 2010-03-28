package br.com.kibutx.bixochecker.vestibular;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class SingleListVestibularProcessor implements
		VestibularProcessor {

	@Override
	public List<URL> getResultsURL() {
		List<URL> a = new ArrayList<URL>();
		a.add(getResultURL());
		return a;
	}

	public abstract URL getResultURL();

}
