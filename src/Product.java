/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */
public class Product {
    private String name;
    private double price;
    private String pricePerPcsOrKg;
    private String fruitOrVegetable;
    private String productType;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String pricePerPcsOrKg, String fruitOrVegetable, String productType) {
        this.name = name;
        this.price = price;
        this.pricePerPcsOrKg = pricePerPcsOrKg;
        this.fruitOrVegetable = fruitOrVegetable;
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPricePerPcsOrKg() {
        return pricePerPcsOrKg;
    }

    public void userSetPricePerPcsOrKg(String pricePerPcsOrKg) {
        this.pricePerPcsOrKg = pricePerPcsOrKg;
    }

    public String getFruitOrVegetable() {
        return fruitOrVegetable;
    }

    public void setFruitOrVegetable(String fruitOrVegetable) {
        this.fruitOrVegetable = fruitOrVegetable;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "\nName of the product: " + name + "\nPrice: " + price + " kr per " + pricePerPcsOrKg + "\nPrimarytype: " + fruitOrVegetable + "\nSecondarytype: " + productType;
    }
}
