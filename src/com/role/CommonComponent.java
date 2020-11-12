package com.role;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommonComponent extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1568932424082681555L;

	public CommonComponent() {
        // 菜单条
        JMenuBar bar = new JMenuBar();
        // 菜单
        JMenu menu = new JMenu("文件");
        // 菜单选项
        JMenuItem myNew = new JMenuItem("新建");
        JMenuItem myOpen = new JMenuItem("打开");
        bar.add(menu);
        menu.add(myNew);
        menu.add(myOpen);
        add(bar, BorderLayout.NORTH);

        // 面板
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 2, 5, 5));
        add(p1);

        // 标签
        JLabel name = new JLabel("用户名:");
        p1.add(name);

        // 文本域
        JTextField field = new JTextField(8);
        p1.add(field);

        // 标签
        JLabel passwd = new JLabel("密码");
        p1.add(passwd);
        // 密码域
        JPasswordField pass = new JPasswordField(8);
        p1.add(pass);

        JPanel p2 = new JPanel();
        // 单选按钮
        JLabel gender = new JLabel("性别");
        p2.add(gender);
        JRadioButton male = new JRadioButton("男");
        JRadioButton female = new JRadioButton("女");
        // 单选按钮组,同一个单选按钮组的互斥.
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        // 注意,单选按钮组不能添加进容器
        p2.add(male);
        p2.add(female);

        JPanel p3 = new JPanel();
        // 复选框
        JLabel like = new JLabel("爱好:");
        p3.add(like);
        JCheckBox eat = new JCheckBox("吃饭");
        JCheckBox movie = new JCheckBox("看电影");
        JCheckBox sleep = new JCheckBox("睡觉");
        p3.add(eat);
        p3.add(movie);
        p3.add(sleep);

        JPanel p4 = new JPanel(new GridLayout(1, 0, 5, 5));
        // 文本域
        JLabel info = new JLabel("个人简介:");
        p4.add(info);
        JTextArea area = new JTextArea(50, 10);
        p4.add(area);

        JPanel p5 = new JPanel(new BorderLayout());
        // 列表
        String[] data = { "one", "two", "three" };
        JList list = new JList(data);
        p5.add(list, BorderLayout.WEST);

        JPanel p6 = new JPanel();
        // 普通按钮
        JButton button = new JButton("注册");
        p6.add(button);
        JButton button2 = new JButton("取消");
        p6.add(button2);

        setLayout(new GridLayout(7, 1, 5, 5));
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

    }
}