
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;






public class TodoList extends JFrame implements Serializable {
	
	
	private ArrayList<Tache> liste= new ArrayList<Tache>();
	
	
	
	public TodoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public ArrayList<Tache> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Tache> liste) {
		this.liste = liste;
	}
	
	//Trie la liste par date d'échéance croissante
	
	public void listeSimple(){
		Collections.sort(liste, new Comparator<Tache>() {
		    @Override
		    public int compare(Tache o1, Tache o2) {
		    	int diff1 = o1.getDiff2dates(o1.getEcheance(), o2.getEcheance());
			      int diff2 = o1.getDiff2dates(o2.getEcheance(), o1.getEcheance());
			      
			      if(diff1>diff2){
			    	  return 1;
			      }
			      
			      else if (diff1==diff2){
			    	  return 0;
			      }
			      
			      else
			    	  return -1;
		    }
		});
	}
	
	/*
	 * Retourne la copie de la liste actuelle sans les taches réalisées (ne modifie pas la liste actuelle afin de ne pas perdre d'informations)
	 */
	public ArrayList<Tache> listeSansTachesRealisees(){
		ArrayList<Tache> liste2= new ArrayList<Tache>();
		for (int i = 0; i < liste.size(); i++) {
			Tache A = (Tache) liste.get(i);
			if (A.isRealisee()!=true){
				liste2.add(A);
			}
		}
		return liste2;
	}
	
	//écrit sur le fichier la liste des taches triées par ordre de date d'écheance intermédiaire croissante
	public void listeAvancee(){
	}
	
	public void ajoutTache(Tache d){
		if (!liste.contains(d)){
			liste.add(d);
			System.out.println("Ajout de la tâche : "+d+"\n");
		}
		else {
			System.out.println("Vous avez déjà  une tâche semblable \n");
		}
	}

	
	/*
	 * Chaine de caractères avec les informations de la tache
	 */
	public String infosTaches(){
		String s = "";
		for (int i = 0; i < listeSansTachesRealisees().size(); i++) {
			Tache A = (Tache) listeSansTachesRealisees().get(i);
				s=s+"titre: "+A.getTitre()+" | "+"categorie: "+A.getCat()+" | "+"date echeance: "+A.getDateString(A.getEcheance());

				if (A instanceof TacheLongue ) {
					s=s+" | date début :"+((TacheLongue) A).getDateString(((TacheLongue) A).getDateDebut());
				}

				s=s+"<br><br>"; //Permettra de passer à la ligne dans la fenêtre
		}
		return s;
	}
	
	public double getPourcentageNbrTachesRealisees(){ //Pourcentage du nombre de taches réalisées
	int cpt=0;
		for (int i = 0; i < liste.size(); i++) {
			Tache A = (Tache) liste.get(i);
				if (A.isRealisee()==true)
					cpt=cpt+1;
		}
		return (cpt/(double)liste.size())*100;
	}
	
	public double getPourcentageNbrTachesEnRetard(){ //Le nombre de taches en retard
		int cpt=0;
			for (int i = 0; i < liste.size(); i++) {
				Tache A = (Tache) liste.get(i);
					if (A.estRetard()==true)
						cpt=cpt+1;
			}
			return (cpt/(double)liste.size())*100;
		}
	
	public double getPourcentageNbrTachesNonRealisees(){ //Le nombre de taches non réalisées
		double cpt=0;
			for (int i = 0; i < liste.size(); i++) {
				Tache A = (Tache) liste.get(i);
					if (A.isRealisee()==false)
						cpt=cpt+1;
			}
			return (cpt/(double)liste.size())*100;
		}
	
}
