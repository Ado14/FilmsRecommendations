package reference.comparator;
import reference.domain.Person;
import java.util.*;

public class PersonComparator implements Comparator<Person> {
	Map<Person, Integer> peopleIdentities;
	
	public PersonComparator(Map<Person, Integer> peopleIdentities){
		this.peopleIdentities=peopleIdentities;
	}
	
	public static void main(String []args){
		 Person matas = new Person("Matas");
		 Person tomas = new Person("Tomas");
		 Person paulius = new Person("Paulius");
		 Person adomas = new Person("Adomas");

		 Map<Person, Integer> peopleIdentities = new HashMap<Person, Integer>();
		 peopleIdentities.put(matas, 42);
		 peopleIdentities.put(tomas, 134);
		 peopleIdentities.put(paulius, 8);
		 peopleIdentities.put(adomas, 82);

		 List<Person> ppl = Arrays.asList(matas, tomas, paulius, adomas);
		 System.out.println("People before sorting: " + ppl);
		    
		 Collections.sort(ppl, new PersonComparator(peopleIdentities));
		 System.out.println("People after sorting: " + ppl);
	}
	
	public int compare(Person name1, Person name2){
		return this.peopleIdentities.get(name2)- this.peopleIdentities.get(name1);
	}

}
