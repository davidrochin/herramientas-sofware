import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ITLM
 */
public class BankTest {
    
    Bank bank;
    Client client;
    
    @Before
    public void setUp() {
        bank = new Bank();
        
        // Creamos y registramos el cliente de prueba en el banco
        client = new Client();
        bank.addClient(cligent);
    }
        
    // Tests -------------------------------------------------------------------
    
    @Test
    public void addClient(){
        bank.addClient(new Client());
        bank.addClient(new Client());
        bank.addClient(new Client());
        
        assertEquals(bank.getClients().size(), 4);
        
        bank.addClient(new Client());
        bank.addClient(new Client());
        
        assertEquals(bank.getClients().size(), 6);
    }
    
    @Test(expected = NullPointerException.class)
    public void addNullClient(){
        bank.addClient(null);
    }
    
    @Test
    public void removeClient(){
        
        Client clientB = new Client();
        Client clientC = new Client();
        
        bank.addClient(clientB);
        bank.addClient(clientC);
        assertEquals(3, bank.getClients().size());
        bank.removeClient(clientB);
        bank.removeClient(clientC);
        assertEquals(1, bank.getClients().size());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void removeNotFoundClient(){
        try {
            bank.removeClient(new Client());
        } catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    
    @Test
    public void listClients(){
        
        bank.addClient(new Client("David", "Rochín"));
        bank.addClient(new Client("Luis", "Aguirre"));
        bank.addClient(new Client("Jorge", "Castro"));
        bank.addClient(new Client("Alan", "Flores"));
        bank.addClient(new Client("Miguel", "Navarro"));
        bank.addClient(new Client("Marco", "Soto"));
        bank.addClient(new Client("Zacarías", "Zúñiga"));
        
        // Revisar si estan ordenados
        boolean sorted = true;
        String last = null;
        
        for(String line : bank.listClients().split("\\r?\\n")){
            if(last == null){
                last = line;
            } else {
                if(line.compareTo(last) < 0){
                    sorted = false;
                    break;
                }
            }
        }
        
        assertTrue(sorted);
    }
    
    @Test
    public void getBalance(){
        bank.deposit(new Float(1000), client);
        assertEquals(new Float(1000), bank.getBalance());
    }
    
    @Test
    public void getBalanceNoTransactions(){
        assertEquals(new Float(0), bank.getBalance());
    }
    
    @Test
    public void getClientBalance(){
        bank.deposit(new Float(1000), client);
        assertEquals(new Float(1000), bank.getBalance(client));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void getNotFoundClientBalance(){
        try {
            bank.getBalance(new Client());
        } catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    
    @Test
    public void deposit(){        
        bank.deposit(new Float(5000), client);
        assertEquals(new Float(5000), bank.getBalance(client));
        bank.deposit(new Float(500), client);
        assertEquals(new Float(5500), bank.getBalance(client));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void depositToNotFoundClient(){
        try {
            bank.deposit(new Float(1000), new Client());
        } catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void depositNegative(){
        bank.deposit(new Float(-1000), client);
    }
    
    @Test(expected = NullPointerException.class)
    public void depositNull(){
        bank.deposit(null, client);
    }
    
    @Test
    public void withdraw(){        
        bank.deposit(new Float(5000), client);     
        bank.withdraw(new Float(500), client);
        assertEquals(new Float(4500), bank.getBalance(client));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void withdrawNegative(){
        bank.withdraw(new Float(-1000), client);
    }
    
    @Test(expected = NullPointerException.class)
    public void withdrawNull(){
        bank.withdraw(null, client);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void withdrawFromNotFoundClient(){
        try {
            bank.withdraw(new Float(1000), new Client());
        } catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    
    @Test
    public void getClientWithHighestBalance(){
        
        Client clientB = new Client();
        Client clientC = new Client();
        
        bank.addClient(clientB);
        bank.addClient(clientC);
        
        bank.deposit(new Float(500), clientB);
        bank.deposit(new Float(200), clientC);
        bank.deposit(new Float(1000), client);
        
        assertEquals(client, bank.getClientWithHighestBalance());
    }
    
    @Test
    public void getClientWithLowestBalance(){
        Client clientB = new Client();
        Client clientC = new Client();
        
        bank.addClient(clientB);
        bank.addClient(clientC);
        
        bank.deposit(new Float(500), clientB);
        bank.deposit(new Float(800), clientC);
        bank.deposit(new Float(100), client);
        
        assertEquals(client, bank.getClientWithLowestBalance());
    }
    
    @Test
    public void getClientsWithHightestBalance(){
        Client clientB = new Client();
        Client clientC = new Client();
        
        bank.addClient(clientB);
        bank.addClient(clientC);
        
        bank.deposit(new Float(1000), clientB);
        bank.deposit(new Float(1000), clientC);
        bank.deposit(new Float(500), client);
        
        List<Client> arr = new ArrayList<Client>(Arrays.asList(bank.getClientsWithHighestBalance()));
        assertTrue(arr.contains(clientB) && arr.contains(clientC));
        
    }
    
    @Test
    public void getClientsWithLowestBalance(){
        Client clientB = new Client();
        Client clientC = new Client();
        
        bank.addClient(clientB);
        bank.addClient(clientC);
        
        bank.deposit(new Float(500), clientB);
        bank.deposit(new Float(500), clientC);
        bank.deposit(new Float(1000), client);
        
        List<Client> arr = new ArrayList<Client>(Arrays.asList(bank.getClientsWithLowestBalance()));
        assertTrue(arr.contains(clientB) && arr.contains(clientC));
    }
}
