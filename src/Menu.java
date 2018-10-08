import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JPanel;



public class Menu extends JFrame implements ActionListener {

  private JMenuBar menuBar = new JMenuBar();
  
  JPanel top = new JPanel();  

  private JMenu test1 = new JMenu("Fichier");

  
  private JButton b = new JButton ("Creation d'une tache ponctuelle");
  
  private JButton b4 = new JButton ("Creation d'une tache longue");
  
  private JButton b6 = new JButton ("Edition tache");

  private JButton b2 = new JButton ("Affichage simple des taches");
  
  private JButton b5 = new JButton ("Affichage avancé des taches");
  
  private JButton b3 = new JButton ("Edition de bilans");


  private JMenuItem item2 = new JMenuItem("Fermer");



  public static void main(String[] args){
	  

    Menu men = new Menu();

  }


  public Menu(){
	  
	this.setTitle("Menu"); 

    this.setSize(250, 300);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLocationRelativeTo(null);




    //On ajoute les boutons de création,affichage de taches et d'édition de bilan
    top.add(b);
    b.addActionListener(this);
    
    top.add(b4);
    b4.addActionListener(this);
    
    top.add(b6);
    b6.addActionListener(this);
    
    top.add(b2);
    b2.addActionListener(this);
    
    top.add(b5);
    b5.addActionListener(this);
    
    top.add(b3);
    b3.addActionListener(this);
    
    //On affiche le JPanel dans la fenêtre
    this.setContentPane(top);


    //Ajout d'un séparateur

    this.test1.addSeparator();

    item2.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent arg0) {

        System.exit(0);

      }        

    });

    this.test1.add(item2);  

   


    //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite

    //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier

    this.menuBar.add(test1);

    

    this.setJMenuBar(menuBar);

    this.setVisible(true);

  }


@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == b){ //Si l'on a cliqué sur le bouton "Tache Ponctuelle"
			new TachePonctuelleForm();
		}
		
		else if (source == b4){ //Si l'on a cliqué sur le bouton "Tache Longue"
			new TacheLongueForm();
		}
		
		else if (source == b6){ //Si l'on a cliqué sur le bouton "Modification taches"
			new ModificationTacheForm();
		}
		
		else if (source == b2){ //Si l'on a cliqué sur le bouton "Affichage Simple"
			new TodoListForm1();
		}
		
		else if (source == b5){ //Si l'on a cliqué sur le bouton "Affichage Avancé"
			new TodoListForm2();
		}
		
		else if (source == b3){ //Si l'on a cliqué sur le bouton "Edition de Bilans"
			new BilanForm();
		}
	}
}