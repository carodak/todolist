import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;


public abstract class Tache implements Serializable{

	private String titre;
	private String cat;
	
	private Date echeance;
	private boolean realisee = false;
	
	
	
	public Tache(String titre, String categorie, String echeance) {
		super();
		this.titre = titre;
		this.cat = categorie;

		//Conversion du String en Date
		setEcheanceSring(echeance);
    }
	
	/*
	 * On peut �galement avoir une t�che sans cat�gorie
	 */
	
	public Tache(String titre, String echeance) {
		super();
		this.titre = titre;
		
		this.cat = "Sans categorie";

		///Conversion du String en Date
		setEcheanceSring(echeance);
    }
	
	/*
	 * Accesseurs en lecture / �criture
	 */
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Date getEcheance() {
		return echeance;
	}
	
	
	
	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}
	
	/*
     * On obtient la dur�e (on utilise la fonction ceil afin d'avoir une arrondie au sup�rieur 
     * dans l'optique de pouvoir commencer une t�che la veille pour le lendemain
     */
	public int getDiff2dates(Date sup, Date inf){
		double diff = sup.getTime() - inf.getTime();
        double diff2dates = (long) Math.ceil(diff / (1000*60*60*24));
        /*
        double diffSecondes = diff / 1000;         
        double diffMinutes = diff / (60 * 1000);         
        double diffHeures = diff / (60 * 60 * 1000);     

        System.out.println("Diff�rence en nombre de secondes entre les deux dates : "+diffSecondes+"\n");
        System.out.println("Diff�rence en nombre de minutes entre les deux dates : "+diffMinutes+"\n");
        System.out.println("Diff�rence en nombre d'heures entre les deux dates : "+diffHeures+"\n");
        System.out.println("Diff�rence en nombre de jour entre les deux dates : "+diff2dates+"\n");
        */
        return (int)diff2dates;
	}

	/*
	 * On transforme la date d'�ch�ance sous la forme d'un String pour pouvoir l'afficher
	 */
	public String getDateString(Date d){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateEcheance = df.format(d);
		return dateEcheance;
	}
	
	public int setEcheanceSring(String echeance) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
	        	//On enregistre la date transform�e en type Date dans l'attribut �ch�ance
	        	this.echeance = sdf.parse(echeance);
	        	
	        	/*Afin de v�rifier que l'�ch�ance soit imp�rativement post�rieure � la date de cr�ation de la tache
	        	 * On utilise la date du jour actuel
	        	 */
	        	
	            Date today = new Date( );
	            
	         // Calcul de diff�rence
	         
	            
	            int diff2dates = getDiff2dates(getEcheance(), today);
	          
	            if (diff2dates<1){
	            	System.out.println("L'�ch�ance ("+diff2dates+") n'est pas post�rieure �la date de la cr�ation de la t�che \n");
	            	return -1;
	            }
	            
		} catch (ParseException e) {
            e.printStackTrace();
        }
         return 0; 
	}
	
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	public boolean isRealisee() {
		return realisee;
	}
	public void setRealisee(boolean realisee) {
		this.realisee = realisee;
	}
	
	public abstract boolean estRetard();
	/*
	 * Tache Ponctuelle : si jour actuel > date �ch�ance alors renvoie true
	 * Tache Longue : d = dur�e impartie pour une t�che � chaque pas d/4, on v�rifie l'avancement
	 */
	
	/*
	 * On soustrait la date d'�ch�ance � la date d'aujourd'hui
	 * Si le r�sultat est >=0 alors la date d'�ch�ance > date aujourd'hui, la t�che n'est pas en retard
	 * sinon la t�che est en retard (-1, -2..), on renvoie la valeur absolue de cette diff�rence
	 */
	public int nbJoursRetardsApresEcheance(){
		Date today = new Date();
		 
		int diff2dates = getDiff2dates(getEcheance(), today);
        
        if (diff2dates>=0){
        	return 0;
        }
        
        else {
        	return Math.abs((int)diff2dates);
        }
	}
	
	

		
}
