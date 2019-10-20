package parking;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class ParkingLot
{
    public int    Total_Profit; 
    public int    Cost_Per_HR = 10;
    private boolean full;
    private boolean empty;
    public int capacity = 100;
    Car[] space = new Car[capacity];
    
    public ParkingLot()
    {
    	Total_Profit = 0;
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
    
    public boolean is_empty()
    {
    	for(int i=0; i < capacity; i++)
    	{
    		if(space[i] == null)
    		{
    			empty = true;
    		}
    		else
    		{
    			full = false;
    			break;
    		}
    	}
    	return empty;
    }
    
 };
 
 class Car extends ParkingLot
 {
	 private boolean is_Parked; 
	 private double time_In;
	 private double time_out;
	 public int time_parked;
	 
	public Car(int wait_time)
	 {
		time_parked = wait_time ;
	 }
	
	
  };
  
  

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int time = 0;
		// create instance of Random class 
        Random rand = new Random(); 
	
		System.out.println("Creating Parking Lot...");
		ParkingLot LotA = new ParkingLot();
	    Queue<Car> entrance = new LinkedList<>();
	    Queue<Car> exit = new LinkedList<>();
	    
	  
	    
	      Scanner sc = new Scanner(new File("test1.txt"));
	      int num = sc.nextInt();
	      int num_of_Cars = num;
	    
	 
	    
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
	    
	    System.out.println("The parking lot will start recording profit at hour 0");
	    
	   //bubble sort car array so i can have the timeParked at the beginning and 
	    //cars that will be parked longer at the end
	    int n;
	    if(num_of_Cars < LotA.capacity)
	    {
	    	 n = num_of_Cars;
	    }
	    else
	    {
	    	n = LotA.space.length;
	    }
	  
	        for (int i = 0; i < n-1; i++) 
	            for (int j = 0; j < n-i-1; j++) 
	                if (LotA.space[j].time_parked > LotA.space[j+1].time_parked) 
	                { 
	                    // swap LotA.space[j+1] and LotA.space[i] 
	                    Car temp = LotA.space[j]; 
	                    LotA.space[j] = LotA.space[j+1]; 
	                    LotA.space[j+1] = temp; 
	                } 
	    
	        //simulates a car coming into the lot from the entrance queue
	        //once the car is done being parked it will go to the exit queue
	        while(!LotA.is_empty())
	        {
	        	if(entrance.peek() != null)
	        	{
	        		if(LotA.space[0] != null)
	        		{
	        			time = time + (LotA.space[0].time_parked - time);
	        			exit.add(LotA.space[0]);
	        			
	        			LotA.space[0] = entrance.peek();
	        			entrance.remove();
	        			
	        			
	        			//bubble sort car array so i can have the timeParked at the beginning and 
	        		    //cars that will be parked longer at the end
	        			if(num_of_Cars < LotA.capacity)
	        		    {
	        		    	 n = num_of_Cars;
	        		    }
	        		    else
	        		    {
	        		    	n = LotA.space.length;
	        		    }
	        		  
	        		        for (int i = 0; i < n-1; i++) 
	        		            for (int j = 0; j < n-i-1; j++) 
	        		                if (LotA.space[j].time_parked > LotA.space[j+1].time_parked) 
	        		                { 
	        		                    // swap LotA.space[j+1] and LotA.space[i] 
	        		                    Car temp = LotA.space[j]; 
	        		                    LotA.space[j] = LotA.space[j+1]; 
	        		                    LotA.space[j+1] = temp; 
	        		                } 	
	        			
	        			
	        		}
	        	}
	        	else
	        	{
	        		if(num_of_Cars < LotA.capacity)
	        		{
	        			n = num_of_Cars;
	        		}
	        		else
	        		{
	        			n = LotA.space.length;
	        		}
	        		for(int i =0; i < n; i++)
	        		{
	        			time = time + (LotA.space[i].time_parked - time);
	        			exit.add(LotA.space[i]);
	        			LotA.space[i] = null;
	        		}
	        	}
	        	
	        }
	        
	        //eventually the parking lot will be empty and you can process the exit queue
	        //will add the amount of hr each car stayed to calculate the profit
		while(exit.peek() != null)
		{
			LotA.Total_Profit = LotA.Total_Profit + (exit.peek().time_parked * LotA.Cost_Per_HR);
			exit.remove();
		}
		
		System.out.println("It took " + time + " hours to make " + LotA.Total_Profit + " dollars at "+
							 LotA.Cost_Per_HR + " per hour with a traffic of "+  num_of_Cars + " cars");
		
		System.out.println("end of program..");

	}

}
