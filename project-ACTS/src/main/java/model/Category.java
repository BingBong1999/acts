package model;

public enum Category {
	DESIGN(0, "디자인"), 
	IT(1, "IT"), 
	DOCUMENT(2, "문서"), 
	OTHER(3, "기타");

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
}
