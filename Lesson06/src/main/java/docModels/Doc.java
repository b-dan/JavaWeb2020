package docModels;

public class Doc {
	private String name;
	private String type;
	public Doc() {	
	}
	public Doc(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Doc [name=" + name + ", type=" + type + "]";
	}
	
	
	

}
