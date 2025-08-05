package bill;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Item class to represent a product/item in the pharmacy
class Item{
    int quantity;
    String Item_name;
    double rate;
    double discount;
    
//  parameterazied Constructor
    Item(int quantity,String Item_name ,double rate ,double discount){
        this.quantity=quantity;
        this.Item_name=Item_name;
        this.rate=rate;
        this.discount=discount;
    }
//    create a non-parameterized method to get Discount price
    public double discountamount(){
        double discountprice = rate*discount/100* quantity;
        return discountprice;
    }
//  create a non-parameterized method to get a final amount after discount 
    double realamount(){
        double finalamount=(rate*quantity)-(rate*discount/100* quantity);
        return finalamount;
    }
}
class Receipt {
// Create a dynamic list to store all the purchased items in the receipt
    List <Item> items = new ArrayList<>();
    String dateTime;
    
//  Constructor to initialize and formate date and time
    Receipt() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy     HH:mm:ss");
        this.dateTime = now.format(formatter);
    }
// Parameterized method to add an item to the receipt
    void addItem(Item item ){
        items.add(item);
    }
//  create a non-parameterized method to print the receipt
    void printreceipt(){
        System.out.println("\n\n======================= RECEIPT =======================");
        System.out.println("                   KARACHI DRUG MART         ");
        System.out.println("    Main Disco Mor Chowrangi, Orangi Town Karachi.");
        System.out.println("                  CONTACT NO : 0331-2930347     ");
        System.out.println("                      CASH MEMO                   ");
        System.out.println("Date : " + dateTime);
        System.out.println("===========================================================");
        
//      Use for receipt formating
        System.out.printf("%-5s%-30s%-8s%-8s%-8s\n","Qty","Item name","Rate","Dis%","Amount");
        
        double grosstotal = 0 , totaldiscount = 0 ,nettotal=0;
        int itemcount=0;
        
//      Loop through each item to calculate totals and print item details
        for(Item item: items){
            double amount= item.realamount();
            grosstotal+=item.rate*item.quantity;
            totaldiscount+=item.discountamount();
            nettotal+= amount;
            itemcount+=item.quantity;
            
            System.out.printf("%-5s%-30s%-8s%-8s%-8s\n", item.quantity , item.Item_name , item.rate , item.discount , amount);
        }
        System.out.println("==========================================================");
        System.out.printf("Total ITEMS : %d\n", itemcount );
        System.out.printf("Gross Total :- %.2f\n",grosstotal);
        System.out.printf("Total Discount :- %.2f\n",totaldiscount);
        System.out.printf("Net Total :- %.2f\n",nettotal);
        System.out.println("==========================================================");
        System.out.println("Fridge items, loose tablets & Sugar Strip"
                + "\nare non-refundable & non exchangeable.\nNo return & No exchange other items after 2 days\nwithout bill.");
    }
}
// create a main Class
public class Bill {

    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        Receipt r = new Receipt();
        System.out.println("=== Pharmacy Billing System ===");
//      loop to take input multiple times
        do{
            System.out.println("\nEnter the Details of items\nif you want to exit enter 0 in quanity");
            System.out.print("Enter the Quantity of item: ");
             int quan = s.nextInt();
             s.nextLine();
           if (quan == 0){
            break;
            }
            System.out.print("Enter the name of item: ");
            String nam = s.nextLine();
            
            System.out.print("Enter the Rate : ");
            double rate = s.nextDouble();
            
            System.out.print("Enter the Discount(in percentage): ");
            double dis =s.nextDouble();
            
//          Create item and add to receipt
            Item item = new Item(quan,nam,rate,dis);
            r.addItem(item);
        
        }while(true);
      
// print the receipt        
   r.printreceipt();
    }   
}
