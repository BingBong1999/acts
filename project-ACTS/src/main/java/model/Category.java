package model;

public class Category {

	private int categoryId;
	private String categorName;

	public Category() {
	
	}

	public Category(int categoryId, String categorName) {
		super();
		this.categoryId = categoryId;
		this.categorName = categorName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorName() {
		return categorName;
	}

	public void setCategorName(String categorName) {
		this.categorName = categorName;
	}
}