import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class BilanForm extends JFrame {
	private JPanel container = new JPanel();
	private JLabel label;
	
	
	public BilanForm (){      

	    this.setTitle("Edition de Bilan");

	    this.setSize(600, 350);

	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	    this.setLocationRelativeTo(null);

	    JPanel top = new JPanel();

	    container.setBackground(Color.white);

	    container.setLayout(new BorderLayout());
	    
	    ObjectInputStream ois = null;

	    try {
	      final FileInputStream fichier = new FileInputStream("Sauvegardes/listeTaches.ser");
	      ois = new ObjectInputStream(fichier);
	      final TodoList T = (TodoList) ois.readObject();
	      
	    //  System.out.println(T.getPourcentageNbrTachesNonRealisees());
	      
		    double tr = T.getPourcentageNbrTachesRealisees();
		    double ter = T.getPourcentageNbrTachesEnRetard();
		    double tnr = T.getPourcentageNbrTachesNonRealisees();
		    
		    
		    label = new JLabel("<html>Taches Realisées: "+tr+"%<br>Taches non réalisées: "+tnr+"%<br>Taches en retard: "+ter+"%</html>", SwingConstants.CENTER);
	      
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
	    
	    
	    top.add(label);
	    
	    container.add(top, BorderLayout.NORTH);
	    
	    this.setContentPane(top);
	    this.setVisible(true); 
	}

	public static void main(String[] args){

		  new BilanForm();

	}
	
	
}
