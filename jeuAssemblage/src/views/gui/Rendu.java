package views.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup; // BP: Import non utilisé
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton; // BP: Import non utilisé

import model.Game;
import observer.ListeningModel;

import javax.swing.JPanel;
import javax.swing.JRadioButton; // BP: Import non utilisé
import javax.swing.JScrollPane;

/**
 * cette classe permet de contenir la grille graphique et les boutons de navigation
 * @author Mamadou Alpha Diallo
 * @version 1.0
 */
public class Rendu extends JPanel implements ListeningModel {

	private static final long serialVersionUID = 1L;

	protected JPanel rendu = new JPanel(new BorderLayout());
	protected JPanel nav=new JPanel(new FlowLayout());
	protected JPanel nav2=new JPanel(new FlowLayout());
	protected JButton playAIBtn, advancedAIBtn, endBtn, rotateCWBtn, rotateACWBtn;
	private JComboBox<String> boardConfigSelect;
	protected JButton startBtn;
	protected JLabel actionsLabel;
	protected JLabel actionValueLabel;
	protected JLabel scoreLabel;
	protected JLabel scoreValueLabel;
	protected Game game;
	protected String currentConfigOption;


	public Rendu(Game game){
		this.setLayout(new BorderLayout());

		// Listen to model
		this.game = game;
		this.game.addListening(this);
		
		//boutton de navigation
		this.startBtn=new JButton(Constants.START_GAME_LABEL);
		String[] options = {Constants.DEFAULT_CONFIG_LABEL, Constants.RANDOM_CONFIG_LABEL};
		this.currentConfigOption = Constants.DEFAULT_CONFIG_LABEL;
        
		this.boardConfigSelect = new JComboBox<>(options);
		if(game.getBoard().randomPlacementStrategy() == true) {
			this.boardConfigSelect.setSelectedItem(Constants.RANDOM_CONFIG_LABEL);
			this.currentConfigOption = Constants.DEFAULT_CONFIG_LABEL;
		}
		
		this.playAIBtn= new JButton("Jouer IA");
		this.advancedAIBtn= new JButton("IA Avancé");
		this.endBtn= new JButton("Terminer la partie");

		// actions
		this.actionsLabel = new JLabel("Actions restantes: ");
		this.actionValueLabel = new JLabel(game.getRemainingActions() + "");

		// actions
		this.scoreLabel = new JLabel("Score: ");
		this.scoreValueLabel = new JLabel("");
		
		//creation de panel nav
		this.nav.setPreferredSize(new Dimension(100,40));
		nav.add(this.startBtn);nav.add(this.boardConfigSelect);
		nav.add(this.playAIBtn);nav.add(this.advancedAIBtn);nav.add(this.endBtn);
		nav.add(actionsLabel); nav.add(actionValueLabel);nav.add(scoreLabel);
		nav.add(scoreValueLabel);

		//this.nav.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		// nav 2
		this.rotateCWBtn = new JButton("Rotation 90°");
		this.rotateACWBtn = new JButton("Rotation -90°");
		this.nav2.setPreferredSize(new Dimension(100,40));
		nav2.add(rotateCWBtn); nav2.add(rotateACWBtn);
		//this.nav2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		// panell north
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.setPreferredSize(new Dimension(100,100));
		northPanel.add(nav);
		northPanel.add(nav2);
		northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		

		//creation de panel west pour le marging
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(20,0));

		this.rendu.setPreferredSize(new Dimension(600,600));
		this.rendu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.rendu.add(west,BorderLayout.WEST);

		JScrollPane jsc1=new JScrollPane(this.rendu);
		JScrollPane jsc2=new JScrollPane(northPanel);

		
		this.add(jsc2,BorderLayout.NORTH);
		this.add(jsc1,BorderLayout.CENTER);
		manageEvents();
	}

	JScrollPane getRenduPanel(){
		return new JScrollPane(this);
	}

	/**
	 * 
	 */
	public void manageEvents() {
		// BP: Cette méthode est trop complexe, il faut la séparer en plusieurs fonctions
		// Start btn
		startBtn.addActionListener(new ActionListener() { // BP: Vous pouvez utilisez une fonction anonyme avec l'opérateur `->`, c'est plus esthétique
			@Override
			public void actionPerformed(ActionEvent e) {
				// Start or restart the game
				game.start();
				if(startBtn.getText() == Constants.START_GAME_LABEL) {
					startBtn.setText(Constants.RESTART_GAME_LABEL);
				}
			}
		});

		// Config
		boardConfigSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsqu'une option est sélectionnée
                String selectedOption = (String) boardConfigSelect.getSelectedItem();
				boolean isPossible = false;
				
				if(selectedOption == Constants.RANDOM_CONFIG_LABEL) {
					isPossible = game.useRandomPlacementStrategy(true);
				} else {
					isPossible = game.useRandomPlacementStrategy(false);
				}

				if(!isPossible) {
					JOptionPane.showMessageDialog(rendu, Constants.WARNING_CHANGE_CONFIG_LABEL, Constants.WARNING_LABEL, JOptionPane.WARNING_MESSAGE, null);
					boardConfigSelect.setSelectedItem(currentConfigOption);
				} else {
					currentConfigOption = selectedOption;
				}
            }
        });

		// AI Random Button
		playAIBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isOnGoing()) {
					JOptionPane.showMessageDialog(rendu, Constants.WARNING_START_GAME_BEFORE_LABEL, Constants.WARNING_LABEL, JOptionPane.WARNING_MESSAGE, null);
				} else {
					game.playRandomAI();
				}
				
			}
		});

		// Advanced AI Button
		advancedAIBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isOnGoing()) {
					JOptionPane.showMessageDialog(rendu, Constants.WARNING_START_GAME_BEFORE_LABEL, Constants.WARNING_LABEL, JOptionPane.WARNING_MESSAGE, null);
				} else {
					game.playAdvancedAI();
				}
			}
		});

		// Game over button
		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.gameOver();
			}
		});

		// Rotaction 90 button
		rotateCWBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PieceView.currentSelectedPiece != null) {
					game.rotatePiece(PieceView.currentSelectedPiece.getPiece(), true);
				} else {
					JOptionPane.showMessageDialog(rendu, Constants.SELECT_PIECE_LABEL, Constants.WARNING_LABEL, JOptionPane.WARNING_MESSAGE, null);
				}
				
			}
		});

		rotateACWBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PieceView.currentSelectedPiece != null) {
					game.rotatePiece(PieceView.currentSelectedPiece.getPiece(), false);
				} else {
					JOptionPane.showMessageDialog(rendu, Constants.SELECT_PIECE_LABEL, Constants.WARNING_LABEL, JOptionPane.WARNING_MESSAGE, null);
				}
				
			}
		});

		
	}

	/**
	 * 
	 */
	public void updateGame() {

		// Update score
		scoreValueLabel.setText(game.getScore() + "");

		// Update remaining actions
		actionValueLabel.setText(game.getRemainingActions() + "");

		// Game over
		if(game.isOnGoing() == false) {
			JOptionPane.showMessageDialog(rendu, Constants.GAME_OVER_LABEL + game.getScore(), Constants.INFO_LABEL, JOptionPane.INFORMATION_MESSAGE, null);
		}
	}


	@Override
	public void modelUpdtae(Object source) {
		updateGame();
	}

	public Game getGame() {
		return game;
	}

	

}
