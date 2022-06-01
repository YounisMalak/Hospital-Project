package Model;

public class ChronicDisease extends Disease{

	private boolean genetic;

	public ChronicDisease(String name, boolean genetic) {
		super(name);
		this.genetic = genetic;
	}


	public ChronicDisease(int id) {
		super(id);
	}


	public boolean isGenetic() {
		return genetic;
	}


	public void setGenetic(boolean genetic) {
		this.genetic = genetic;
	}


	@Override
	public String toString() {
		return "ChronicDisease [genetic=" + genetic + "]";
	}


}
