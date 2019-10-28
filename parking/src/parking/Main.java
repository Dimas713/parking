package parking;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class group
{
	public int Cost_Per_HR[] = {5 ,7 ,10 } ;
	
	public int Num_of_groups()
	{
		return Cost_Per_HR.length ;
	}
	
	
	public int Price_Inquiry(int Lotnum)
	{
		if(Lotnum < Cost_Per_HR.length && Lotnum > 0)
		{
			--Lotnum;
			return Cost_Per_HR[Lotnum];
		}
		else
		{
			return -1;
		}
		
		
	}
};

class ParkingLot extends group
{
    public int    Total_Profit; 
    private boolean full;
    private boolean empty;
    private int capacity = 100;
    public int cost ;
    Car[] space = new Car[capacity];
    Queue<Car> entrance = new LinkedList<>();
    Queue<Car> exit = new LinkedList<>();
    
    public ParkingLot(int groupnum)
    {
    	cost = Cost_Per_HR[groupnum-1];
    	Total_Profit = 0;
    	for(int i=0; i < capacity; i++)
    	{
    		space[i] = null;
    	}
    }
    
    public void carEnter(int count)
    {
    	Random rand = new Random(); 
    	int num_of_Cars = count ;
    	
    	//Adds car objects to the entrance  queue
	    for (int i=0; i< num_of_Cars; i++) 
	    {
	    	
	    	entrance.add(new Car(rand.nextInt(25)));
	    		
	    }
    }
    
    public int parkingSize()
    {
    	int size = capacity;
    	return size;
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
    			empty = false;
    			break;
    		}
    	}
    	return empty;
    }
    
 };
 
 class Car
 {
	 private boolean is_Parked; 
	 private double time_In;
	 private double time_out;
	 private int time_parked;
	 
	public Car(int wait_time)
	 {
		time_parked = wait_time ;
	 }
	
	public int timeParked()
	{
		return time_parked; 
	}
	
  };
  
  

public class Main {

	public static void main(String[] args) throws FileNotFoundException 
	{
		int time = 0;
	 
		System.out.println("Creating Parking Lots..." );
		ParkingLot LotA = new ParkingLot(1);
		ParkingLot LotB = new ParkingLot(2);
		ParkingLot LotC = new ParkingLot(3);
	    
	  
	      Scanner sc = new Scanner(new File("test1.txt"));
	      int num = sc.nextInt();
	      int num_of_Cars = num;
	    /*
	    Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter number of cars");
	    int num_of_Cars = myObj.nextInt();;
	 */
	    int lotnum = 1;
	    
	    System.out.println("There are " + num_of_Cars + " cars in the entrence queue for lot " );
	    while(num_of_Cars > 0)
	    {
	    // The cars enter the entrance queue based on lowest price
	    if((LotA.cost < LotB.cost && LotA.cost < LotC.cost))
	    {
	    	if(num_of_Cars < LotA.parkingSize())
	    	{
	    		LotA.carEnter(num_of_Cars) ;
	    		num_of_Cars = 0;
	    	}
	    	else
	    	{
	    		LotA.carEnter(LotA.parkingSize());
	    		num_of_Cars = num_of_Cars - LotA.parkingSize() ;
	    		if(LotB.cost < LotC.cost)
	    		{
	    			if(num_of_Cars < LotB.parkingSize())
	    	    	{
	    	    		LotB.carEnter(num_of_Cars) ;
	    	    		num_of_Cars = 0;
	    	    	}
	    			else
	    			{
	    				LotB.carEnter(LotB.parkingSize());
	    	    		num_of_Cars = num_of_Cars - LotB.parkingSize() ;
	    			}
	    		}
	    		else
	    		{
	    			if(num_of_Cars < LotC.parkingSize())
	    	    	{
	    	    		LotC.carEnter(num_of_Cars) ;
	    	    		num_of_Cars = 0;
	    	    	}
	    			else
	    			{
	    				LotC.carEnter(LotC.parkingSize());
	    	    		num_of_Cars = num_of_Cars - LotC.parkingSize() ;
	    			}
	    		}
	    		
	    	}
	    }
	    else if(LotB.cost < LotA.cost && LotB.cost < LotA.cost)
	    {
	    	if(num_of_Cars < LotB.parkingSize())
	    	{
	    		LotB.carEnter(num_of_Cars) ;
	    		num_of_Cars = 0;
	    	}
	    	else
	    	{
	    		LotB.carEnter(LotB.parkingSize());
	    		num_of_Cars = num_of_Cars - LotB.parkingSize() ;
	    	}
	    }
	    else if(LotC.cost < LotA.cost && LotC.cost < LotB.cost)
	    {
	    	if(num_of_Cars < LotC.parkingSize())
	    	{
	    		LotC.carEnter(num_of_Cars) ;
	    		num_of_Cars = 0;
	    	}
	    	else
	    	{
	    		LotC.carEnter(LotC.parkingSize());
	    		num_of_Cars = num_of_Cars - LotC.parkingSize() ;
	    }
	    }
	    }
	    	
	   
	    //lets the first batch of cars into the parking lot A if entrance queue is empty or there is
	    //no more space no more cars will go in 
	    int num_of_Carsin_A = LotA.entrance.size();
	    int counter= 0;
	    while(LotA.is_full() == false)
	    {
	    	if(LotA.space[counter] == null && counter < LotA.parkingSize() && LotA.entrance.peek() != null)
	    	{
	    		LotA.space[counter] = LotA.entrance.peek();
	    		LotA.entrance.remove();
	    		counter++;
	    	}
	    	else
	    	{
	    		counter++;
	    	}
	    	
	    	if(LotA.entrance.peek() == null)
	    	{
	    		break;
	    	}
	    	
	    }
	    
	    
	   //bubble sort car array so i can have the timeParked at the beginning and 
	    //cars that will be parked longer at the end
	    int n;
	    if(num_of_Carsin_A < LotA.parkingSize())
	    {
	    	 n = num_of_Carsin_A;
	    }
	    else
	    {
	    	n = LotA.space.length;
	    }
	  
	        for (int i = 0; i < n-1; i++) 
	            for (int j = 0; j < n-i-1; j++) 
	                if (LotA.space[j].timeParked() > LotA.space[j+1].timeParked()) 
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
	        	
	        	if(LotA.entrance.peek() != null)
	        	{
	        		if(LotA.space[0] != null)
	        		{
	        			time = time + (LotA.space[0].timeParked() - time);
	        			LotA.exit.add(LotA.space[0]);
	        			
	        			LotA.space[0] = LotA.entrance.peek();
	        			LotA.entrance.remove();
	        			
	        			
	        			//bubble sort car array so i can have the timeParked at the beginning and 
	        		    //cars that will be parked longer at the end
	        			if(num_of_Cars < LotA.parkingSize())
	        		    {
	        		    	 n = num_of_Cars;
	        		    }
	        		    else
	        		    {
	        		    	n = LotA.space.length;
	        		    }
	        		  
	        		        for (int i = 0; i < n-1; i++) 
	        		            for (int j = 0; j < n-i-1; j++) 
	        		                if (LotA.space[j].timeParked() > LotA.space[j+1].timeParked()) 
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
	        		
	        		if(num_of_Carsin_A < LotA.parkingSize())
	        		{
	        			n = num_of_Carsin_A;
	        		}
	        		else
	        		{
	        			n = LotA.space.length;
	        		}
	        		for(int i =0; i < n; i++)
	        		{
	        			time = time + (LotA.space[i].timeParked() - time);
	        			LotA.exit.add(LotA.space[i]);
	        			LotA.space[i] = null;
	        		}
	        	}
	        	
	        }
	        
	        //eventually the parking lot will be empty and you can process the exit queue
	        //will add the amount of hr each car stayed to calculate the profit
		while(LotA.exit.peek() != null)
		{
			LotA.Total_Profit = LotA.Total_Profit + (LotA.exit.peek().timeParked() * LotA.cost);
			LotA.exit.remove();
		}
		
		System.out.println("It took " + time + " hours to make " + LotA.Total_Profit + " dollars at "+
							 LotA.cost + " dollards per hour for lot # " + lotnum) ;
		lotnum++;
		//-----------------------------------------------------------------------------------------
		// start of lot B
		
		//lets the first batch of cars into the parking lot A if entrance queue is empty or there is
	    //no more space no more cars will go in 
	    int num_of_Carsin_B = LotB.entrance.size();
	     counter= 0;
	    
	    while(LotB.is_full() == false)
	    {
	    	if(LotB.space[counter] == null && counter < LotB.parkingSize() && LotB.entrance.peek() != null)
	    	{
	    		LotB.space[counter] = LotB.entrance.peek();
	    		LotB.entrance.remove();
	    		counter++;
	    	}
	    	else
	    	{
	    		counter++;
	    	}
	    	
	    	if(LotB.entrance.peek() == null)
	    	{
	    		break;
	    	}
	    	
	    }
	    
	    
	   //bubble sort car array so i can have the timeParked at the beginning and 
	    //cars that will be parked longer at the end
	    
	    if(num_of_Carsin_B < LotB.parkingSize())
	    {
	    	 n = num_of_Carsin_B;
	    }
	    else
	    {
	    	n = LotB.space.length;
	    }
	  
	        for (int i = 0; i < n-1; i++) 
	            for (int j = 0; j < n-i-1; j++) 
	                if (LotB.space[j].timeParked() > LotB.space[j+1].timeParked()) 
	                { 
	                    // swap LotA.space[j+1] and LotA.space[i] 
	                    Car temp = LotB.space[j]; 
	                    LotB.space[j] = LotB.space[j+1]; 
	                    LotB.space[j+1] = temp; 
	                } 
	        
	        //simulates a car coming into the lot from the entrance queue
	        //once the car is done being parked it will go to the exit queu
	        
	        while(!LotB.is_empty())
	        {
	        	
	        	if(LotB.entrance.peek() != null)
	        	{
	        		if(LotB.space[0] != null)
	        		{
	        			time = time + (LotB.space[0].timeParked() - time);
	        			LotB.exit.add(LotB.space[0]);
	        			
	        			LotB.space[0] = LotB.entrance.peek();
	        			LotB.entrance.remove();
	        			
	        			
	        			//bubble sort car array so i can have the timeParked at the beginning and 
	        		    //cars that will be parked longer at the end
	        			if(num_of_Cars < LotB.parkingSize())
	        		    {
	        		    	 n = num_of_Cars;
	        		    }
	        		    else
	        		    {
	        		    	n = LotB.space.length;
	        		    }
	        		  
	        		        for (int i = 0; i < n-1; i++) 
	        		            for (int j = 0; j < n-i-1; j++) 
	        		                if (LotB.space[j].timeParked() > LotB.space[j+1].timeParked()) 
	        		                { 
	        		                    // swap LotA.space[j+1] and LotA.space[i] 
	        		                    Car temp = LotB.space[j]; 
	        		                    LotB.space[j] = LotB.space[j+1]; 
	        		                    LotB.space[j+1] = temp; 
	        		                } 	
	        			
	        			
	        		}
	        	}
	        	else
	        	{
	        		
	        		if(num_of_Carsin_B < LotB.parkingSize())
	        		{
	        			n = num_of_Carsin_B;
	        		}
	        		else
	        		{
	        			n = LotB.space.length;
	        		}
	        		for(int i =0; i < n; i++)
	        		{
	        			time = time + (LotB.space[i].timeParked() - time);
	        			LotB.exit.add(LotB.space[i]);
	        			LotB.space[i] = null;
	        		}
	        	}
	        	
	        }
	        
	        //eventually the parking lot will be empty and you can process the exit queue
	        //will add the amount of hr each car stayed to calculate the profit
		while(LotB.exit.peek() != null)
		{
			LotB.Total_Profit = LotB.Total_Profit + (LotB.exit.peek().timeParked() * LotB.cost);
			LotB.exit.remove();
		}
		
		System.out.println("It took " + time + " hours to make " + LotB.Total_Profit + " dollars at "+
							 LotB.cost + " dollards per hour for lot # " + lotnum) ;
		lotnum++;
		
		//-----------------------------------------------------------------------------------------
				// start of lot c
				
				//lets the first batch of cars into the parking lot A if entrance queue is empty or there is
			    //no more space no more cars will go in 
			    int num_of_Carsin_C = LotC.entrance.size();
			     counter= 0;
			    
			    while(LotC.is_full() == false)
			    {
			    	if(LotC.space[counter] == null && counter < LotC.parkingSize() && LotC.entrance.peek() != null)
			    	{
			    		LotC.space[counter] = LotC.entrance.peek();
			    		LotC.entrance.remove();
			    		counter++;
			    	}
			    	else
			    	{
			    		counter++;
			    	}
			    	
			    	if(LotC.entrance.peek() == null)
			    	{
			    		break;
			    	}
			    	
			    }
			    
			    
			   //bubble sort car array so i can have the timeParked at the beginning and 
			    //cars that will be parked longer at the end
			    
			    if(num_of_Carsin_C < LotC.parkingSize())
			    {
			    	 n = num_of_Carsin_C;
			    }
			    else
			    {
			    	n = LotC.space.length;
			    }
			  
			        for (int i = 0; i < n-1; i++) 
			            for (int j = 0; j < n-i-1; j++) 
			                if (LotC.space[j].timeParked() > LotC.space[j+1].timeParked()) 
			                { 
			                    // swap LotC.space[j+1] and LotC.space[i] 
			                    Car temp = LotC.space[j]; 
			                    LotC.space[j] = LotC.space[j+1]; 
			                    LotC.space[j+1] = temp; 
			                } 
			        
			        //simulates a car coming into the lot from the entrance queue
			        //once the car is done being parked it will go to the exit queue
			        
			        while(!LotC.is_empty())
			        {
			        	
			        	if(LotC.entrance.peek() != null)
			        	{
			        		if(LotC.space[0] != null)
			        		{
			        			time = time + (LotC.space[0].timeParked() - time);
			        			LotC.exit.add(LotC.space[0]);
			        			
			        			LotC.space[0] = LotC.entrance.peek();
			        			LotC.entrance.remove();
			        			
			        			
			        			//bubble sort car array so i can have the timeParked at the beginning and 
			        		    //cars that will be parked longer at the end
			        			if(num_of_Cars < LotC.parkingSize())
			        		    {
			        		    	 n = num_of_Cars;
			        		    }
			        		    else
			        		    {
			        		    	n = LotC.space.length;
			        		    }
			        		  
			        		        for (int i = 0; i < n-1; i++) 
			        		            for (int j = 0; j < n-i-1; j++) 
			        		                if (LotC.space[j].timeParked() > LotC.space[j+1].timeParked()) 
			        		                { 
			        		                    // swap LotA.space[j+1] and LotA.space[i] 
			        		                    Car temp = LotC.space[j]; 
			        		                    LotC.space[j] = LotC.space[j+1]; 
			        		                    LotC.space[j+1] = temp; 
			        		                } 	
			        			
			        			
			        		}
			        	}
			        	else
			        	{
			        		
			        		if(num_of_Carsin_C < LotC.parkingSize())
			        		{
			        			n = num_of_Carsin_C;
			        		}
			        		else
			        		{
			        			n = LotC.space.length;
			        		}
			        		for(int i =0; i < n; i++)
			        		{
			        			time = time + (LotC.space[i].timeParked() - time);
			        			LotC.exit.add(LotC.space[i]);
			        			LotC.space[i] = null;
			        		}
			        	}
			        	
			        }
			        
			        //eventually the parking lot will be empty and you can process the exit queue
			        //will add the amount of hr each car stayed to calculate the profit
				while(LotC.exit.peek() != null)
				{
					LotC.Total_Profit = LotC.Total_Profit + (LotC.exit.peek().timeParked() * LotC.cost);
					LotC.exit.remove();
				}
				
				System.out.println("It took " + time + " hours to make " + LotC.Total_Profit + " dollars at "+
									 LotC.cost + " dollards per hour for lot # " + lotnum) ;
				lotnum++;
		
		System.out.println("end of program..");

	}


	}
	