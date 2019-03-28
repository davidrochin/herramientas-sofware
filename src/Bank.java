
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ITLM
 */
public class Bank {

    public static final String NO_SUCH_CLIENT_MESSAGE = "Cliente no existe";
    
    private final Map<Client, Float> data = new HashMap<>();

    private Float balance = 0f;
    
    public String listClients() {
        String result = "";
        
        List<Client> sorted = new ArrayList<>(data.keySet());
        sorted.sort((Client o1, Client o2) -> {
            return o1.getLastName().compareTo(o2.getLastName());
        });
        
        for(Client c : sorted){
            result += c.getLastName() + " " + c.getName() + ", $" + data.get(c) + "\n";
        }
        
        return result;
    }

    public void deposit(Float q, Client client){
        
        if(q <= 0){
            throw new IllegalArgumentException();
        }
        
        if(data.containsKey(client)){
            data.put(client, data.get(client) + q);
        } else {
            throw new NoSuchElementException(NO_SUCH_CLIENT_MESSAGE);
        }
    }
    
    public void withdraw(Float q, Client client){
        if(q <= 0){
            throw new IllegalArgumentException();
        }
        
        if(data.containsKey(client)){
            data.put(client, data.get(client) - q);
        } else {
            throw new NoSuchElementException(NO_SUCH_CLIENT_MESSAGE);
        }
    }
    
    public void addClient(Client c){
        
        if(c == null){
            throw new NullPointerException();
        }
        
        data.put(c, new Float(0));
    }
    
    public void removeClient(Client c){
        if(data.containsKey(c)){
            data.remove(c);
        } else {
            throw new NoSuchElementException(NO_SUCH_CLIENT_MESSAGE);
        }
    }
    
    public Collection<Client> getClients(){
        return data.keySet();
    }

    public Float getBalance(){
        Float balance = new Float(0);
        for(Float v : data.values()){
            balance += v;
        }
        return balance;
    }
    
    public Float getBalance(Client c){
        if(data.containsKey(c)){
            return data.get(c);
        } else {
            throw new NoSuchElementException(NO_SUCH_CLIENT_MESSAGE);
        }
    }
    
    public Client getClientWithHighestBalance(){
        Client highest = null;
        for(Client c : data.keySet()){
            if(highest == null || getBalance(c) > getBalance(highest)){
                highest = c;
            }
        }
        return highest;
    }
    
    public Client getClientWithLowestBalance(){
        Client lowest = null;
        for(Client c : data.keySet()){
            if(lowest == null || getBalance(c) < getBalance(lowest)){
                lowest = c;
            }
        }
        return lowest;
    }
    
    public Client[] getClientsWithHighestBalance(){
        Float highestBalance = getBalance(getClientWithHighestBalance());
        Set<Client> clients = new HashSet<>();
        for(Client c : data.keySet()){
            if(getBalance(c).equals(highestBalance)){
                clients.add(c);
            }
        }
        return clients.toArray(new Client[clients.size()]);
    }
    
    public Client[] getClientsWithLowestBalance(){
        Float lowestBalance = getBalance(getClientWithLowestBalance());
        Set<Client> clients = new HashSet<>();
        for(Client c : data.keySet()){
            if(getBalance(c).equals(lowestBalance)){
                clients.add(c);
            }
        }
        return clients.toArray(new Client[clients.size()]);
    }
    
    public static void main(String[] args){
        Bank bank = new Bank();
        
        bank.addClient(new Client("David", "Rochín"));
        bank.addClient(new Client("Luis", "Aguirre"));
        bank.addClient(new Client("Jorge", "Castro"));
        bank.addClient(new Client("Alan", "Flores"));
        bank.addClient(new Client("Miguel", "Navarro"));
        bank.addClient(new Client("Marco", "Soto"));
        bank.addClient(new Client("Zacarías", "Zúñiga"));
        
        System.out.println(bank.listClients());
    }
    
}
