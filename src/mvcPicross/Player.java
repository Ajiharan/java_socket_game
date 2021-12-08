package mvcPicross;

public class Player {
	
	private String name;
	private String id;
	private String points;
	private String time;
	
	public Player() {}
	
	public Player(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Player [" + id + "]," + name + ", points=" + points + ", time=" + time;
	}

	
	
	
	

}
