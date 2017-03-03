package examples.pubhub.model;

public class Tag {
	
	private String tag_name;
	
	
	public Tag()	{
		tag_name = null;
	}
	
	public Tag(String name){
		tag_name = name;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
	

}
