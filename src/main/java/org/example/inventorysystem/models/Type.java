package org.example.inventorysystem.models;

import lombok.Getter;

@Getter
public enum Type {
	Computer("Computer asset", 487),
	Furniture("Furniture asset", 809),
	Measure("Measure asset", 664),
	Audiovisual("Audiovisual asset", 662),
	Intelectual("Intelectual asset", 0202);


	private final String description;
	private final int number;
	Type(String description, int number){
		this.description = description;
		this.number = number;
	}

	public static Type getGroup(int number){
		for(Type type : Type.values()){
			if(type.getNumber() == number){
				return type;
			}
		}
		throw new IllegalArgumentException("No group with number " + number + " found");
	}

	public static String getDescription(int number){
		for(Type type : Type.values()){
			if(type.getNumber() == number){
				return type.getDescription();
			}
		}
		throw new IllegalArgumentException("No group with number " + number + " found");
	}
}
