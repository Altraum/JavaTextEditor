/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereadwrite;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

public class FileReadWrite {
    
    private static void saveWindow(String msg){
        JFrame saveFrame = new JFrame();
        saveFrame.setLayout(new FlowLayout());
        saveFrame.setTitle("Save As...");
        saveFrame.setSize(300,100);
        JLabel fileNameLabel = new JLabel("Save as:");
        saveFrame.add(fileNameLabel);

        TextField titleInput = new TextField();
        titleInput.setColumns(25);
        saveFrame.add(titleInput);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(
            new ActionListener()  {
                public void actionPerformed(ActionEvent e) {
                    try{
                        if(titleInput.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Please name the file to save under at the top.", "No File Name", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            writeFile(titleInput.getText() + ".txt", msg);
                            saveFrame.dispose();
                        }

                    }
                    catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
        );
        saveFrame.add(saveBtn);
        saveFrame.setVisible(true);
    }
    
    private static void userWindow(){
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setTitle("Text Editor");
        window.setSize(1280,720);
	window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel content = new JPanel();
	content.setLayout(new BorderLayout());
	window.add(content);
        
        TextArea userMessage= new TextArea();
        content.add(userMessage, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(
            new ActionListener()  {
                public void actionPerformed(ActionEvent e) {
                    saveWindow(userMessage.getText());
                }
            }
        );
        fileMenu.add(saveMenuItem);
        
        window.setVisible(true);
    }

    private static String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }
        return stringBuilder.toString();
    }
    
    private static void writeFile(String fileName, String txt) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(txt);
        writer.close();
    }
    
    public static void main(String[] args) throws IOException {
        userWindow();
    }
}
