package massimatti.domain;

public class Category {

    //private Integer categoryId;
    private String categoryName;

    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {

        return this.categoryName;
    }
}
