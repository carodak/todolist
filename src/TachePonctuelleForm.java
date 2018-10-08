import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JOptionPane;


public class TachePonctuelleForm extends JFrame implements ActionListener {


  private JPanel container = new JPanel();

  private JTextField jtf;    
  private JTextField jtf2;
  private JTextField jtf3;

  private JLabel label = new JLabel("Titre");
  
  private JLabel label2 = new JLabel("Cat�gorie");
  
  private JLabel label3 = new JLabel("Date d'�ch�ance (jj/MM/aaaa)");

  private JButton b = new JButton ("OK");


  public TachePonctuelleForm(){      

    this.setTitle("Tache Ponctuelle");

    this.setSize(300, 250);

    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.setLocationRelativeTo(null);


    container.setBackground(Color.white);

    container.setLayout(new BorderLayout());


    jtf = new JTextField();
    
    jtf2 = new JTextField();
    
    jtf3 = new JTextField();

    JPanel top = new JPanel();      

    Font police = new Font("Arial", Font.BOLD, 14);

    jtf.setFont(police);

    jtf.setPreferredSize(new Dimension(250, 30));

    jtf.setForeground(Color.BLUE);

    //On ajoute l'�couteur � notre composant
    
    jtf.addKeyListener(new ClavierListener());

    top.add(label);
    top.add(jtf);
    
    jtf2.setFont(police);

    jtf2.setPreferredSize(new Dimension(250, 30));

    jtf2.setForeground(Color.BLUE);
    
    jtf2.addKeyListener(new ClavierListener());
    
    top.add(label2);
    top.add(jtf2);
    
    
    jtf3.setFont(police);

    jtf3.setPreferredSize(new Dimension(250, 30));

    jtf3.setForeground(Color.BLUE);
    
    jtf3.addKeyListener(new ClavierListener());
    
    top.add(label3);
    top.add(jtf3);

    

    top.add(b);
    b.addActionListener(this);


    this.setContentPane(top);

    this.setVisible(true);         

  }      


  class ClavierListener implements KeyListener{


    public void keyPressed(KeyEvent event) {

      System.out.println("Code touche press�e : " + event.getKeyCode() + " - caract�re touche press�e : " + event.getKeyChar());

      pause();

    }


    public void keyReleased(KeyEvent event) {

      System.out.println("Code touche rel�ch�e : " + event.getKeyCode() + " - caract�re touche rel�ch�e : " + event.getKeyChar());         

      pause();                  

    }


    public void keyTyped(KeyEvent event) {

      System.out.println("Code touche tap�e : " + event.getKeyCode() + " - caract�re touche tap�e : " + event.getKeyChar());

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

  

  public static void main(String[] args){

    new TachePonctuelleForm();

  }

/*
 * (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 * Cr�� un objet de type TachePonctuelle si l'on a cliqu� sur le bouton de validation
 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == b){ //Si l'on a cliqu� sur le bouton "ok"
			String titre = jtf.getText();
			String categorie = jtf2.getText();
			String echeance = jtf3.getText();
			TachePonctuelle A;
			
			if (!categorie.isEmpty()){
				A = new TachePonctuelle (titre,categorie,echeance); //date au format jj/mm/aaaa  
				Categorie cat = new Categorie();
				cat.ajoutCategorie(A.getCat());
			}
			
			else {
			
				 A = new TachePonctuelle (titre,"sans categorie",echeance); //date au format jj/mm/aaaa  
			
			}
	
			if (A.setEcheanceSring(echeance)==-1){
				JOptionPane.showMessageDialog(this, "Votre date d'�chance n'est pas bonne ","avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
			else {
			
				
				
				JOptionPane.showMessageDialog(this, "Votre t�che a bien �t� cr�� ","Ok", JOptionPane.INFORMATION_MESSAGE);
				
				/*
				 * Si le fichier n'existe pas (= il n'y a pas d'arraylist de taches = on a jamais cr�� de taches)
				 * On cr�� une arraylist de taches et on ins�re notre tache � l'int�rieur
				 * sinon :
				 * on d�s�rialise l'ArrayList existante, on ins�re la nouvelle t�che dedans
				 * on la res�rialise ensuite
				 */
				File monFichier = new File("Sauvegardes/listeTaches.ser");
				if(!monFichier.exists())
				{
					ObjectOutputStream oos = null;

				    try {
				    		TodoList T = new TodoList();
				    		T.ajoutTache(A);
					      final FileOutputStream fichier = new FileOutputStream("Sauvegardes/listeTaches.ser");
					      oos = new ObjectOutputStream(fichier);
					      oos.writeObject(T);
					      oos.flush();
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
				
				else{
					ObjectInputStream ois = null;
					ObjectOutputStream oos = null;

				    try {
				    	//On d�s�rialise la liste sauvegard�e
				      final FileInputStream fichier = new FileInputStream("Sauvegardes/listeTaches.ser");
				      ois = new ObjectInputStream(fichier);
				      final TodoList T = (TodoList) ois.readObject();
				      //On ajoute la nouvelle tache
				      T.ajoutTache(A);
				      
				      //On res�rialise
				      
				      
				      final FileOutputStream fichier2 = new FileOutputStream("Sauvegardes/listeTaches.ser");
				      oos = new ObjectOutputStream(fichier2);
				      oos.writeObject(T);
				      oos.flush();
				      
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
				}
				
				
			
			}

		      pause();
		  	
		      
		}
		
	}

}