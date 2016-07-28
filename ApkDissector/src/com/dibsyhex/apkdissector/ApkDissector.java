package com.dibsyhex.apkdissector;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class ApkDissector implements Response{
	private static final String VERSION=" v2.0";
	private String fileName;
	
	private JPanel mainpanel;
	private JPanel buttonsPanel;
	private JPanel responsePanel;
	
	private JLabel image;
	private JLabel background;
	private JLabel footerImage;
	private JFileChooser jFileChooser;
	private JButton selectFileButton;
	private JLabel selectfileLabel;
	private JButton analyzeButton;
	private JButton decompileButton;
	private JButton aboutButton;
	private JTextArea responseArea;
	
	
	public ApkDissector(){
		JFrame mainframe=new JFrame();
		mainframe.setTitle("ApkDissector"+VERSION);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setPreferredSize(new Dimension(700, 500));
		mainframe.setVisible(true);
		mainframe.setResizable(false);
		mainframe.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		mainframe.setBackground(Color.WHITE);
		
		background=new JLabel(new ImageIcon(this.getClass().getResource("apkdissectorbackground.png")));
		
		
		
		
		GridBagConstraints g=new GridBagConstraints();
		
		
		//Managing MainPanel
		mainpanel=new JPanel(new GridBagLayout());
		//mainpanel.setBackground(new Color(253,253,181));
		//mainpanel.add(background);
		mainpanel.setOpaque(false);
		
		//Managing Buttons Panel
		buttonsPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		//buttonsPanel.setBackground(Color.WHITE);
		//buttonsPanel.add(background);
		//buttonsPanel=new JPanel(new GridLayout(1,3));
		
		
		//Managing Response Panel
		responsePanel=new JPanel(new FlowLayout());
		responsePanel.setBackground(new Color(226,236,236));
		//responsePanel.add(background);
		
		//Header image
		image=new JLabel(new ImageIcon(this.getClass().getResource("apkdissector.png")));
		mainpanel.add(image);
		
		g.fill=GridBagConstraints.HORIZONTAL;
		g.gridx=0;
		g.gridy=0;
		
		mainframe.add(image,g);
		
		
		//Managing JFileChooser
		jFileChooser=new JFileChooser();
		
		
		
		
		//Managing Select APK Button
		selectFileButton=new JButton("Select APK/DEX file");
		selectFileButton.setBackground(new Color(255,255,166));
		buttonsPanel.add(selectFileButton);
		
		
		//Managing Analyze Button
		analyzeButton=new JButton("Analyze APK File");
		analyzeButton.setBackground(new Color(255,255,166));
		analyzeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				analyze();
			} 
		}); 
		buttonsPanel.add(analyzeButton);
		
		
		//Managing Decompile Button
		decompileButton=new JButton("Decompile APK/DEX");
		decompileButton.setBackground(new Color(255,255,166));
		decompileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(fileName!=null){
					if(fileName.endsWith(".apk"))
						decompile();
					else if(fileName.endsWith(".dex"))
						decompileDexFiles();
				}
				
			}
		});
		buttonsPanel.add(decompileButton);
		
		//Managing about Button
		aboutButton=new JButton("About");
		aboutButton.setBackground(new Color(255,255,166));
		aboutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				about();
			}
		});
		buttonsPanel.add(aboutButton);
		
		
		g.gridx=0;
		g.gridy=1;
		
		mainpanel.add(buttonsPanel,g);
		
		
		//Managing Chosen File
		selectfileLabel=new JLabel("You have chosen:");
		
		
		//Managing Response Panel
		responseArea=new JTextArea("",15,62);
		responseArea.setEditable(false);
		responseArea.setBackground(Color.WHITE);
		responseArea.setFont(new Font("serif",Font.PLAIN,12));
		responsePanel.add(responseArea);
		
		
		g.fill=GridBagConstraints.RELATIVE;
		g.gridx=0;
		g.gridy=2;
		mainpanel.add(responsePanel,g);
		
		JScrollPane jScrollPane=new JScrollPane(responseArea);

		responsePanel.add(jScrollPane);
				
		
		
		
		selectFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int check=jFileChooser.showOpenDialog(mainframe);
				
				if(check == JFileChooser.APPROVE_OPTION){
					File file=jFileChooser.getSelectedFile();
					fileName=file.getAbsolutePath();
					responseArea.setText("");
					responseArea.setText(fileName);
				}
				
			}
		});
		
		
		
		
		
		footerImage=new JLabel(new ImageIcon(this.getClass().getResource("apkdissectorfooter.png")));
		g.fill=GridBagConstraints.RELATIVE;
		g.gridx=0;
		g.gridy=3;
		mainpanel.add(footerImage,g);
		
		
		
		mainframe.add(mainpanel);
		mainframe.pack();
	}
	
	public void analyze(){
		try{
			ZipReader zipReader=new ZipReader();
			zipReader.response=this;
			zipReader.getZipEntries(fileName);
			
			
		}catch(Exception e){
			displayError(e.toString());
		}
		
	}
	
	
	public void decompile(){
		try{
			
			//Extracts the contents 
			ZipReader zipReader=new ZipReader();
			zipReader.response=this;
			zipReader.getZipContents(fileName);
			
			//Decompile the dex files
			
			
			
			
			
			
		}catch(Exception e){
			displayError(e.toString());
		}
		
	}

	public void decompileDexFiles(){
		try{
			DecompileDex2Class decompileDex2Class=new DecompileDex2Class();
			decompileDex2Class.response=this;
			decompileDex2Class.dexDecompile(fileName);
		}catch(Exception e){
			displayError(e.toString());
		}
	}
	
	
	
	public void about(){
		try{
			String developer="Developed by Dibyendu Sikdar\n @dibsyhex \n https://github.com/dibsy";
			JOptionPane.showMessageDialog(null,developer);
			
		}catch(Exception e){
			displayError(e.toString());
		}
	}
	
	
	@Override
	public void displayLog(String log) {
		responseArea.append("\n"+log);
	}
	
	@Override
	public void displayError(String error) {
		JOptionPane.showMessageDialog(null,error);
	}
	
	
	public static void main(String[] args) {
		
		try{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new ApkDissector();
					
				}
			});
		}
		catch(Exception e){
			
		}

	}

	

	

}
