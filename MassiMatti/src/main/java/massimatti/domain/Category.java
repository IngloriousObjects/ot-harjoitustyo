/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

/**
 *
 * @author pjtoropa
 */
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
