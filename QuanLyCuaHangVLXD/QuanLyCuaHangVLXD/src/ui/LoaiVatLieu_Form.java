package ui;

import dao.LoaiVatLieu_DAO;
import entity.LoaiVatLieu;
import table_model.LoaiVatLieu_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class LoaiVatLieu_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen;
    JTextField txtMa,txtTen;
    public LoaiVatLieu_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ LOẠI VẬT LIỆU");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        Box b,b1;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,70));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã Loại Vật Liệu: "));
        b1.add(txtMa = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Loại Vật Liệu:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());

        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Loại VL"));
        pnCenS.add(btnXoa = new JButton("Xóa Loại VL"));
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
        List<LoaiVatLieu> ls = new ArrayList<>();
        LoaiVatLieu_DAO loaiVatLieu_dao = new LoaiVatLieu_DAO();
        LoaiVatLieu_TableModel model = new LoaiVatLieu_TableModel(loaiVatLieu_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,1).toString());
                    txtTen.setText(table.getValueAt(r,2).toString());
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Loại Vật Liệu"));

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
                    if(loaiVatLieu_dao.deleteLVL(maX)){
                        JOptionPane.showMessageDialog(null,"Xóa thành công !");
                        table.setModel(new LoaiVatLieu_TableModel(loaiVatLieu_dao.getLS()));
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTen.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên loại sản phẩm!");
                    txtTen.requestFocus();
                }else{
                    LoaiVatLieu loaiVatLieu = new LoaiVatLieu(txtMa.getText().trim(),txtTen.getText().trim());
                    if(loaiVatLieu_dao.addLoaiVatLieu(loaiVatLieu)){
                        table.setModel(new LoaiVatLieu_TableModel(loaiVatLieu_dao.getLS()));
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
                LoaiVatLieu loaiVatLieu = new LoaiVatLieu(txtMa.getText(),txtTen.getText());
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn sử thông tin?","Update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(loaiVatLieu_dao.updateLoaiVatLieu(table.getValueAt(r,0).toString(),loaiVatLieu)){
                            table.setModel(new LoaiVatLieu_TableModel(loaiVatLieu_dao.getLS()));
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


