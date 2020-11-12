package com.role;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TrustThirdParty extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2898912693065886015L;

	private JTextArea ppJta;
	private JTextArea statusJta;

	private JButton genBtn;
	public TrustThirdParty() {
		this.setLayout(null);
		JPanel paramPanel = initParamPanel();
		JPanel statusPanel= initClientPanel();
		paramPanel.setBounds(2, 2, 660, 150);
		statusPanel.setBounds(2, 150, 660, 240);
		this.add(paramPanel);
		this.add(statusPanel);
		this.setTitle("TTP");
		this.setLocation(800, 400);
		this.setSize(680, 430);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
	}
	public JPanel initParamPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("公共参数生成"));
		JLabel label = new JLabel("公共参数");
		label.setLocation(20, 30);
		label.setSize(75,20);
		
		ppJta = new JTextArea();
		ppJta.setSize(450, 100);
		ppJta.setLineWrap(true);// 激活自动换行功能
		ppJta.setWrapStyleWord(true);// 激活断行不断字功能
		ppJta.setBackground(Color.LIGHT_GRAY);
		ppJta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		ppJta.setLocation(90, 30);
		genBtn = new JButton("生成参数");
		genBtn.addActionListener(this);
		genBtn.setSize(80, 27);
		genBtn.setLocation(560, 105);
		panel.add(label);
		panel.add(ppJta);
		panel.add(genBtn);
		return panel;
	}
	public JPanel initClientPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("用户和服务器"));
//		String data[] = {"192.168.0.100","127.0.0.1","192.168.0.101"};
		String data[] = new String[0];
		JList<String> myList = new JList<String>(data);
		myList.setBorder(BorderFactory.createTitledBorder("用户和服务器列表"));
		myList.setSize(200,200);
		myList.setLocation(10, 30);
		myList.setBackground(Color.LIGHT_GRAY);
		statusJta = new JTextArea();
		statusJta.setSize(400, 200);
		statusJta.setLineWrap(true);// 激活自动换行功能
		statusJta.setWrapStyleWord(true);// 激活断行不断字功能
		statusJta.setBackground(Color.LIGHT_GRAY);
		statusJta.setBorder(BorderFactory.createTitledBorder("状态栏"));
		statusJta.setLocation(230, 30);
		
		panel.add(myList);
		panel.add(statusJta);
		return panel;
	}
	public static void main(String[] args) {
		new TrustThirdParty();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(genBtn)) {
			statusJta.setText("读取JPBC配置文件...\r\n创建大整数群...\r\n设置双线性映射...\r\n导出公共参数信息...\r\ndone...");
			ppJta.setText("Order: 3064991081731777546575510593831386635550174528483098623\r\n" + 
					"g: 61041999158664171800197414883347538527885873499581094962508254598771095186828718986606120258194315644092420775464199033771865191775457868810960341708355000257103254603398611356298822,68346109771694933240703583549640120208703085413895299403121378044249728364870793108491373608567929376936834174598622574344045505325969396753006978625143835636738027636814508304299427,0\r\n" + 
					"u: 61839752748444518920930447349975145818089953621567489916591835273912753930668140396424372621015140062874175628100717884137468861164448777717615232210025997848149657883786848893269329,25717527178738426426295048647765985501718286430119325004463183681055216188228874912804175293750377832399778710339147374785452613654876075743693388264717563672173571838454053138876516,0\r\n" + 
					"pairing degree : 2\r\n" + 
					"lemada : 1234567890123");
		}
	}
}
