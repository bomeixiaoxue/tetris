package Tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPabel extends JPanel implements KeyListener,MouseListener{
	JButton startButton,overButton,pause_start_Button;
	JButton upButton,downButton,leftButton,rightButton;
	JLabel label1,label2;
	Font font,font2;
	GamePanel gamePanel;
	Color color;
	boolean flag = false;  //区别开始新游戏和暂停后的开始
	public RightPabel(GamePanel gamePanel){
		setLayout(null);
		setBackground(Color.black);
		this.gamePanel = gamePanel;
		font = new Font("黑体", Font.BOLD, 20);
		font2 = new Font("黑体", Font.BOLD, 15);
		color = new Color(34,49,63);
		
		startButton = new JButton("开始新游戏");
		overButton = new JButton("结束");
		pause_start_Button = new JButton("暂停/开始");
		upButton = new JButton("↑");
		downButton = new JButton("↓");
		leftButton = new JButton("←");
		rightButton = new JButton("→");
		label1 = new JLabel("↑:旋转   ←:向左 ");
		label2 = new JLabel("↓:向下   →:向右");
		
		
		startButton.setBounds(55, 50, 150, 40);
		overButton.setBounds(55, 120, 150, 40);
		pause_start_Button.setBounds(55, 190, 150, 40);
		upButton.setBounds(105, 400, 50, 50);
		downButton.setBounds(105, 470, 50, 50);
		leftButton.setBounds(35, 470, 50, 50);
		rightButton.setBounds(175, 470, 50, 50);
		label1.setBounds(60, 550, 200, 30);
		label2.setBounds(60, 600, 200, 30);
		
		startButton.setFont(font);
		overButton.setFont(font);
		pause_start_Button.setFont(font);
		upButton.setFont(font2);
		downButton.setFont(font2);
		leftButton.setFont(font2);
		rightButton.setFont(font2);
		label1.setFont(font2);
		label2.setFont(font2);
		
		startButton.setBackground(color);
		overButton.setBackground(color);
		pause_start_Button.setBackground(color);
		upButton.setBackground(color);
		downButton.setBackground(color);
		leftButton.setBackground(color);
		rightButton.setBackground(color);
		
		
		startButton.setForeground(Color.white);
		overButton.setForeground(Color.white);
		pause_start_Button.setForeground(Color.white);
		upButton.setForeground(Color.white);
		downButton.setForeground(Color.white);
		leftButton.setForeground(Color.white);
		rightButton.setForeground(Color.white);
		label1.setForeground(Color.white);
		label2.setForeground(Color.white);
		
		
		
		
		startButton.setFocusPainted(false);
		overButton.setFocusPainted(false);
		pause_start_Button.setFocusPainted(false);
		upButton.setFocusPainted(false);
		downButton.setFocusPainted(false);
		leftButton.setFocusPainted(false);
		rightButton.setFocusPainted(false);
		
		add(startButton);
		add(overButton);
		add(pause_start_Button);
		add(upButton);
		add(downButton);
		add(leftButton);
		add(rightButton);
		add(label1);
		add(label2);
		
		startButton.addKeyListener(this);
		overButton.addKeyListener(this);
		pause_start_Button.addKeyListener(this);
		upButton.addKeyListener(this);
		downButton.addKeyListener(this);
		leftButton.addKeyListener(this);
		rightButton.addKeyListener(this);
		
		startButton.addMouseListener(this);
		overButton.addMouseListener(this);
		pause_start_Button.addMouseListener(this);
		upButton.addMouseListener(this);
		downButton.addMouseListener(this);
		leftButton.addMouseListener(this);
		rightButton.addMouseListener(this);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		//因为按钮会获取焦点使得gamepanel无法获得焦点，从而失去键盘事件
		gamePanel.keyPressed(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == startButton){
			gamePanel.newGame();
			gamePanel.repaint();
			flag = false;
		}
		if(e.getSource() == overButton){
			gamePanel.overGame();
			gamePanel.repaint();
			flag = false;
		}
		if(e.getSource() == pause_start_Button){
			if(!gamePanel.timer.isRunning() && flag){
				gamePanel.timer.start();
				flag = false;
			}else{
				gamePanel.timer.stop();
				flag = true;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		JButton button = (JButton) e.getSource();
		button.setBackground(new Color(52,73,94));
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		JButton button = (JButton) e.getSource();
		button.setBackground(color);
	}
	
}
