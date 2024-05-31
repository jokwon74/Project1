package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AnnualPaidLeave.AnnualPGUI;
import AnnualPaidLeave.AnnualP_AdminGUI;
import Commute.CommuteGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceManage extends JPanel {

    public AttendanceManage(JPanel panel) {
      
    
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1,10,40));
        buttonPanel.setBounds(50,50,170,300);
        
        
        JButton button1 = new JButton("출퇴근 인식");    
        JButton button2 = new JButton("연차,휴가,출장");
        JButton button3 = new JButton("휴일 근무/야근");
        
        button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CommuteGUI();
				
			}
		});
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnnualP_AdminGUI();
				
			}
		});
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        
        add(buttonPanel);
        
  
        JPanel tablepanel = new JPanel();
        tablepanel.setBounds(150, 30, 800, 400);
        

    }

}