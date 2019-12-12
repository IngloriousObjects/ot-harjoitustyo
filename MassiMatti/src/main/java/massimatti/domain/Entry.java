package massimatti.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entry {

    private Integer id;
    private LocalDate date;
    private Boolean type;
    private Double sum;
    private String category;
    private String user;

    public Entry(Integer id, LocalDate date, Boolean type, Double sum, String category, String user) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.sum = sum;
        this.category = category;
        this.user = user;
    }

    public Entry(LocalDate date, Boolean type, Double sum, String category, String user) {

        this.date = date;
        this.type = type;
        this.sum = sum;
        this.category = category;
        this.user = user;
    }

    public void setId(Integer id) {

        this.id = id;
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

    public Integer getId() {

        return id;
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
