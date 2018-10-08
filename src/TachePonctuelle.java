
public class TachePonctuelle extends Tache {
	

	public TachePonctuelle(String titre, String categorie, String echeance) {
		super(titre, categorie, echeance);
		// TODO Auto-generated constructor stub
	}
	
	

	public TachePonctuelle(String titre, String echeance) {
		super(titre, echeance);
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean estRetard() {
		// TODO Auto-generated method stub
		         
         if (nbJoursRetardsApresEcheance()>0){
         	System.out.println("Votre tâche ponctuelle est en retard de "+nbJoursRetardsApresEcheance()+" jours \n");
         	return true;
         }
         
         else {
        	 System.out.println("Votre tâche ponctuelle n'est pas en retard \n");
        	 return false;
         }
	}

}
