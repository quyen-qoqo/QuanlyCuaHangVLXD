package ui;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;
import table_model.TaiKhoan_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoan_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen, lblMatKhau;
    JTextField txtMa,txtTen, txtMatKhau, txtMaNhanVien;
    private JLabel lblNhanVien;

    public TaiKhoan_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ TÀI KHOẢN");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,100));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã Tài Khoản: "));
        b1.add(txtMa = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Đăng Nhập:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMatKhau = new JLabel("Mật Khẩu: "));
        b2.add(txtMatKhau = new JTextField());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNhanVien = new JLabel("Mã Nhân Viên: "));
        b2.add(txtMaNhanVien = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblMatKhau.setPreferredSize(lblTen.getPreferredSize());
        lblNhanVien.setPreferredSize(lblTen.getPreferredSize());

        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Tài Khoản"));
        pnCenS.add(btnXoa = new JButton("Xóa Tài Khoản"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));

        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);


        txtMa.setEditable(false);
        //pnSouth
        pnSouth = new JPanel();
        List<ChucVu> ls = new ArrayList<>();
        TaiKhoan_DAO taiKhoan_dao = new TaiKhoan_DAO();
        TaiKhoan_TableModel model = new TaiKhoan_TableModel(taiKhoan_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,1).toString());
                    txtTen.setText(table.getValueAt(r,2).toString());
                    txtMatKhau.setText(table.getValueAt(r,3).toString());
                    txtMaNhanVien.setText(table.getValueAt(r,4).toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(850,300));

        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Tài Khoản"));

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtTen.requestFocus();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    String maX = txtMa.getText().trim();
                    if(taiKhoan_dao.deleteTK(maX)){
                        JOptionPane.showMessageDialog(null,"Xóa thành công !");
                        table.setModel(new TaiKhoan_TableModel(taiKhoan_dao.getLS()));
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean rs = false;
                for (NhanVien nhanVien: nhanVien_dao.getLS()) {
                    if (nhanVien.getMaNV().equals(txtMaNhanVien.getText().trim())) {
                        rs = true;
                    }
                }
                if(txtTen.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên loại sản phẩm!");
                    txtTen.requestFocus();
                } else if (!rs) {
                    JOptionPane.showMessageDialog(null,"Mã Nhân Viên Không Tồn Tại!");
                    txtMaNhanVien.requestFocus();
                }
                else{
                    TaiKhoan taiKhoan = new TaiKhoan(txtMa.getText().trim(),txtTen.getText().trim(), txtMatKhau.getText().trim());
                    NhanVien nhanVien = nhanVien_dao.TimKiemMa(txtMaNhanVien.getText().trim());
                    if (nhanVien != null) {
                        taiKhoan.setNhanVien(nhanVien);
                    }
                    if(taiKhoan_dao.addTaiKhoan(taiKhoan)){
                        table.setModel(new TaiKhoan_TableModel(taiKhoan_dao.getLS()));
                        txtTen.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null,"Lỗi!");
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                TaiKhoan taiKhoanSua = new TaiKhoan(txtMa.getText(),txtTen.getText(), txtMatKhau.getText().trim());
                NhanVien nhanVien = nhanVien_dao.TimKiemMa(txtMaNhanVien.getText().trim());
                if (nhanVien != null) {
                    taiKhoanSua.setNhanVien(nhanVien);
                }
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn sử thông tin?","Update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(taiKhoan_dao.updateTaiKhoan(table.getValueAt(r,0).toString(),taiKhoanSua)){
                            table.setModel(new TaiKhoan_TableModel(taiKhoan_dao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa");
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
}

