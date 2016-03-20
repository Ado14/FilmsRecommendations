package reference.domain;

public class Person {
	private final String name;
	
	
	public Person(String name){
		this.name=name;
		
	}
	
	public static void main (String [] args){
		Person p= new Person("Tomas");
		Person p2= new Person("Tadas");
		Person p3= new Person("Tomas");
		System.out.println(p.getName());
		System.out.println(p);
		System.out.println(p.equals(p2));// false
		System.out.println(p.equals(p3)); //true;
		
	}
		
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}
	
	public boolean equals(Object another){
		if(another==null){
			return false;
		}
		
		if (getClass()!=another.getClass()){
			return false;
		}
		
		Person anotherPerson = (Person) another;
		
		if (this.name==null|| !this.name.equals(anotherPerson.name)){
			return false;
		}		
		return true;
	}
	
	public int hashCode(){
		if (this.name==null){
			return 7;
		}
		return this.name.hashCode();
	}
}
