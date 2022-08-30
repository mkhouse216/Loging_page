package com.example.loging_page;

public class Usermodel {
    String id; String userid; String product; String price0; String description; String image;

    public Usermodel(String id, String userid, String product, String price0, String description, String image) {
        this.id = id;
        this.userid = userid;
        this.product = product;
        this.price0 = price0;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice0() {
        return price0;
    }

    public void setPrice0(String price0) {
        this.price0 = price0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Usermodel{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", product='" + product + '\'' +
                ", price0='" + price0 + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';


    }
}
