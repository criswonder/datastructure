package interviewFunFun;

public class Parcel{
	private String name;
	private String id;
	public Parcel(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}