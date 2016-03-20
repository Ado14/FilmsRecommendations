package reference.domain;

public class Film {
	private final String name;
	
	
	public Film(String name){
		this.name=name;
		
	}
	
	public static void main (String [] args){
		Film f1= new Film("Kill Bill:Volume I");
		Film f2 = new Film("Django Unchained");
		Film f3 = new Film ("Django Unchained");
		System.out.println(f1.getName());
		System.out.println(f1);
		System.out.println(f1.equals(f2)); // false
		System.out.println(f2.equals(f3)); // true
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
		
		Film anotherFilm = (Film) another;
		
		if (this.name==null|| !this.name.equals(anotherFilm.name)){
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
