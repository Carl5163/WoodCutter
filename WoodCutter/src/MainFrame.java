import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.border.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements DocumentListener{

	private JPanel contentPane;
	private JTextField tfLogLength;
	private JTextField tfLength1;
	private JTextField tfLength2;
	private JTextField tfNumLength1;
	private JTextField tfNumLength2;
	private JTextField tfNumLength3;
	private JLabel lblLogLength;
	private JLabel lblLength1;
	private JLabel lblLength2;
	private JLabel lblNumLength1;
	private JLabel lblNumLength2;
	private JLabel lblNumLength3;
	
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
		
		lblLogLength = new JLabel("Length of Log");
		lblLogLength.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfLogLength = new JTextField();
		tfLogLength.setText("0");
		tfLogLength.setColumns(10);
		tfLogLength.getDocument().addDocumentListener(this);
		
		lblLength1 = new JLabel("Cut Length 1");
		lblLength1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblLength2 = new JLabel("Cut Length 2");
		lblLength2.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfLength1 = new JTextField();
		tfLength1.setText("0");
		tfLength1.setColumns(10);
		tfLength1.getDocument().addDocumentListener(this);
		
		tfLength2 = new JTextField();
		tfLength2.setText("0");
		tfLength2.setColumns(10);
		tfLength2.getDocument().addDocumentListener(this);
		
		lblNumLength1 = new JLabel("Number of (0) Inch Cuts");
		lblNumLength1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNumLength2 = new JLabel("Number of (0) Inch Cuts");
		lblNumLength2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNumLength3 = new JLabel("Wasted");
		lblNumLength3.setHorizontalAlignment(SwingConstants.CENTER);
		
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
								.addComponent(lblLength1, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
								.addComponent(tfLength1, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(glMain.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfLength2, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(lblLength2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
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
								.addComponent(lblNumLength3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
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
						.addComponent(lblLength1)
						.addComponent(lblLength2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfLength1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLength2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumLength1)
						.addComponent(lblNumLength2)
						.addComponent(lblNumLength3))
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


	@Override
	public void insertUpdate(DocumentEvent arg0) {

		process();		
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
		process();		
		
	}
	
	private void process() {
		
		double logLength = 0;
		double cut1 = 0;
		double cut2 = 0;
		boolean bad = false;
		
		tfNumLength1.setText("0");
		tfNumLength2.setText("0");
		tfNumLength3.setText("0");
		
		try {
			logLength = Double.parseDouble(tfLogLength.getText().trim());
			cut1 = Double.parseDouble(tfLength1.getText().trim());
			cut2 = Double.parseDouble(tfLength2.getText().trim());
		} catch (NumberFormatException e) {
			bad = true;
		}
		if(cut1 < 0 || cut2 < 0) {
			bad = true;
		}
		if(!bad && cut1 == 0 && cut2 == 0) {
			tfNumLength1.setText(""+0);
			tfNumLength2.setText(""+0);
			tfNumLength3.setText(""+logLength);
		} else if(!bad && (cut1 == 0 || cut2 == 0)) {
			
		} else if(!bad) {
			//Main Processing
			
			double len1, len2;
			double waste = Double.MAX_VALUE;
			boolean swapped = false;
			len1 = Math.min(cut1, cut2);
			len2 = Math.max(cut1, cut2);
			if(len1 != cut1 || len2 != cut2) {
				swapped = true;
			}
			int startVal = (int) Math.floor(logLength / len1);
			int n[] = {0,0};
			int m[] = {startVal, startVal};
			double wasteTemp = 0;
			while(m[0] >= 0) {
				wasteTemp = logLength - (m[0] * len1 + n[0] * len2);
				if(wasteTemp < waste) {
					m[1] = m[0];
					n[1] = n[0];
					waste = wasteTemp;
				}
				m[0]--;
				n[0]++;
			}
			if(swapped) {
				tfNumLength1.setText(""+n[1]);
				tfNumLength2.setText(""+m[1]);
			} else {
				tfNumLength1.setText(""+m[1]);
				tfNumLength2.setText(""+n[1]);			
			}
			tfNumLength3.setText(""+waste);
				
		}
		
		//Update Label colors to indicate invalid data.
		if(bad) {
			lblLogLength.setForeground(Color.RED);
			lblLength1.setForeground(Color.RED);
			lblLength2.setForeground(Color.RED);
			lblNumLength1.setForeground(Color.RED);
			lblNumLength2.setForeground(Color.RED);
			lblNumLength3.setForeground(Color.RED);
		} else {
			lblLogLength.setForeground(Color.BLACK);
			lblLength1.setForeground(Color.BLACK);
			lblLength2.setForeground(Color.BLACK);
			lblNumLength1.setForeground(Color.BLACK);
			lblNumLength2.setForeground(Color.BLACK);
			lblNumLength3.setForeground(Color.BLACK);
		}
	}
	
	private boolean doubleEquals(double arg1, double arg2) {
		return Math.abs(arg1-arg2) < 0.00005; 
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {}
}
