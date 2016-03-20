package reference.comparator;
import java.util.*;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;
import reference.RatingRegister;


public class FilmComparator implements Comparator<Film> {
	Map<Film, List<Rating>> ratings;
	
	public FilmComparator(Map<Film, List<Rating>> ratings){
		this.ratings=ratings;
		
	}
	
	public static void main (String [] args){
		
		RatingRegister ratings = new RatingRegister();

	    Film exMachina = new Film("Ex Machina");
	    Film chappy = new Film("Chappy");
	    Film selfLess = new Film("Self/less");

	    Person tomas = new Person("Tomas");
	    Person adomas = new Person("Adomas");
	    Person paulius = new Person("Paulius");

	    ratings.addRating(tomas, exMachina, Rating.BAD);
	    ratings.addRating(tomas, chappy, Rating.GOOD);
	    ratings.addRating(tomas, selfLess, Rating.FINE);

	    ratings.addRating(adomas, exMachina, Rating.FINE);
	    ratings.addRating(adomas, chappy, Rating.BAD);
	    ratings.addRating(adomas, selfLess, Rating.MEDIOCRE);

	    ratings.addRating(paulius, selfLess, Rating.BAD);

	    Map<Film, List<Rating>> filmRatings = ratings.filmRatings();

	    List<Film> films = Arrays.asList(exMachina, chappy, selfLess);
	    System.out.println("The films before sorting: " + films);

	    Collections.sort(films, new FilmComparator(filmRatings));
	    System.out.println("The films after sorting: " + films);
	}
	
	public int averageRating(Film film){
		int sum= 0;
		List<Rating> filmRatings = this.ratings.get(film);
		int evaluationNumber= filmRatings.size();
		for (Rating r1 : filmRatings){
			sum+=r1.getValue();			
		}
		int average=sum/evaluationNumber;
		return average;
	}
	
	public int compare(Film f1, Film f2){
		return averageRating(f2) - averageRating(f1);
	}

}
