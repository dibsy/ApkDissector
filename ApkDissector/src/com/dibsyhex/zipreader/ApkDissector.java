package com.dibsyhex.zipreader;


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
	private static final String VERSION=" v1.1";
	private String fileName;
	
	private JPanel mainpanel;
	private JPanel buttonsPanel;
	private JPanel responsePanel;
	
	private JLabel image;
	private JLabel footerImage;
	private JFileChooser jFileChooser;
	private JButton selectFileButton;
	private JLabel selectfileLabel;
	private JButton analyzeButton;
	private JButton decompileButton;
	private JTextArea responseArea;
	
	
	public ApkDissector(){
		JFrame mainframe=new JFrame();
		mainframe.setTitle("ApkDissector"+VERSION);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setPreferredSize(new Dimension(700, 500));
		mainframe.setVisible(true);
		mainframe.setResizable(false);
		mainframe.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		mainframe.setBackground(Color.BLACK);
		
		
		
		
		GridBagConstraints g=new GridBagConstraints();
		
		
		//Managing MainPanel
		mainpanel=new JPanel(new GridBagLayout());
		mainpanel.setBackground(Color.BLACK);
		
		//Managing Buttons Panel
		buttonsPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.setBackground(Color.BLACK);
		//buttonsPanel=new JPanel(new GridLayout(1,3));
		
		
		//Managing Response Panel
		responsePanel=new JPanel(new FlowLayout());
		responsePanel.setBackground(Color.BLACK);
		
		
		//Header image
		image=new JLabel(new ImageIcon("apkdissector.jpg"));
		mainpanel.add(image);
		
		g.fill=GridBagConstraints.HORIZONTAL;
		g.gridx=0;
		g.gridy=0;
		
		mainframe.add(image,g);
		
		
		//Managing JFileChooser
		jFileChooser=new JFileChooser();
		
		
		
		
		//Managing Select APK Button
		selectFileButton=new JButton("Select APK file");
		selectFileButton.setBackground(new Color(215,215,215));
		buttonsPanel.add(selectFileButton);
		
		
		//Managing Analyze Button
		analyzeButton=new JButton("Analyze");
		analyzeButton.setBackground(new Color(215,215,215));
		analyzeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				analyze();
			} 
		}); 
		buttonsPanel.add(analyzeButton);
		
		
		//Managing Decompile Button
		decompileButton=new JButton("Decompile");
		decompileButton.setBackground(new Color(215,215,215));
		decompileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				decompile();
			}
		});
		buttonsPanel.add(decompileButton);
		
		
		g.gridx=0;
		g.gridy=1;
		
		mainpanel.add(buttonsPanel,g);
		
		
		//Managing Chosen File
		selectfileLabel=new JLabel("You have chosen:");
		
		
		//Managing Response Panel
		responseArea=new JTextArea("",15,62);
		responseArea.setEditable(false);
		responseArea.setBackground(new Color(206,206,206));
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
		
		
		
		
		
		footerImage=new JLabel(new ImageIcon("apkdissectorfooter.jpg"));
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
			ZipReader zipReader=new ZipReader();
			zipReader.response=this;
			zipReader.getZipContents(fileName);
			
			
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
