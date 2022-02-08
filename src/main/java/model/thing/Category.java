package model.thing;

public class Category {
    private Integer id;
    private String name;
    private Category superCategory;

    public Category(Integer id, String name, Category superCategory) {
        this.id = id;
        this.name = name;
        this.superCategory = superCategory;
    }

    public Category(int superCategoryId, String superCategoryName) {
        this.id = superCategoryId;
        this.name = superCategoryName;
    }

    public Category(String name, Category superCategory) {
        this.name = name;
        this.superCategory = superCategory;
    }

    public Category() {
    }

    public Category(String superCategoryName) {
        this.id = null;
        this.name = superCategoryName;
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Category superCategory) {
        this.superCategory = superCategory;
    }

    public Category(Category superCategory, Integer id) {
        this.superCategory = superCategory;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
