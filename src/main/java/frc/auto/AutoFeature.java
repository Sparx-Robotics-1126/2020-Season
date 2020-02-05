package frc.auto;

public enum AutoFeature {
	STOP("Stopping Auto");
	
	private String name;
	private AutoFeature(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
