import java.util.Iterator;
import java.util.Random;

public class InNOut {
	
	private ArrayQueue<Integer> queue;
	private int customerNumber;
	private int wasteBuns;
	private int wastePatty;
	private int wasteLettuce;
	private int wasteTomato;
	private int wasteOnion;
	private int wasteCheese;
	private int  itemOne;
	private int  itemTwo;
	private int  itemThree;
	private int  itemFour;
	private int  itemFive;
	private int  itemSix;
	private int totalFood;
	LinkedStack<Integer>  Bun;
	LinkedStack<Integer>  Patty;
	LinkedStack<Integer>  Lettuce;
	LinkedStack<Integer>  Tomato;
	LinkedStack<Integer>  Onion;
	LinkedStack<Integer>  Cheese;
	LinkedStack<Integer>  tempStack;
	NodeDictionary<Integer, Integer> dictionary = new NodeDictionary<Integer, Integer>();// Dictionary	<customerNumber, orderNumber>
	
	Random random = new Random();	//Random number generator
	
	public InNOut() 
	{	
		//Create stacks to hold ingredients
		Bun = new LinkedStack<Integer>();
		Patty = new LinkedStack<Integer>();
		Lettuce = new LinkedStack<Integer>();
		Tomato = new LinkedStack<Integer>();
		Onion = new LinkedStack<Integer>();
		Cheese = new LinkedStack<Integer>();
		queue = new ArrayQueue<Integer>(50);
		tempStack = new LinkedStack<Integer>();
		customerNumber = 1;
	}
	
	public void NumOfItemsOrdered()
	{
		System.out.println("Number Of item One Ordered: " + itemOne);
		System.out.println("Number Of item Two Ordered: " + itemTwo);
		System.out.println("Number Of item Three Ordered: " + itemThree);
		System.out.println("Number Of item Four Ordered: " + itemFour);
		System.out.println("Number Of item Five Ordered: " + itemFive);
		System.out.println("Number Of item Six Ordered: " + itemSix);
		
	}
	
	public void printDictionary()
	{
		Iterator<Integer> customerIterator = getCustomerIterator();
		Iterator<Integer> orderIterator = getOrderIterator();
		while(customerIterator.hasNext())
		{
			System.out.println("Customer #" + customerIterator.next()  + " Item #" + orderIterator.next());
		}
	}
	public void newShipment() {
		
				//Create new Shipment
				totalFood = random.nextInt(301) + 700;	//Randomly add 700-1000 per item
				int buns = 0;
				int patty = 0;
				int lettuce = 0;
				int tomato = 0;
				int onion = 0;
				int cheese = 0;
				
				while (totalFood != 0)
				{
					int ingredient = random.nextInt(6) + 1;	//randomly generate menu items 1-6
					if(ingredient == 1)
					{
						buns++;
					}
					if(ingredient == 2)
					{
						patty++;
					}
					if(ingredient == 3)
					{
						lettuce++;
					}
					if (ingredient == 4)
					{
						tomato++;
					}
					if(ingredient == 5)
					{
						cheese++;
					}
					totalFood--;
				}	//end loop	
				
				
				
				
				/**
				 	To re-order ingredient(new ---> expired)
				 1. Put(Pop) current ingredients in tempStack (Push)
				 2.	Put(Push) new ingredients onto to Current stack 
				 	* number pushed is expiration date
				 3.	Go back to tempStackstack(POP) and put old ingredients onto new Current Stack(Push)
				 */
				
				//Buns reorder
				while(!Bun.isEmpty())
				{
					tempStack.push(Bun.pop());
				}
				for(int i = 0; i < buns; i++) 
				{
					Bun.push(5);
				}
				while(!tempStack.isEmpty())
				{
					Bun.push(tempStack.pop());
				}
				
				//Patty reorder
				while(!Patty.isEmpty     ())
				{
					tempStack.push(Patty.pop());
				}
				for(int i = 0; i < patty; i++)
				{
					Patty.push(4);
				}
				while(!tempStack.isEmpty())
				{
					Patty.push(tempStack.pop());
				}
				
				//Lettuce reorder
				while(!Lettuce.isEmpty())
				{
					tempStack.push(Lettuce.pop());
				}
				for(int i = 0; i < lettuce; i++)
				{
					Lettuce.push(3);
				}
				while(!tempStack.isEmpty())
				{
					Lettuce.push(tempStack.pop());
				}
				
				//Tomato reorder
				while (!Tomato.isEmpty()) 
				{
					tempStack.push(Tomato.pop());
				}
				for (int i = 0; i < tomato; i++) 
				{
					Tomato.push(3); 
				}
				while (!tempStack.isEmpty()) 
				{
					Tomato.push(tempStack.pop());
				}
				
				//Onion reorder
				while (!Onion.isEmpty()) 
				{
					tempStack.push(Onion.pop());
				}
				for (int i = 0; i < onion; i++) 
				{
					Onion.push(5); 
				}
				while (!tempStack.isEmpty()) 
				{
					Onion.push(tempStack.pop());
				}
				
				//Cheese reorder
				while (!Cheese.isEmpty()) 
				{
					tempStack.push(Cheese.pop());
				}
				for (int i = 0; i < cheese; i++) 
				{
					Cheese.push(2); 
				}
				while (!tempStack.isEmpty())
				{
					Cheese.push(tempStack.pop());
				}
			
	}
	
	/**	
	 * 		Checks if the ingrdedients are in stock
	 * 		pop ingredients according to order number
	 * @return boolean True if the item is in stock, false if it is not
	 * @throws EmptyQueueException 
	 */
	public boolean Order() 
	{
		//gets  the first person in line
		int orderNumber = queue.dequeue();		
	
		//If bun stack and patty stack are empty
		if(Bun.isEmpty() || Patty.isEmpty())
		{
			if(orderNumber != 3)		//if order number is not equal to vegan lettuce Burger
			{
				return false;		//if stack is empty and order number is not equal to vegan lettuce Burger
			}
		}
		//tempItemk if Lettuce in stock
		if(Lettuce.isEmpty())	
		{
			return false;
		}
		//if no tomatos in stock
		if(Tomato.isEmpty())
		{
			if(orderNumber != 6)		//no tomatos in stock and not order 6
			{
				return false;
			}
		}
		
		if(Onion.isEmpty())		//no onions in stock
		{
			if(orderNumber != 4 || orderNumber != 5) 	//Order number does not = 4 or 5
			{
				return false;
			}
		}

		if(Cheese.isEmpty())		//no cheese in stock
		{
			if(orderNumber == 2 || orderNumber == 5) 
			{
				return false;
			}
		}
		
		System.out.println("l-----------------------------------" + orderNumber);
		if(orderNumber == 1)		//Burger
		{
			Bun.pop();
			Patty.pop();
			Lettuce.pop();
			Tomato.pop();
			Onion.pop();
			itemOne++;
		}
		else if (orderNumber == 2)		//Cheese Burger
		{
			Cheese.pop();
			Bun.pop();
			Patty.pop();
			Lettuce.pop();
			Tomato.pop();
			Onion.pop();
			itemTwo++;
		}
		
		//order number 3 = 2 Lettuce, so we pop one lettuce of the stack and tempItemk if there is another
		else if(orderNumber == 3)	//Vegan lettuce wrap burger
		{
			int tempLettuce = 0;
			tempLettuce = Lettuce.pop();		//pop lettuce and put in tempStackstack
			if(Lettuce.isEmpty())		
			{
				Lettuce.push(tempLettuce);	//put lettuce back on stack
				return false;
			}
			Lettuce.pop();
			Tomato.pop();
			Onion.pop();
			itemThree++;
		}
		else if(orderNumber == 4)	// Burger No onion
		{
			Bun.pop();
			Patty.pop();
			Lettuce.pop();
			Tomato.pop();
			itemFour++;
		}
		else if(orderNumber == 5)		//cheese burger
		{
			Cheese.pop();
			Bun.pop();
			Patty.pop();
			Lettuce.pop();
			Tomato.pop();
			itemFive++;
		}
		else if(orderNumber == 6)	//burger no tomato
		{
			Bun.pop();
			Patty.pop();
			Lettuce.pop();
			Onion.pop();
			itemSix++;
		}
		System.out.println("l-----------------------------------" + orderNumber);
		dictionary.add(customerNumber, orderNumber);
		customerNumber++;
		return true;
	}
	
		public Iterator<Integer> getCustomerIterator()
		{
			Iterator<Integer> customerIterator = dictionary.getKeyIterator();
			return customerIterator;
		}
		public Iterator<Integer> getOrderIterator()
		{
			Iterator<Integer> orderIterator = dictionary.getValueIterator();
			return orderIterator;
		}
		
		/**
		 * @param orderNumber
		 * @return	true if line is not full, false if full
		 */
		public boolean checkQueue(int orderNumber)
		{
			if(queue.isFull())		//queue if full
			{
				return false;
			}
			else			//queue is not full
			{
				queue.enqueue(orderNumber);	//add order number to queue
				return true;
			}
		}
		
		
		public int getItemOne()
		{
			return itemOne;
		}
		public int getItemTwo()
		{
			return itemTwo;
		}
		public int getItemThree()
		{
			return itemThree;
		}
		public int getItemFour()
		{
			return itemFour;
		}
		public int getItemFive()
		{
			return itemFive;
		}
		public int getItemSix()
		{
			return itemSix;
		}
		
		
		public int ExpiredItemBun()
		  {
		    
		    while(!Bun.isEmpty())
		    {
		      int tempItem = Bun.pop() - 1; 
		      if(tempItem > 0) // Item is not expired.
		      {
		        tempStack.push(tempItem);
		      }
		      else // Item is expired.
		      {
		        wasteBuns++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Bun.push(tempStack.pop());
		    }
		    return wasteBuns;
		  } 
		 
		  public int ExpiredItemPatty()
		  {
		    
		    while(!Patty.isEmpty())
		    {
		      int tempItem = Patty.pop() - 1;
		      if(tempItem > 0) // Item is not expired.
		      {
		        tempStack.push(tempItem);
		      }
		      else // Item is expired.
		      {
		        wastePatty++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Patty.push(tempStack.pop());
		    }
		    return wastePatty;
		  } 

		  public int ExpiredItemLettuce()
		  {
		    
		    while(!Lettuce.isEmpty())
		    {
		      int tempItem = Lettuce.pop() - 1; // Decrement remaining days left to use ingredient.
		      if(tempItem > 0) // Item is not expired.
		      {
		        tempStack.push(tempItem);
		      }
		      else 
		      {
		        wasteLettuce++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Lettuce.push(tempStack.pop());
		    }
		    return wasteLettuce;
		  } 

	
		  public int ExpiredItemTomato()
		  {
		    
		    while(!Tomato.isEmpty())
		    {
		      int tempItem = Tomato.pop() - 1;
		      if(tempItem > 0) // Item is not expired.
		      {
		        tempStack.push(tempItem);
		      }
		      else // Item is expired.
		      {
		        wasteTomato++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Tomato.push(tempStack.pop());
		    }
		    return wasteTomato;
		  } 
		
		  public int ExpiredItemOnion()
		  {
		    
		    while(!Onion.isEmpty())
		    {
		      int tempItem = Onion.pop() - 1; 
		      if(tempItem > 0) 
		      {
		        tempStack.push(tempItem);
		      }
		      else // Item is expired.
		      {
		    	  	wasteOnion++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Onion.push(tempStack.pop());
		    }
		    return wasteOnion;
		  } 

		 
		  public int ExpiredItemCheese()
		  {
		    
		    while(!Cheese.isEmpty())
		    {
		      int tempItem = Cheese.pop() - 1; 
		      if(tempItem > 0) 
		      {
		        tempStack.push(tempItem);
		      }
		      else 
		      {
		        wasteCheese++;
		      }
		    }
		    while(!tempStack.isEmpty()) // Reorder stack.
		    {
		      Cheese.push(tempStack.pop());
		    }
		    	return wasteCheese;
		  }

}


	

