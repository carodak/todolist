import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TacheLongue extends Tache {

	private Date dateDebut;
	private Date dateEcheanceIntermediaire; //A pour valeur la date de la prochaine échéance intermédiaire
	private int step = 0; //permet de savoir si l'on est à dureeImpartie/4 (step vaut 1), dureeImpartie/2(step vaut 2)...
	private int dureeImpartie; //différence entre échéance et date de début
	private int avancement = 0; //avancement en pourcent
	
	

	public TacheLongue(String titre, String categorie, String dateDebut, String echeance) {
		super(titre, categorie, echeance);
		// TODO Auto-generated constructor stub
		if (dateDebut.isEmpty()){
			Date actuelle = new Date();
			this.dateDebut = actuelle;
		}
		else setDateDebutSring(dateDebut);
		
		this.dureeImpartie = getDiff2dates(getEcheance(), getDateDebut());
		
         
	}
	
	

	public TacheLongue(String titre, String dateDebut, String echeance) {
		super(titre, echeance);
		// TODO Auto-generated constructor stub
		if (dateDebut.isEmpty()){
			Date actuelle = new Date();
			this.dateDebut = actuelle;
		}
		else setDateDebutSring(dateDebut);
		
		this.dureeImpartie = getDiff2dates(getEcheance(), getDateDebut());
	}


	public int setDateDebutSring(String dateDebut) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
	        	//On enregistre la date transformée en type Date dans l'attribut dateDebut
	        	this.dateDebut = sdf.parse(dateDebut);
	        	
	        	/*On vérifie que la date de début soit impérativement postérieure à l'échéance
	        	 * 
	        	 */
	        	
	            
	        	int diff2dates = getDiff2dates(getEcheance(), getDateDebut());
	            
	            
	            if (diff2dates<1){
	            	System.out.println("La date de début n'est pas postérieure à la date d'échéance  ("+diff2dates+")  \n");
	            	return -1;
	            }
	            
		} catch (ParseException e) {
            e.printStackTrace();
        }
		
		return 0;
          
	}
	
	


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public int getAvancement() {
		return avancement;
	}


	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	
	public Date getDateEcheanceIntermediaire() {
		return dateEcheanceIntermediaire;
	}
	
	public void setDateEcheanceIntermediaire(Date dateEcheanceIntermediaire) {
		this.dateEcheanceIntermediaire = dateEcheanceIntermediaire;
	}

	public int getDureeEcouleeDepuisDateDebut(){
		Date actuelle = new Date();
		return getDiff2dates(actuelle,getDateDebut());
	}
	
	
	/*
	 * On calcule la date de la prochaine échéance intermédiaire
	 * Pour cela:
	 * 
	 */
	
	public void calcProchaineDateEcheanceIntermediaire() {
		if (step==0){
			int d =(int)this.dureeImpartie/4;
			Calendar c = Calendar.getInstance();
			c.setTime(getDateDebut());
			c.add(Calendar.DATE, d);  // nombres de jours à ajouter
			
			this.dateEcheanceIntermediaire = c.getTime();
			step=1;
		}
		
		else if (step==1){
			int d =(int)this.dureeImpartie/2;
			Calendar c = Calendar.getInstance();
			c.setTime(getDateDebut());
			c.add(Calendar.DATE, d);  // nombres de jours à ajouter
			
			this.dateEcheanceIntermediaire = c.getTime();
			step=2;
		}
		
		else if (step==2){
			int d =(int)3*this.dureeImpartie/4;
			Calendar c = Calendar.getInstance();
			c.setTime(getDateDebut());
			c.add(Calendar.DATE, d);  // nombres de jours à ajouter
			
			this.dateEcheanceIntermediaire = c.getTime();
			step = 3;
		}
		
		else if (step==3){
			int d =(int)this.dureeImpartie;
			Calendar c = Calendar.getInstance();
			c.setTime(getDateDebut());
			c.add(Calendar.DATE, d);  // nombres de jours à ajouter
			
			this.dateEcheanceIntermediaire = c.getTime();
			step = 4;
		}
		
		
	
	}

	@Override
	public boolean estRetard() {//A modifier 
		
		Date actuelle = new Date();
		
		//Si le jour actuel est le jour de la prochaine échéance intermédiaire
		if (actuelle==getDateEcheanceIntermediaire()){
			
			int a = getAvancement();
			
			if (getStep() == 1 && a!=25){
				System.out.println("L'avancement devrait être de 25%  ("+getAvancement()+"). Vous êtes en retard.  \n");
				return true;
			}
			
			else if (getStep() == 2 && a!=50){
				System.out.println("L'avancement devrait être de 50%  ("+getAvancement()+"). Vous êtes en retard.  \n");
				return true;
			}
			
			else if (getStep() == 3 && a!=75){
				System.out.println("L'avancement devrait être de 75%  ("+getAvancement()+"). Vous êtes en retard.  \n");
				return true;	
			}
			
			else if (getStep() == 4 && a!=100){
				System.out.println("L'avancement devrait être de 100%  ("+getAvancement()+"). Vous êtes en retard.  \n");
				return true;	
			}
		}

		System.out.println("Vous avez encore jusqu'au "+getDateString(getDateEcheanceIntermediaire())+". Vous n'êtes pas en retard.  \n");
		return false;
		

	}

	public int getDureeImpartie() {
		return dureeImpartie;
	}

	public void setDureeImpartie(int dureeImpartie) {
		this.dureeImpartie = dureeImpartie;
	}
	
	

}
