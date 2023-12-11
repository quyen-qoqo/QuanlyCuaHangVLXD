package ui;

import dao.NhanVien_DAO;
import entity.NhanVien;
import table_model.NV_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TimKiemNV_Form extends JPanel {
    JPanel pnNorth,pnCenter;
    JTextField txtTenKh,txtSDT;
    JRadioButton rdTenKH,rdSDT;
    ButtonGroup btnGR;
    JButton btnTim,btnThoat;
    public TimKiemNV_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN NHÂN VIÊN");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700,130));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdTenKH = new JRadioButton("Họ Tên Nhân Viên"));
        b1.add(txtTenKh = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdSDT = new JRadioButton("SĐT Nhân Viên"));
        b1.add(txtSDT = new JTextField());
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
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Nhân Viên Theo"));


        //pnCenter
        pnCenter = new JPanel();
        List<NhanVien> ls = new ArrayList<>();
        NV_TableModel model = new NV_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(850,300));
        pnCenter.add(sc);
        pnCenter.setBorder(new TitledBorder("Kết Quả Tìm Kiếm"));

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rdTenKH.isSelected()){
                    if(!txtTenKh.getText().trim().equals("")){
                        NhanVien_DAO nvDao =new NhanVien_DAO();
                        if(nvDao.TimKiemTen(txtTenKh.getText().trim()) != null){
                            List<NhanVien> ls = new ArrayList<>();
                            ls.add(nvDao.TimKiemTen(txtTenKh.getText().trim()));
                            table.setModel(new NV_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new NV_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }else{
                    if(!txtSDT.getText().trim().equals("")){
                        NhanVien_DAO nvDao =new NhanVien_DAO();
                        if(nvDao.TimKiemSDT1(txtSDT.getText().trim()) != null){
                            List<NhanVien> ls = new ArrayList<>();
                            ls.add(nvDao.TimKiemSDT1(txtSDT.getText().trim()));
                            table.setModel(new NV_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new NV_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }
            }
        });


        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}
