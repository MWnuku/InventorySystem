package org.example.inventorysystem.models;

public enum Group {
	Computer("Computer asset", 487),
	Furniture("Furniture asset", 809),
	Measure("Measure asset", 664),
	Audiovisual("Audiovisual asset", 662),
	Intelectual("Intelectual asset", 0202);


	private String description;
	private int number;
	Group(String description, int number){
		this.description = description;
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static Group getGroup(int number){
		for(Group group : Group.values()){
			if(group.getNumber() == number){
				return group;
			}
		}
		throw new IllegalArgumentException("No group with number " + number + " found");
	}
}
