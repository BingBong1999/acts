package model;

public enum Category {
	DESIGN(0, "디자인"), IT(1, "IT"), DOCUMENT(3, "문서"), OTHER(4, "기타");

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
