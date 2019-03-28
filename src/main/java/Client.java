

/**
 *
 * @author ITLM
 */
public class Client {
    
    private String name = "Sin nombre";
    private String lastName = "Sin apellido";

    public Client() {
    }
    
    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
        
}
