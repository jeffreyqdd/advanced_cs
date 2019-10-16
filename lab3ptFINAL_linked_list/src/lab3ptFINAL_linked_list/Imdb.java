package lab3ptFINAL_linked_list;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
//import java.util.Arrays;

public class Imdb {

	Imdb(){}
	
	public Actor[] unpackActor() throws IOException
	{
		File inFile = new File("actors.txt");
		Scanner sc = new Scanner(inFile);
		
		String all = "";
		while(sc.hasNextLine()) all += sc.nextLine() + ",";
		
		String[] nameArr = all.split(",");
		Actor[] actorArr = new Actor[nameArr.length];
		
		for(int i  = 0; i < nameArr.length; i++)
		{
			Actor a = new Actor(nameArr[i]);
			actorArr[i] = a;
		}
		
		sc.close();
		
		return actorArr;
	}
	public ArrayList<Movie> unpackMovie() throws IOException
	{
		File inFile = new File("movies.txt");
		Scanner sc = new Scanner(inFile);
		ArrayList<Movie> movieArr = new ArrayList<Movie>();
		
		while(sc.hasNextLine())
		{
			int date;
			String movieTitle = new String();
			LinkedList actors = new LinkedList();
			LinkedList directors = new LinkedList();
			
			
			String line = sc.nextLine();
			String[] splitSpace = line.split(" ");
			
			
			//System.out.println(Arrays.toString(splitSpace));
			
			String time = new String(); 
			String title = new String();
			//unpack date, title, i stops at first name of first actor
			int i, section;
			for( i = 0, section = 0; i < splitSpace.length; i++) 
			{
				if(section == 0)
				{
					time = splitSpace[i];
					section = 1;
					continue;
				}
				if(!splitSpace[i].equals("") && section == 1) title += splitSpace[i];
				if(!splitSpace[i+1].equals("") && section == 1) title += " ";
				if (splitSpace[i+1].equals("") && section == 1) section = 2;
				else if(section == 2 && !splitSpace[i].equals("")) break;
						
			}
			date = Integer.parseInt(time); //set
			movieTitle = title;
			
			//System.out.println(splitSpace[i]);
			
			//read actors
			String movieActors = new String();
			String movieDirectors = new String();
			for(section = 0; i < splitSpace.length; i++)
			{
				if(!splitSpace[i].equals("") && section == 0)
				{
					movieActors += splitSpace[i];
					
					if(!splitSpace[i+1].equals("") && !splitSpace[i].contains(","))
						movieActors += " ";
				}

				if(!splitSpace[i].equals("") && section == 2)
				{
					movieDirectors += splitSpace[i];
					
					if(i < splitSpace.length - 1 && !splitSpace[i+1].equals("") && !splitSpace[i].contains(","))
						movieDirectors += " ";
				}
				
				if(splitSpace[i].equals("")) section = 1;
			    if(splitSpace[i].equals("Dir:"))section = 2;
			}
			
			String[] splitActors = movieActors.split(",");
			String[] splitDirector = movieDirectors.split(",");
			
			for(String s : splitActors)
			{
				Actor a = new Actor(s);
				actors.add(a);
			}
			for(String s : splitDirector)
				directors.add(s);

			
			Movie m = new Movie(date, movieTitle, actors, directors);
			
			movieArr.add(m);
		}
		
		sc.close();
		return movieArr;
	}
	public String solve(Actor a, ArrayList<Movie> m)
	{
		Actor currentActor = a;
		Movie currentMovie;
		String conc = currentActor + ":\n";
		//iterate for every movie. Movies are in earliest to latest, iterate backwards;
		
		for(int i = m.size() - 1; i >= 0; i--)
		{
			currentMovie = m.get(i);
			
			LinkedList actorList = currentMovie.getActors();
			Node actorPtr = actorList.getHead();
			
			//System.out.println("NEWMOVIE::----" + currentMovie.getTitle() + " "+
				//Integer.toString(currentMovie.getDate()) + "====" +currentActor.getName());
			
			int j = 0;
			while(j < actorList.size())
			{
				Actor curr = (Actor) actorPtr.get();
				//System.out.print(curr.getName() + " ");
				
				String foo = curr.getName();
				
				
				if(foo.equals(currentActor.getName()))
					conc += Integer.toString(currentMovie.getDate()) + " "
									+ currentMovie.getTitle() + "\n";
				
				actorPtr= actorPtr.getNextPtr(); j++;
			}
		}
		return conc;
	}
	public static void main(String args[]) throws IOException
	{
		Imdb currentObj = new Imdb();
		
		Actor[] test = currentObj.unpackActor();
		
		ArrayList<Movie> movies = currentObj.unpackMovie();
		currentObj.solve(test[0], movies);
		for(Actor a : test)
		{
			System.out.println(currentObj.solve(a, movies));
			//currentObj.solve(a, movies);
		}
		//System.out.println(currentObj.solve(test[0], movies));
		
		
	}
}
