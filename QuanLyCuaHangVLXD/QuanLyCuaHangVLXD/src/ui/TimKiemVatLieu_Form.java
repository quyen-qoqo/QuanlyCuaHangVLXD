package ui;

import dao.LoaiVatLieu_DAO;
import dao.VatLieu_DAO;
import entity.LoaiVatLieu;
import entity.VatLieu;
import table_model.VatLieu_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TimKiemVatLieu_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -343354033986428930L;
	JPanel pnNorth,pnCenter;
    JTextField txtTenVL;
    JRadioButton rdTenKH,rdSDT;
    ButtonGroup btnGR;
    JButton btnTim,btnThoat;
    JComboBox cbcLoai;
    public TimKiemVatLieu_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN VẬT LIỆU");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700,130));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdTenKH = new JRadioButton("Tên Vật Liệu"));
        b1.add(txtTenVL = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdSDT = new JRadioButton("Chọn Loại Vật Liệu"));
        b1.add(cbcLoai = new JComboBox<>());
        LoaiVatLieu_DAO loaiVatLieu_dao = new LoaiVatLieu_DAO();
        for (LoaiVatLieu lt: loaiVatLieu_dao.getLS()) {
            cbcLoai.addItem(lt.getTenLoai());
        }
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnTim = new JButton("Tìm Kiếm"));
        b2.add(Box.createHorizontalStrut(100));
        b2.add(btnThoat = new JButton("Thoát"));
        b.add(Box.createVerticalStrut(60));

        btnGR = new ButtonGroup();
        btnGR.add(rdTenKH);
        btnGR.add(rdSDT);
        pnNorth_C.add(b);

        pnNorth.setLayout(new BorderLayout());
        pnNorth.add(pnNorth_N,BorderLayout.NORTH);
        pnNorth.add(pnNorth_C,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Vật Liệu Theo"));


        //pnCenter
        pnCenter = new JPanel();
        VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
        List<VatLieu> ls = new ArrayList<>();
        VatLieu_TableModel model = new VatLieu_TableModel(vatLieu_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(900,300));
        pnCenter.add(sc);
        pnCenter.setBorder(new TitledBorder("Kết Quả Tìm Kiếm"));

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rdTenKH.isSelected()){
                    if(!txtTenVL.getText().trim().equals("")){
                        if(vatLieu_dao.TimKiemTen(txtTenVL.getText().trim()) != null){
                            List<VatLieu> ls = new ArrayList<>();
                            ls.add(vatLieu_dao.TimKiemTen(txtTenVL.getText().trim()));
                            table.setModel(new VatLieu_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new VatLieu_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }else{
                    if(!cbcLoai.getSelectedItem().toString().trim().equals("")){
                        LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                        String maL = loaiVatLieu.getMaLoai();
                        if(vatLieu_dao.TimKiemMaL(maL) != null){
                            table.setModel(new VatLieu_TableModel(vatLieu_dao.TimKiemMaL(maL)));
                        }
                    }
                }
            }
        });


        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}
