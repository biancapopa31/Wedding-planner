public class Vendor extends Person{
    private String email;
    private double price;
    private String serviceType;
    private String notes;

    public Vendor(String lastName, String firstName, String telephone, String email, double price, String serviceType, String notes) {
        super(lastName, firstName, telephone);
        this.email = email;
        this.price = price;
        this.serviceType = serviceType;
        this.notes = notes;
    }

    public Vendor(String lastName, String firstName, String telephone, String email, double price, String serviceType) {
        super(lastName, firstName, telephone);
        this.email = email;
        this.price = price;
        this.serviceType = serviceType;
    }

    public Vendor(Person person, String email, double price, String serviceType, String notes) {
        super(person);
        this.email = email;
        this.price = price;
        this.serviceType = serviceType;
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public double getPrice() {
        return price;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Contact person: "+ super.toString() + "\nEmail: " + email + "\nPrice: " + price + " " + serviceType + "\nNotes: " + notes;
    }

    
}
