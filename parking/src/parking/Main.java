package parking;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Random;

class ParkingLot
{
    private int    Total_Profit; 
    private int    Cost_Per_HR;
    private boolean full;
    public int capacity = 100;
    Car[] space = new Car[capacity];
    
    public ParkingLot()
    {
    	for(int i=0; i < capacity; i++)
    	{
    		space[i] = null;
    	}
    }
    
    
    public boolean is_full()
    {
    	for(int i=0; i < capacity; i++)
    	{
    		if(space[i] == null)
    		{
    			full = false;
    			break;
    		}
    		else
    		{
    			full = true;
    		}
    	}
    	return full;
    }
    
 };
 
 class Car extends ParkingLot
 {
	 private boolean is_Parked; 
	 private double time_In;
	 private double time_out;
	 private double time_parked;
	 
	public Car(double wait_time)
	 {
		time_parked = wait_time ;
	 }
	
	
  };

public class Main {

	public static void main(String[] args) {
		// create instance of Random class 
        Random rand = new Random(); 
	
		System.out.println("Creating Parking Lot...");
		ParkingLot LotA = new ParkingLot();
	    Queue<Car> entrance = new LinkedList<>();
	    
	    int num_of_Cars = 100;
	 
	    
	    //Adds car objects to the entrance  queue
	    for (int i=0; i< num_of_Cars; i++) 
	    {
	    	
	    	entrance.add(new Car(rand.nextInt(25)));
	    		
	    }
	    System.out.println("There are " + num_of_Cars + " cars in the entrence queue.");
	    
	    //lets the first batch of cars into the parking lot if entrance queue is empty or there is
	    //no more space no more cars will go in 
	    int counter= 0;
	    while(LotA.is_full() == false)
	    {
	    	if(LotA.space[counter] == null && counter < LotA.capacity && entrance.peek() != null)
	    	{
	    		LotA.space[counter] = entrance.peek();
	    		entrance.remove();
	    		counter++;
	    	}
	    	else
	    	{
	    		counter++;
	    	}
	    	
	    	if(entrance.peek() == null)
	    	{
	    		break;
	    	}
	    	
	    }
		
		
		System.out.println("end of program..");

	}

}
