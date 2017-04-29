package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String input;
	private boolean corretto;
	
	public RichWord(String input) {
		super();
		this.input = input;
	}

	public RichWord(String parola, boolean b) {
		input= parola;
		corretto=b;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public boolean isCorretto() {
		return corretto;
	}

	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		return true;
	}
	
	
}
