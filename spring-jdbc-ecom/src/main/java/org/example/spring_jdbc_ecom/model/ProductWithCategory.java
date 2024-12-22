package org.example.spring_jdbc_ecom.model;


public class ProductWithCategory {

    private int id;
    private String pTitle;
    private String pDescription;
    private int price;
    private String cTitle;
    private String cDescription;

    public ProductWithCategory(int id, String pTitle, String pDescription, String cTitle, String cDescription,int price) {
        this.id = id;
        this.pTitle = pTitle;
        this.pDescription = pDescription;
        this.cTitle = cTitle;
        this.cDescription = cDescription;
        this.price = price;
    }

    public ProductWithCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductWithCategory{" +
                "id=" + id +
                ", pTitle='" + pTitle + '\'' +
                ", pDescription='" + pDescription + '\'' +
                ", price=" + price +
                ", cTitle='" + cTitle + '\'' +
                ", cDescription='" + cDescription + '\'' +
                '}';
    }
}

