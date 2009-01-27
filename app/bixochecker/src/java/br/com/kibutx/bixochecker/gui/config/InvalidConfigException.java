package br.com.kibutx.bixochecker.gui.config;
/**
 * Essa exceção caracteriza um erro de validaçao da configuração
 * @author moa
 *
 */
public class InvalidConfigException extends Exception {
	private static final long serialVersionUID = -243832801055305601L;
	private String field;
	private String invalidValue;

	public InvalidConfigException(String field, String invalidValue) {
		this.field = field;
		this.invalidValue = invalidValue;
	}

	public InvalidConfigException(String field, String invalidValue,
			String message) {
		super(message);
		this.field = field;
		this.invalidValue = invalidValue;
	}

	public InvalidConfigException(String field, String invalidValue,
			String message, Throwable cause) {
		super(message, cause);
		this.field = field;
		this.invalidValue = invalidValue;
	}

	public String getField() {
		return field;
	}

	public String getInvalidValue() {
		return invalidValue;
	}
	
	public String getDefaultMessage(){
		return "Invalid value ("+getInvalidValue()+") for field \""+getField()+"\".";
	}
}
