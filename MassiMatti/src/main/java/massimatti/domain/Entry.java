/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pjtoropa
 */
public class Entry {

    private LocalDate date;
    private Boolean type;
    private Double sum;
    private String category;
    private String user;

    public Entry(LocalDate date, Boolean type, Double sum, String category, String user) {

        this.date = date;
        this.type = type;
        this.sum = sum;
        this.category = category;
        this.user = user;

    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getType() {
        return type;
    }

    public Double getSum() {
        return sum;
    }

    public String getCategory() {
        return category;
    }

    public String getUser() {
        return user;
    }

    public String toString() {

        if (this.type == false) {
            return this.date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
                    + "  " + this.category + "  -" + this.sum + " euroa";
        } else {
            return this.date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
                    + "  " + this.category + "  " + this.sum + " euroa";
        }

    }

}
