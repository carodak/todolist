import java.util.ArrayList;

public class Categorie {
	private ArrayList<String> categorie = new ArrayList<String>();
	
	public Categorie() {
		super();
		this.categorie.add("travail");
		this.categorie.add("personnel");		
	}
	
	public ArrayList<String> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<String> categorie) {
		this.categorie = categorie;
	}
	
	public void ajoutCategorie(String d){
		if (!categorie.contains(d)){
			categorie.add(d);
			System.out.println("Ajout de la catégorie : "+d+"\n");
		}
		else {
			System.out.println("Vous avez déjà  une catégorie de ce nom \n");
		}
	}
	
	public void renommerCategorie(String cat_a_Renommer, String nouvelleCat){
		
		for (String a : categorie){
			if (a==cat_a_Renommer){
				a = nouvelleCat;
				System.out.println("La catégorie "+cat_a_Renommer+" a bien été renommée en "+nouvelleCat+"\n");
			}
		}
	}
	
	public void supprimerCategorie(String d){

		for (String a : categorie){
			if (a==d){
				categorie.remove(a);
				System.out.println("Suppression de la catégorie : "+d+"\n");
			}
		}
	}
	
	
	
}
