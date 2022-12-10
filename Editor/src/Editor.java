import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

public class Editor extends JFrame implements ActionListener {
    JFrame f;
    JTextArea t;

    Editor() {
        //intialising frame object
        f = new JFrame("notepad");
        try {
            UIManager.setLookAndFeel("Javax.swing.plaf.metalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
            //e.printStackTrace
            System.out.println("Exception Occuring");
        }
        //Creating text component
        t = new JTextArea();
        //creating Menubar
        JMenuBar menub = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem n1 = new JMenuItem("New");
        JMenuItem n2 = new JMenuItem("Open");
        JMenuItem n3 = new JMenuItem("Save");
        JMenuItem n4 = new JMenuItem("Print");
        n1.addActionListener(this);
        n2.addActionListener(this);
        n3.addActionListener(this);
        n4.addActionListener(this);

        file.add(n1);
        file.add(n2);
        file.add(n3);
        file.add(n4);

        JMenu edit = new JMenu("Edit");
        JMenuItem n5 = new JMenuItem("Cut");
        JMenuItem n6 = new JMenuItem("Copy");
        JMenuItem n7 = new JMenuItem("Paste");

        n5.addActionListener(this);
        n6.addActionListener(this);
        n7.addActionListener(this);

        //adding menu item to edit Menu
        edit.add(n5);
        edit.add(n6);
        edit.add(n7);

        //creating close button
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        //adding Menus to Menubar
        menub.add(file);
        menub.add(edit);
        menub.add(close);

        //add menubar & textarea to frame
        f.setJMenuBar(menub);
        f.add(t);
        f.setSize(800, 400);
        f.show();
    }

    //funtion to add functionality to each menuitems
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("New")) {
             t.setText(" ");
        } else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser(("D : "));
            int r = j.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try{
                    String s1 = " ",s2 = " ";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    while((s1 = br.readLine()) != null){
                        s2 =s2+'\n'+s1;
                    }
  t.setText(s2);
                }catch (Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                }
            }

        } else if (s.equals("Save")) {
            JFileChooser j =new JFileChooser("D: ");
            int r = j.showSaveDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
File fi = new File(j.getSelectedFile().getAbsolutePath());
 try {
     FileWriter fw = new FileWriter(fi);
     BufferedWriter bw = new BufferedWriter(fw);

     bw.write(t.getText());
     bw.flush();
     bw.close();
 }
 catch (Exception et){
     JOptionPane.showMessageDialog(f,et.getMessage());
 }
 }
        } else if (s.equals("Print")) {
         try{  t.print();
        }
         catch (PrinterException ex){
             throw new RuntimeException(ex);
         }
        }
        else if (s.equals("Cut")) {
             t.cut();
        } else if (s.equals("Copy")) {
             t.copy();
        } else if (s.equals("Paste")) {
           t.paste();
        } else if (s.equals("Close")) {
            f.setVisible(false);
        }
    }
}
