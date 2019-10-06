package lab3pt4_linked_list;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ListTester {
	
	public static void part4()
	{
		//load data
		
		LinkedList ll = new LinkedList();
		
		Actor a1 = new Actor("Jeb");
		Actor a2 = new Actor("Bob");
		Actor a3 = new Actor("Joe");
		Actor a4 = new Actor("Phil");
		Actor a5 = new Actor("Karen");
		
		ll.add(a1);
		ll.add(a2);
		ll.add(a3);
		ll.add(a4);
		ll.add(a5);
		
		
		//loop
		for(int i = 0; i < ll.size(); i++)
		{
			Actor current = (Actor) ll.get(i);
			System.out.println(current);
		}
		
		
		//head
		System.out.print("Head: ");
		System.out.println(ll.get(0));
		
		
	}
	public static void part5() throws IOException
	{
		File inFile = new File("actors.txt");
		Scanner sc = new Scanner(inFile);
		
		//load from actors.txt ---------------------------;
		LinkedList ll = new LinkedList();
		
		while(sc.hasNextLine())
		{
			String s = new String(sc.nextLine());
			Actor currentActor = new Actor(s);
			ll.add(currentActor);
		}
		sc.close();
		
		//print using loop --------------------------------
		
		for(int i = 0; i < ll.size(); i++)
			System.out.println((Actor) ll.get(i));
		
		System.out.print("Head: ");
		System.out.println(ll.get(0));
	}
	public static void part6() 
	{
		LinkedList movies = new LinkedList();
		 
		Object[] actorArr = new Object[10];
		String[] movieNames = {"Q","W","E","R","T"};
		String[] dirNames = {"Q1","W1","E1","R1","T1","Y1","U1","I1","O1","P1"};
		int[] dates = {1,2,3,4,5,6,7,8,9,0};
		
		for(int i = 0; i < 10; i++)
		{
			Actor a = new Actor("Joe");
			actorArr[i] = a;
		}
		System.out.println("made it");
		//construct the movies
		
		for(int i = 0; i < 5; i++)
		{
			String cTitle = movieNames[i]; int cDate = dates[i];
			
			LinkedList actors = new LinkedList(), directors = new LinkedList();
			
			for(Object a : actorArr)
				actors.add(a);
			
			for(String d : dirNames)
				directors.add(d);
			
			Movie cMovies = new Movie(cDate, cTitle, actors, directors);
			
			movies.add(cMovies);
		}
		
		for(int i = 0; i < movies.size(); i++)
		{
			Movie casted = (Movie) movies.get(i);
			System.out.println(casted.toString());
		}
		
		
	}
	public static void part7() throws IOException
	{
		File inFile = new File("movies.txt");
		Scanner sc = new Scanner(inFile);
		
		//each line is new movie
		while(sc.hasNextLine())
		{
			//storage;
			int date;
			String movieName = new String("");
			LinkedList actors = new LinkedList();
			LinkedList directors = new LinkedList();
			
			//start unpacking
			String line = sc.nextLine();
			
			//unpack by spaces
			String[] splitSpace = line.split(" ");
			
			//date
			date = Integer.parseInt(splitSpace[0]);
			//movie name
			int i, cnt;
			for(i = 1, cnt = 0;  cnt < 1 && i < splitSpace.length;i++)
			{
				if(splitSpace[i].equals(""))
					cnt ++;
				else
					cnt = 0;
				
				movieName += splitSpace[i] + " ";
			}
			//System.out.println(movieName);
			//actors and directors
			String s = new String();
			for(; i < splitSpace.length; i++)
			{

				if(splitSpace[i].equals("Dir:")) s += ",DIR,";
				if(!splitSpace[i].equals("") && !splitSpace[i].equals("Dir:")) s += splitSpace[i] + " ";
			}
			
			String splitComma[] = s.split(",");
			
			//System.out.println(Arrays.toString(splitComma));
			
			boolean flag = false;
			for(i = 0; i < splitComma.length; i++)
			{
				if(splitComma[i].equals("DIR")) 
				{
					flag = true;
					continue;
				}
				if(!flag)
				{
					Actor a = new Actor(splitComma[i]);
					actors.add(a);
				}
				else if(flag)
				{
					directors.add(splitComma[i]);
				}
			}
			
			Movie m = new Movie(date, movieName, actors, directors);
			System.out.println(m);
		}
		
		sc.close();

	}
	public static void main(String[] args)  throws IOException{
		//part4();
		//part5();
		//part6();
		part7();


	}

}
