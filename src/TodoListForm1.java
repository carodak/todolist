import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;



public class TodoListForm1 extends JFrame {
	private JPanel container = new JPanel();
	private JLabel label;
	
	
	public TodoListForm1 (){      

	    this.setTitle("Liste Tache Simple (taches non réalisées)");

	    this.setSize(600, 350);

	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	    this.setLocationRelativeTo(null);

	    JPanel top = new JPanel();
	    
	    top.setLayout(new GridLayout(0, 1));

	    container.setBackground(Color.white);

	    container.setLayout(new BorderLayout());
	    
	    ObjectInputStream ois = null;
	    
	    

	    try {
	      final FileInputStream fichier = new FileInputStream("Sauvegardes/listeTaches.ser");
	      ois = new ObjectInputStream(fichier);
	      final TodoList T = (TodoList) ois.readObject();

	      T.listeSimple(); //on trie en fonction de la date d'échéance
	      String s = T.infosTaches();
	      
	      System.out.println(s);
	      label = new JLabel("<html>"+s+"</html>", SwingConstants.CENTER);
	      
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
	    
	    container.add(new JScrollPane(top), BorderLayout.NORTH);
	    
	    this.setContentPane(top);
	    this.setVisible(true); 
	}

	public static void main(String[] args){

		  new TodoListForm1();

	}
	
	
}
