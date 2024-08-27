package model;

public enum Category {
	ART(0, "예술"), 
	MUSIC(1, "음악"), 
	WRITING(3, "글쓰기"), 
	DESIGN(4, "디자인"),
	TECHNOLOGY(5, "기술"),
	ETC(6, "기타");

	private final int id;
	private final String name;

	Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static String getNameById(int id) {
		for (Category category : Category.values()) {
			if (category.getId() == id) {
				return category.getName();
			}
		}
		return "기타";
	}

	public static int getIdByName(String name) {
		for (Category category : Category.values()) {
			if (category.getName().equalsIgnoreCase(name)) {
				return category.getId();
			}
		}
		return -1;
	}
}
