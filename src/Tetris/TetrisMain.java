package Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class TetrisMain{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(null);
		LeftPanel leftPanel = new LeftPanel();
		GamePanel gamePanel = new GamePanel(frame,leftPanel);
		RightPabel rightPabel = new RightPabel(gamePanel);
		
		leftPanel.setBounds(0, 0, 180, 740);
		gamePanel.setBounds(180, 0, 360, 740);
		rightPabel.setBounds(540, 0, 260, 740);
		
		frame.add(leftPanel);
		frame.add(gamePanel);
		frame.add(rightPabel);
		
		frame.setVisible(true);
		//��ȡһ����ϵͳ��ع��������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//��ȡ��Ļ�ķֱ���
		Dimension d = toolkit.getScreenSize();
		int width = (int) d.getWidth();
		int height = (int)d.getHeight();
		frame.setBounds((width-800)/2, (height-739)/2, 815, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}