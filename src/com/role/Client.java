package com.role;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.role.ui.GBC;

public class Client extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218521135140387319L;

	private JButton importPolyBtn;
	private JButton initPolyBtn;
	private JButton genKeyBtn;
	private JButton reqBtn;
	private JTextField reqField;
	private JTextField resultField;
	private JTextArea ekArea;
	private JTextArea skArea;
	private JTextArea polyArea;
	private JTextField serverField;
	private JTextArea statusArea;
	public Client() {
		this.setLayout(null);
		JPanel serPanel = serverPanel();
		JPanel pPanel = polyPanel();
		serPanel.setBounds(0, 0, 650, 60);
		pPanel.setBounds(1, 65, 649, 395);
		this.add(serPanel);
		this.add(pPanel);
		this.setTitle("Client");
		this.setLocation(800, 400);
		this.setSize(690, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	public JPanel polyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel label = new JLabel("多项式信息");
		label.setBounds(10, 10, 80, 30);
		polyArea = new JTextArea();
		polyArea.setBounds(90, 10, 450, 70);
		polyArea.setLineWrap(true);// 激活自动换行功能
		polyArea.setWrapStyleWord(true);// 激活断行不断字功能
		polyArea.setBackground(Color.LIGHT_GRAY);
		polyArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"多项式"));

		importPolyBtn = new JButton("文件导入");
		importPolyBtn.setBounds(550, 15, 90, 25);
		
		initPolyBtn = new JButton("随机生成");
		initPolyBtn.setBounds(550, 50, 90, 25);
		initPolyBtn.addActionListener(this);
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 85, 645, 1);
		separator.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel keyLabel = new JLabel("密钥生成");
		keyLabel.setBounds(10, 88, 70, 28);
		ekArea = new JTextArea();
		ekArea.setBounds(90, 88, 450, 60);
		ekArea.setLineWrap(true);// 激活自动换行功能
		ekArea.setWrapStyleWord(true);// 激活断行不断字功能
		ekArea.setBackground(Color.LIGHT_GRAY);
		ekArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"求值密钥"));
		skArea = new JTextArea();
		skArea.setBounds(90, 150, 450, 60);
		skArea.setLineWrap(true);// 激活自动换行功能
		skArea.setWrapStyleWord(true);// 激活断行不断字功能
		skArea.setBackground(Color.LIGHT_GRAY);
		skArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"验证私钥"));
		
		genKeyBtn = new JButton("密钥生成");
		genKeyBtn.setBounds(545, 180, 90, 25);
		genKeyBtn.addActionListener(this);
		
		JSeparator separator2 = new JSeparator(JSeparator.HORIZONTAL);
		separator2.setBounds(2, 220, 645, 1);
		separator2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel reqLabel = new JLabel("计算请求");
		reqLabel.setBounds(10, 225, 70, 28);
		reqField = new JTextField();
		reqField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		reqField.setBounds(90, 225, 200, 28);
		reqField.setBackground(Color.LIGHT_GRAY);
		reqBtn = new JButton("发送请求"); 
		reqBtn.setBounds(295, 225, 87, 25);
		reqBtn.addActionListener(this);
		JSeparator separator4 = new JSeparator(JSeparator.VERTICAL);
		separator4.setBounds(390, 221, 1, 37);
//		separator4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel resultLabel = new JLabel("计算结果");
		resultLabel.setBounds(395, 225, 60, 25);
		resultField = new JTextField();
		resultField.setBounds(455, 225, 180, 28);
		resultField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resultField.setBackground(Color.LIGHT_GRAY);
		
		JSeparator separator3 = new JSeparator(JSeparator.HORIZONTAL);
		separator3.setBounds(2, 258, 645, 1);
		separator3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		statusArea = new JTextArea();
		statusArea.setBounds(10, 260, 628, 130);
		statusArea.setLineWrap(true);// 激活自动换行功能
		statusArea.setWrapStyleWord(true);// 激活断行不断字功能
		statusArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"状态栏"));
		statusArea.setBackground(Color.LIGHT_GRAY);
		
		statusArea.setLineWrap(true);
		
		panel.add(statusArea);
		panel.add(statusArea);
		panel.add(resultField);
		panel.add(separator4);
		panel.add(resultLabel);
		panel.add(separator3);
		panel.add(genKeyBtn);
		panel.add(reqBtn);
		panel.add(reqField);
		panel.add(reqLabel);
		panel.add(separator2);
		panel.add(skArea);
		panel.add(ekArea);
		panel.add(keyLabel);
		panel.add(separator);
		panel.add(initPolyBtn);
		panel.add(importPolyBtn);
		panel.add(polyArea);
		panel.add(label);
		return panel;
	}
	public JPanel serverPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "连接服务器"));
		JLabel serverIP = new JLabel("服务器IP");
		serverIP.setBounds(290, 20, 65, 33);
		serverField = new JTextField("192.168.0.100");
		serverField.setHorizontalAlignment(JTextField.CENTER);
		serverField.setBounds(350, 20, 120, 28);
		serverField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));
		serverField.setBackground(Color.LIGHT_GRAY);
		
		JButton connBtn = new JButton("连接Ser");
		connBtn.setBounds(480, 20, 85, 28);
		connBtn.addActionListener(this);
		connBtn.setEnabled(false);
		JButton disConnBtn = new JButton("断开");
		
		disConnBtn.setBounds(570, 20, 60, 28);
		disConnBtn.addActionListener(this);
		
		JLabel ttpLabel = new JLabel("TTP IP");
		ttpLabel.setBounds(15, 20, 70, 33);
		JTextField ttpField = new JTextField("192.168.0.101");
		ttpField.setBounds(65, 20, 110, 28);
		
		ttpField.setHorizontalAlignment(JTextField.CENTER);
		ttpField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));
		ttpField.setBackground(Color.LIGHT_GRAY);
		
		
		JButton connTTPBtn = new JButton("连接TTP");
		connTTPBtn.setBounds(180, 20, 85, 28);
		connTTPBtn.setEnabled(false);
		JSeparator separator2 = new JSeparator(JSeparator.VERTICAL);
		separator2.setBounds(285, 9, 1, 47);
//		separator2.setBorder(BorderFactory.createLineBorder(2));
		
		panel.add(separator2);
		panel.add(ttpLabel);
		panel.add(ttpField);
		panel.add(connTTPBtn);
		panel.add(disConnBtn);
		panel.add(connBtn);
		panel.add(serverIP);
		panel.add(serverField);
		return panel;
	}
	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
		new Client();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("连接TTP")) {
			this.statusArea.append("连接可信第三方...\r\n获取公共参数信息...\r\ndone...\r\n");
		}else if(e.getActionCommand() == "连接Ser") {
			this.statusArea.append("连接目标服务器...\r\n");
		}else if(e.getActionCommand() == "随机生成") {
			this.statusArea.append("随机生成多项式...\r\n");
		}else if(e.getActionCommand() == "密钥生成") {
			this.statusArea.append("密钥生成...\r\n");
			this.statusArea.append("构建模幂运算方案公私钥信息...\r\n");
			this.statusArea.append("发送模幂运算请求...\r\n");
			this.statusArea.append("对模幂运算结果进行验证...\r\n");
			this.statusArea.append("验证通过...还原验证密钥信息...\r\ndone...\r\n");
		}else if(e.getActionCommand() == "发送请求") {
			this.statusArea.append("发送多项式求值计算请求...Eval At :" + this.reqField.getText() +"  \r\n" );
			this.statusArea.append("读取服务器返回结果...\r\n");
			this.statusArea.append("计算结果进行验证...\r\n验证通过...\r\ndone...\r\n");
		}
	}
}
