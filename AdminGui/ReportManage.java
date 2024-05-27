package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ReportManage extends JPanel {

    public ReportManage(JPanel panel) {
      
    
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1,10,40));
        buttonPanel.setBounds(50,50,170,300);
        
        
        JButton button1 = new JButton("신청관리");    
        JButton button2 = new JButton("업무 전달");
        JButton button3 = new JButton("공지사항 관리");
        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        
        add(buttonPanel);
        
  
        JPanel tablepanel = new JPanel();
        tablepanel.setBounds(150, 30, 800, 400);
        
    }

}