package gr.hua.katanemhmena.model;

public class Auction {
	
	private int id;
	private String title;
	private String location;
	private String description;
	    
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		return "{id="+id+",title="+title+",location="+location+",description="+description+"}";
	}
}
