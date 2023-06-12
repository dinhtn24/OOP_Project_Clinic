package com.oop2023nlu.group1.view.panel.sub;

import com.oop2023nlu.group1.custom.TableCustom;
import com.oop2023nlu.group1.main.Main;
import com.oop2023nlu.group1.model.Clinic;
import com.oop2023nlu.group1.model.Medicine;
import com.oop2023nlu.group1.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Vector;

public class PnItemPrescriptions extends JPanel implements Observer {
	private JPanel pnMain, pnTitle, pnTable, pnSearch, pnContent, pnButton;
	private JLabel lbSearch, lbName, lbPhone, lbAddress, lbYearOfBirth;
	private JTextArea taPrescriptions;
	private JTextField tfSearch;
	private DefaultTableModel dtmPrescription;
	private JTable tbPrescription;
	private JButton btnBack, btnSave, btnSelected;
	private Clinic clinic;

	public PnItemPrescriptions() {
		Main.changLNF("Windows");
		addControls();
	}

	private void addControls() {
		Font font = new Font("Tahoma", Font.PLAIN, 20);
		/*
		 * ======================= PANEL CARD =======================
		 */
		this.setLayout(new BorderLayout());
		pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		this.add(pnMain, BorderLayout.CENTER);
		/*
		 * ======================= PANEL TITLE =======================
		 */
		pnTitle = new JPanel();
		pnMain.add(pnTitle);
		pnTitle.add(new JLabel("<html><h1>TOA THUỐC</h1></html>"), BorderLayout.CENTER);
		/*
		 * ======================= PANEL CONTENT =======================
		 */
		pnContent = new JPanel();
		pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.Y_AXIS));
		lbName = new JLabel("Nguyen");
		lbYearOfBirth = new JLabel();
		lbPhone = new JLabel();
		lbAddress = new JLabel();

		lbName.setFont(font);
		lbYearOfBirth.setFont(font);
		lbPhone.setFont(font);
		lbAddress.setFont(font);

		taPrescriptions = new JTextArea(4, 24);
		taPrescriptions.setEditable(false);
		taPrescriptions.setFont(font);

		Dimension lbSize = lbName.getPreferredSize();
		lbName.setPreferredSize(lbSize);
		lbYearOfBirth.setPreferredSize(lbSize);
		lbPhone.setPreferredSize(lbSize);
		lbAddress.setPreferredSize(lbSize);

		pnContent.add(lbName);
		pnContent.add(lbYearOfBirth);
		pnContent.add(lbPhone);
		pnContent.add(lbAddress);
		pnContent.add(taPrescriptions);
		pnMain.add(pnContent);
		/*
		 * ======================= PANEL BUTTON =======================
		 */
		pnButton = new JPanel();
		btnBack = new JButton("Quay lại");
		btnSave = new JButton("Xuất");

		Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
		btnBack.setFont(fontButton);
		btnSave.setFont(fontButton);
		pnButton.add(btnBack);
		pnButton.add(btnSave);
		pnMain.add(pnButton);

		pnSearch = new JPanel();
		lbSearch = new JLabel("Tìm kiếm");
		lbSearch.setFont(font);
		tfSearch = new JTextField(20);
		tfSearch.setFont(font);
		btnSelected = new JButton("Lấy thuốc");
		btnSelected.setFont(font);
		pnSearch.add(lbSearch);
		pnSearch.add(tfSearch);
		pnSearch.add(btnSelected);
		pnMain.add(pnSearch);
		/*
		 * ======================= PANEL TABLE =======================
		 */
		pnTable = new JPanel();
		pnTable.setLayout(new BorderLayout());
		pnTable.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		pnMain.add(pnTable);

		dtmPrescription = new DefaultTableModel();
		dtmPrescription.addColumn("Mã số");
		dtmPrescription.addColumn("Tên thuốc");
		dtmPrescription.addColumn("Liều dùng");

		tbPrescription = new JTable();
		tbPrescription = new TableCustom(dtmPrescription);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		tbPrescription.getColumnModel().getColumn(0).setCellRenderer(renderer);
		tbPrescription.getColumnModel().getColumn(1).setCellRenderer(renderer);
		tbPrescription.getColumnModel().getColumn(2).setCellRenderer(renderer);

		TableColumnModel columnModelBanHang = tbPrescription.getColumnModel();
		columnModelBanHang.getColumn(0).setPreferredWidth(60);
		columnModelBanHang.getColumn(1).setPreferredWidth(200);
		columnModelBanHang.getColumn(2).setPreferredWidth(200);

		JScrollPane scrTblSanPham = new JScrollPane(tbPrescription);
		pnTable.add(scrTblSanPham, BorderLayout.CENTER);
		pnMain.add(pnTable);
	}

	@Override
	public void update() {
		dtmPrescription.setRowCount(0);
		for (Medicine medicine : clinic.getMedicines()) {
			Vector<Object> vec = new Vector<>();
			vec.add(medicine.getMedicineID());
			vec.add(medicine.getName());
			vec.add(medicine.getUnit());
			dtmPrescription.addRow(vec);
		}
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public JLabel getLbName() {
		return lbName;
	}

	public JLabel getLbPhone() {
		return lbPhone;
	}

	public JLabel getLbAddress() {
		return lbAddress;
	}

	public JLabel getLbYearOfBirth() {
		return lbYearOfBirth;
	}

}
