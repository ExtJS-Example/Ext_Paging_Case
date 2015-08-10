package extjs;

public class Person {

	int id;
	String name;
	String birth;
	public int getId() {
		return id;
	}
	public Person(int id, String name, String birth) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
