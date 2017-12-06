import java.util.Random;

public class InNOutTest {
	public static void main(String[] args) {
		
		Random random = new Random();
		//variables
		int nextShipment = 1;
		int orderNumber = 0;
		
		InNOut test = new InNOut();
		int day = 1201;
		
		//menu list with all options
		LinkedList<String> orderMenu = new LinkedList<String>();
		orderMenu.add("Burger");
		orderMenu.add("Cheese Burger");
		orderMenu.add("Vegan lettuce wrap Burger");
		orderMenu.add("Burger No Onion");
		orderMenu.add("Cheeser Burger No Onion");
		orderMenu.add("Burger No Tomato");
		
		
		while(day < 1232)
		{
			
			System.out.println("December " + day % 100);
			if(nextShipment == 0)	//shipment arrives
			{
				System.out.println("New shipment has arrived.");
				test.newShipment();
				nextShipment = random.nextInt(4) + 3;
			}
			else {
				System.out.println("No shipment today");
				nextShipment--;
			}
			
			int customerServed = 0;
			int lostCustomerDay = 0;
			
			
			for(int hours = 0; hours < 10; hours++) //10 hours each day, from 10AM - 7PM
			{
				int numOfCustomers = random.nextInt(100) +1;	/// customers from 1 to 100.
				int tempNumOfCustomers = numOfCustomers;
				while(tempNumOfCustomers != 0) {
					
					orderNumber = random.nextInt(6) +1;
					if(!test.checkQueue(orderNumber)) //if checkline(line full) returns false, 
					{
						lostCustomerDay++;
					}
					tempNumOfCustomers--;
				}
				
				if(numOfCustomers > 50)		//line can only have 50 people
				{
					lostCustomerDay += numOfCustomers - 50;		// keeping track of customers lost 
					numOfCustomers = 50;		//reset to maximum number of customers allowed
				}
				
				while(numOfCustomers != 0) 
				{
					if(!test.Order())	//cant order, not enough ingredients
					{
						lostCustomerDay++;
					}
					numOfCustomers--;
				}
			}
			day++;
			
			int wasteBun = test.ExpiredItemBun();
		    int wastePatty = test.ExpiredItemPatty();
		    int wasteLettuce = test.ExpiredItemLettuce();
		    int wasteTomato = test.ExpiredItemTomato();
		    int wasteOnion = test.ExpiredItemOnion();
		    int wasteCheese = test.ExpiredItemCheese();
			
			System.out.println("Lost Customers: " + lostCustomerDay);
			System.out.println("Expired Items:");
			System.out.println("Buns: " + wasteBun);
		    System.out.println("Patties: " + wastePatty);
		    System.out.println("Lettuces: " + wasteLettuce);
		    System.out.println("Tomatoes: " + wasteTomato);
		    System.out.println("Onions: " + wasteOnion);
		    System.out.println("Cheeses: " + wasteCheese);
		    
		    test.printDictionary();
			test.NumOfItemsOrdered();
			
			if(customerServed == 0)
		    {
		        System.out.println("No orders today.");
		    }
			
			System.out.println();
		}
		
		}
}
