package reference;

import reference.domain.Film;
import reference.domain.Rating;
import reference.domain.Person;
import java.util.*;

public class RatingRegister {
	
	private Map<Film, List<Rating>> filmRatings;	
    private Map<Person,Map<Film, Rating>> personalFilmRatings;
	
	public RatingRegister(){
		filmRatings  = new HashMap<Film, List<Rating>>();
		personalFilmRatings=  new HashMap<Person, Map<Film, Rating>>();
	}
	
	public static void main (String [] args){
				
	}
	
	public void addRating(Film film, Rating rating){
		// adds a new rating to the parameter film. The same film can have various same ratings.
		
		if (!filmRatings.containsKey(film)){
			filmRatings.put(film, new ArrayList<Rating>());
			filmRatings.get(film).add(rating);
		} else {
			filmRatings.get(film).add(rating);
		}
	}
	
	public List<Rating> getRatings(Film film){
		 //returns a list of the ratings which were added in connection to a film.
		return filmRatings.get(film);
	}
	
	public Map<Film, List<Rating>> filmRatings(){
		//returns a map whose keys are the evaluated films
		return filmRatings;
	}
	
	//methods for personal evaluation of the films
    // *********************************************
	
	public void addRating(Person person, Film film, Rating rating){
		//Adds the rating of a specific film to the parameter person
		//The same person can recommend a specific film only once. 
		//The person rating has also to be added to the ratings connected to all the films.
	
		if (!personalFilmRatings.containsKey(person)){
			Map<Film, Rating> personFilms = new HashMap<Film, Rating>();
			personFilms.put(film, rating);
			personalFilmRatings.put(person,personFilms);
			//The person rating has also to be added to the ratings connected to all the films.
			addRating(film, rating);
	    } else {
	    	if (!personalFilmRatings.get(person).containsKey(film)){
	    		personalFilmRatings.get(person).put(film, rating);
	    		//The person rating has also to be added to the ratings connected to all the films.
	    		addRating(film, rating);
	    	} else {
	    		System.out.println("You have already evaluated the film");
	    	}
	    }
	}
	
	 public Rating getRating(Person person, Film film) {
		 //returns the rating the parameter person has assigned to the parameter film
		 //If the person hasn't evaluated such film, the method returns Rating.NOT_WATCHED.
	 
	     if (personalFilmRatings.get(person)!=null && !personalFilmRatings.get(person).containsKey(film)){
		 		return Rating.NOT_WATCHED;
	    } else if (personalFilmRatings.get(person)!=null) {
	        	return personalFilmRatings.get(person).get(film);
	    }
	     return Rating.NOT_WATCHED;
	 }
	 
	 
	 public Map<Film, Rating> getPersonalRatings(Person person){
		 //Returns a HashMap which contains the person's ratings. 
		 // The HashMap keys are the evaluated films, and their values are the ratings of these films.
		 return personalFilmRatings.get(person);
	 }
	 
	 public List<Person> reviewers() {
		 //returns a list of the people who have evaluated the films.
		 List<Person> reviewers = new ArrayList<Person>();
		 reviewers.addAll(personalFilmRatings.keySet());
		 return reviewers;
	 }
		
   

		
		
	
	
	
	
	


}
