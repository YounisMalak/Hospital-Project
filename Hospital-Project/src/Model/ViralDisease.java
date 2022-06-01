package Model;

public class ViralDisease extends Disease{

	private boolean contagious;

	public ViralDisease(String name, boolean contagious) {
		super(name);
		this.contagious = contagious;
	}


	public ViralDisease(int id) {
		super(id);
	}

	public boolean isContagious() {
		return contagious;
	}

	public void setContagious(boolean contagious) {
		this.contagious = contagious;
	}



	@Override
	public String toString() {
		return "ViralDisease [contagious=" + contagious + "]";
	}

}
