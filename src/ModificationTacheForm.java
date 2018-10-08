import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;


public class ModificationTacheForm extends JFrame {

private JPanel container = new JPanel();
private JRadioButton[] boutons; //Tableau de boutons radios (permettant de valider si une tache est réalisée)
private JTextField[] jtf; //Tableau de champs de saisis (permettant de saisir l'avancement)



public ModificationTacheForm (){   

    this.setTitle("Edition Tache (confirmer la réalisation..)");

    this.setSize(1050, 700);

    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.setLocationRelativeTo(null);

  container.setBackground(Color.white);

  container.setLayout(new BorderLayout());

  JPanel top = new JPanel();
  
  top.setLayout(new GridLayout());
  
  ObjectInputStream ois = null;
  
  
  try {
	  /*
	   * En premier lieu on désérialise notre fichier afin de parcourir la liste des tâches
	   */
      final FileInputStream fichier = new FileInputStream("Sauvegardes/listeTaches.ser");
      ois = new ObjectInputStream(fichier);
      final TodoList T = (TodoList) ois.readObject();

      /*
       * On associe un bouton radio (tache réalisée) à côté de chaque tache
       */
      int n = T.getListe().size();
      boutons = new JRadioButton[n];
      jtf = new JTextField[n];
      
     
      
      
  	for (int i = 0; i < T.listeSansTachesRealisees().size(); i++) {
  		Tache A = (Tache) T.listeSansTachesRealisees().get(i);

		
  		if (A instanceof TachePonctuelle ) {

			JLabel label = new JLabel("<html>Type tache : ponctuelle<br>"+"Nom: "+A.getTitre()+"<br>"+"Date Echeance: "+A.getDateString(A.getEcheance())+"<br>"+"Retard: "+A.estRetard()+"<br><br></html>", SwingConstants.LEFT);
			 
			top.add(label);
			 
			boutons[i] = new JRadioButton("Réalisée");
			/*
			 * Si la tâche a bien été réalisée, on modifie l'attribut réalisée puis on sérialise 
			 */
			
			 boutons[i].addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent evn) {
	                	JRadioButton b = (JRadioButton)evn.getSource();
	                    System.out.println("source : " + ((JRadioButton)evn.getSource()).getText() + " - état : " + ((JRadioButton)evn.getSource()).isSelected());
	                    System.out.println("Nom :"+A.getTitre());
	                    
	                    A.setRealisee(true);
	                    
	                    /*
	                     * -------SERIALISATION
	                     */
	                    ObjectOutputStream oos = null;

					    try {
						      final FileOutputStream fichier = new FileOutputStream("Sauvegardes/listeTaches.ser");
						      oos = new ObjectOutputStream(fichier);
						      oos.writeObject(T);
						      oos.flush();
						      JOptionPane.showMessageDialog(top, "Votre tâche est bien terminée ","Ok", JOptionPane.INFORMATION_MESSAGE);
					    } 
					    
					    catch (final java.io.IOException e) {
					      e.printStackTrace();
					    } 
					    
					    finally {
					      try {
					        if (oos != null) {
					          oos.flush();
					          oos.close();
					        }
					      } catch (final IOException ex) {
					        ex.printStackTrace();
					      }
					    }	
	                }
	            }); 
			  
			 top.add(boutons[i]);
		
  		}	
  		
		/*
		 * On veut pouvoir également modifier l'avancement
		 */
		if (A instanceof TacheLongue ) {
	
			
			((TacheLongue) A).calcProchaineDateEcheanceIntermediaire();
			String dateEcheanceInt = ((TacheLongue) A).getDateString(((TacheLongue) A).getDateEcheanceIntermediaire());
		
			
			String s = "Prochaine échéance intermédiaire: "+dateEcheanceInt+" | Avancement: "+((TacheLongue) A).getAvancement()+"%";
			
			JLabel label = new JLabel("<html>Type tache : longue<br>"+"Nom: "+A.getTitre()+"<br>"+s+"<br> Date Echeance: "+A.getDateString(A.getEcheance())+"<br>"+"Retard: "+A.estRetard()+"<br>"+"Modifier avancement: "+"</html>", SwingConstants.LEFT);
			top.add(label);
			
			boutons[i] = new JRadioButton("Réalisée");
			
			
			/*
			 * Si la tâche a bien été réalisée, on modifie l'attribut réalisée puis on sérialise 
			 */
			
			 boutons[i].addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent evn) {
	                	JRadioButton b = (JRadioButton)evn.getSource();
	                    System.out.println("source : " + ((JRadioButton)evn.getSource()).getText() + " - état : " + ((JRadioButton)evn.getSource()).isSelected());
	                    System.out.println("Nom :"+A.getTitre());
	                    
	                    A.setRealisee(true);
	                    
	                    
	                    
	                    /*
	                     * -------SERIALISATION
	                     */
	                    ObjectOutputStream oos = null;

					    try {
						      final FileOutputStream fichier = new FileOutputStream("Sauvegardes/listeTaches.ser");
						      oos = new ObjectOutputStream(fichier);
						      oos.writeObject(T);
						      oos.flush();
						      JOptionPane.showMessageDialog(top, "Votre tâche est bien terminée ","Ok", JOptionPane.INFORMATION_MESSAGE);
					    } 
					    
					    catch (final java.io.IOException e) {
					      e.printStackTrace();
					    } 
					    
					    finally {
					      try {
					        if (oos != null) {
					          oos.flush();
					          oos.close();
					        }
					      } catch (final IOException ex) {
					        ex.printStackTrace();
					      }
					    }	
	                }
	            }); 
			  
			
			
		//A FAIRE : évènement pour modification avancement (non fait par manque de temps)

			jtf[i] = new JTextField();
			 jtf[i].setPreferredSize(new Dimension(10, 10));
			  
			Font police = new Font("Arial", Font.BOLD, 14);
		    jtf[i].setFont(police);
		  
		    jtf[i].setForeground(Color.BLUE);
		    jtf[i].addKeyListener(new ClavierListener());
		    
		    top.add(jtf[i]);
		    top.add(boutons[i]);
		  
		   
		}
		
		
	}
  	
  } catch (final java.io.IOException e) {
      e.printStackTrace();
    } catch (final ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ois != null) {
          ois.close();
        }
      } catch (final IOException ex) {
        ex.printStackTrace();
      }
    }

  



 // container.add(new JScrollPane(top), BorderLayout.NORTH);
  container.add(top, BorderLayout.NORTH);
  this.setContentPane(container);

  this.setVisible(true);            

}       

     

class ClavierListener implements KeyListener{


    public void keyPressed(KeyEvent event) {

      System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());

      pause();

    }


    public void keyReleased(KeyEvent event) {

      System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         

      pause();                  

    }


    public void keyTyped(KeyEvent event) {

      System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());

      pause();

    }       

  }   


  private void pause(){

    try {

      Thread.sleep(5);

    } catch (InterruptedException e) {

      e.printStackTrace();

    }

  }   

  /*
   * Vérifier si une chaine de caractères est un entier
   */
  public static boolean estInt(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args){
	
		  new ModificationTacheForm();
	
	}
}

