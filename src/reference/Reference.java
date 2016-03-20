package reference;
import reference.domain.Film;
import reference.domain.Rating;
import reference.domain.Person;
import java.util.*;
import reference.comparator.FilmComparator;
import reference.comparator.PersonComparator;

public class Reference {
	RatingRegister ratingRegister;
	
	public Reference(RatingRegister ratingRegister){
		this.ratingRegister=ratingRegister;
		
	}
	
	public static void main (String [] args){
		
        RatingRegister ratings = new RatingRegister();
		
		Film exMachina = new Film("Ex Machina");
	    Film chappy = new Film("Chappy");
	    Film selfLess = new Film("Self/less");
	    Film her = new Film ("Her");

	   	Person tomas = new Person("Tomas");
	    Person adomas = new Person("Adomas");
	    Person paulius = new Person("Paulius");
	    Person inga = new Person("Inga");
	    Person simona = new Person("Simona");

	    ratings.addRating(tomas, exMachina, Rating.BAD);
	    ratings.addRating(tomas, chappy, Rating.GOOD);
	    ratings.addRating(tomas, selfLess, Rating.FINE);

	    ratings.addRating(adomas, exMachina, Rating.FINE);
	    ratings.addRating(adomas, selfLess, Rating.BAD);
	    ratings.addRating(adomas, her, Rating.MEDIOCRE);

	    ratings.addRating(paulius, selfLess, Rating.BAD);

	    ratings.addRating(inga, her, Rating.GOOD);
	    ratings.addRating(inga, chappy, Rating.GOOD);

	    Reference ref = new Reference(ratings);
	    System.out.println(inga + " recommendation: " + ref.personalRecomendation(inga));
	    System.out.println(paulius + " recommendation: " + ref.personalRecomendation(paulius));
	    System.out.println(tomas + " recommendation: " + ref.personalRecomendation(tomas));
	    System.out.println(simona + " recommendation: " + ref.personalRecomendation(simona));
	}
	
	public Film recommendFilm(Person person){
		//returns a film to a person without looking to his personal taste
		
		List<Film> allFilms= getAllFilms();
		Map<Film, List<Rating>> filmsRatings = this.ratingRegister.filmRatings();
		FilmComparator filmComparator = new FilmComparator(filmsRatings);
		Collections.sort(allFilms,filmComparator);
		return allFilms.get(0);
	}
	
	public List<Film> getAllFilms(){
		Map<Film, List<Rating>> filmsRatings = this.ratingRegister.filmRatings();
		List<Film> allFilms= new ArrayList<Film>();
		allFilms.addAll(filmsRatings.keySet());
		return allFilms;
		
	}
	
	public Film personalRecomendation(Person person){
		//returns a film to a person based on his personal taste
		// The method has to work also if the person hasn't added any rating
		
		if (this.ratingRegister.getPersonalRatings(person)==null){
			return recommendFilm(person);
		}
		Map<Person, Integer> personalCompatabilityMap = personalCompatability(person);
		
		//Person's who will recommend the film
		Person personToRecommend =personToRecommend(personalCompatabilityMap, person); 
		 		
		Map<Film, Rating> otherPersonPreferences= this.ratingRegister.getPersonalRatings(personToRecommend);
		List<Film> otherBestFilms= bestFilms(personToRecommend,otherPersonPreferences);
		
		Map<Film, Rating> personalPreferences = this.ratingRegister.getPersonalRatings(person);
		
		for (Film f1: otherBestFilms){
			if (!personalPreferences.containsKey(f1) && otherPersonPreferences.get(f1).getValue()>2){
				return f1;
			} 
		}
		
		return recommendFilm(person);
		
	}
	public Person personToRecommend(Map<Person, Integer> personalCompatabilityMap, Person person){
		//returns a person who will recommend a film
		
		List<Person> allOtherPersons = this.ratingRegister.reviewers();
		allOtherPersons.remove(person);
		Collections.sort(allOtherPersons,  new PersonComparator(personalCompatabilityMap));
		Person personToRecommend= allOtherPersons.get(0);
		return personToRecommend;
		
	}
	
	public List<Film> bestFilms(Person person, Map<Film, Rating> personalPreferences){
		// return a person's best list movies
		
		List<Film> bestFilms = new ArrayList<Film>();
		for (Film f1: personalPreferences.keySet()){
			//We add good films at the beginning of the ArrayList
			if(personalPreferences.get(f1).getValue()>4){
				bestFilms.add(f1);
			}
		}
		for (Film f1: personalPreferences.keySet()){
			//We add fine films later
			if(personalPreferences.get(f1).getValue()>2){
				bestFilms.add(f1);
			}
		}
		
		return bestFilms;
	}
	
	
	public Map<Person, Integer> personalCompatability(Person person){
		// Returns a Map how a person's taste is compatible with other person's taste
		// The higher the Integer value, the more similar taste of movies is
		
		List<Person> otherPersons = this.ratingRegister.reviewers();
		otherPersons.remove(person);
		Map<Person, Integer> compatabilityMap= new HashMap<Person, Integer>();
		
		for (Person other: otherPersons){
			Map<Film, Rating> personalPreferences = this.ratingRegister.getPersonalRatings(person);
			Map<Film, Rating> otherPersonPreferences= this.ratingRegister.getPersonalRatings(other);
			int compatabilityScore=0;
			
			for (Film f1:  personalPreferences.keySet()){
				
				if (otherPersonPreferences!=null && otherPersonPreferences.containsKey(f1)){
					Rating personRating = this.ratingRegister.getRating(person, f1);
					Rating otherPersonRating = this.ratingRegister.getRating(other, f1);
					compatabilityScore+= personRating.getValue() * otherPersonRating.getValue();
				}
			}
			compatabilityMap.put(other, compatabilityScore);
		}
		return compatabilityMap;
		
	}
}
