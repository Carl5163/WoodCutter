import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogLength;
	private JLabel lblCutLength1;
	private JLabel lblCutLength2;
	private JTextField tfLength1;
	private JTextField tfLength2;
	private JLabel lblNumLength1;
	private JLabel lblNumLength2;
	private JLabel lblWasted;
	private JTextField tfNumLength1;
	private JTextField tfNumLength2;
	private JTextField tfNumLength3;
	
	public MainFrame() {
		
		layoutFrame();
		setVisible(true);
		
	}	
	
	private void layoutFrame() {
		setTitle("Wood Cutter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlMain = new JPanel();
		contentPane.add(pnlMain, BorderLayout.CENTER);
		
		JLabel lblLogLength = new JLabel("Length of Log");
		lblLogLength.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfLogLength = new JTextField();
		tfLogLength.setText("0");
		tfLogLength.setColumns(10);
		
		lblCutLength1 = new JLabel("Cut Length 1");
		lblCutLength1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCutLength2 = new JLabel("Cut Length 2");
		lblCutLength2.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfLength1 = new JTextField();
		tfLength1.setText("0");
		tfLength1.setColumns(10);
		
		tfLength2 = new JTextField();
		tfLength2.setText("0");
		tfLength2.setColumns(10);
		
		lblNumLength1 = new JLabel("Number of (0) Inch Cuts");
		lblNumLength1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNumLength2 = new JLabel("Number of (0) Inch Cuts");
		lblNumLength2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblWasted = new JLabel("Wasted");
		lblWasted.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumLength1 = new JTextField();
		tfNumLength1.setText("0");
		tfNumLength1.setEditable(false);
		tfNumLength1.setColumns(10);
		
		tfNumLength2 = new JTextField();
		tfNumLength2.setText("0");
		tfNumLength2.setEditable(false);
		tfNumLength2.setColumns(10);
		
		tfNumLength3 = new JTextField();
		tfNumLength3.setText("0");
		tfNumLength3.setEditable(false);
		tfNumLength3.setColumns(10);
		GroupLayout glMain = new GroupLayout(pnlMain);
		glMain.setHorizontalGroup(
			glMain.createParallelGroup(Alignment.LEADING)
				.addGroup(glMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(glMain.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogLength, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(tfLogLength, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(glMain.createSequentialGroup()
							.addGroup(glMain.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCutLength1, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
								.addComponent(tfLength1, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(glMain.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfLength2, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(lblCutLength2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
						.addGroup(glMain.createSequentialGroup()
							.addGroup(glMain.createParallelGroup(Alignment.LEADING)
								.addComponent(tfNumLength1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(lblNumLength1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(glMain.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumLength2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tfNumLength2, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(glMain.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWasted, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(tfNumLength3, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
					.addContainerGap())
		);
		glMain.setVerticalGroup(
			glMain.createParallelGroup(Alignment.LEADING)
				.addGroup(glMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogLength)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfLogLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCutLength1)
						.addComponent(lblCutLength2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfLength1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLength2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumLength1)
						.addComponent(lblNumLength2)
						.addComponent(lblWasted))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNumLength1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfNumLength2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfNumLength3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		pnlMain.setLayout(glMain);
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
