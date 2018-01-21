package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class PatchForm {

	@NotNull
	private String op;
	
	@NotNull
	private String path;
	
	@NotNull
	private String value;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String newValue) {
		this.value = newValue;
	}

	@Override
	public String toString() {
		return "PatchForm [op=" + op + ", path=" + path + ", value=" + value + "]";
	}

	
}
