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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class VatLieu_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen, lblLoaiSanPham,lblXuatXu,lblSoLuong,lblGiaBan,lblDonViTinh, lblTrongLuong;
    JTextField txtMa,txtTen,txtXuatXu,txtSoLuong,txtGia, txtTrongLuong;
    JComboBox<String> cbcLoai,cbcDonVi;

    public VatLieu_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ VẬT LIỆU");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b,b1,b2,b3,b4,b5,b6;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,210));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Vật Liệu:"));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Vật Liệu:"));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblLoaiSanPham = new JLabel("Loại Vật Liệu: "));
        cbcLoai = new JComboBox<>();
        LoaiVatLieu_DAO loaiVatLieu_dao = new LoaiVatLieu_DAO();
        for (LoaiVatLieu loaiVatLieu: loaiVatLieu_dao.getLS()) {
            cbcLoai.addItem(loaiVatLieu.getTenLoai());
        }
        cbcLoai.setPreferredSize(new Dimension(323,20));
        b2.add(cbcLoai);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
        b2.add(txtXuatXu = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblSoLuong = new JLabel("Số Lượng: "));
        b4.add(txtSoLuong = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblGiaBan = new JLabel("Giá Bán:    "));
        b4.add(txtGia = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        cbcDonVi = new JComboBox<>();
        cbcDonVi.addItem("Hộp");
        cbcDonVi.addItem("Cái");
        cbcDonVi.addItem("Bao");
        cbcDonVi.setPreferredSize(new Dimension(323,20));
        b5.add(cbcDonVi);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblTrongLuong = new JLabel("Trọng Lượng: "));
        b5.add(txtTrongLuong = new JTextField(30));
        txtTrongLuong.setToolTipText("gram");
        b.add(Box.createVerticalStrut(10));


        lblMa.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblTen.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblXuatXu.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblSoLuong.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblTrongLuong.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblGiaBan.setPreferredSize(lblLoaiSanPham.getPreferredSize());


        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Vật Liệu"));
        pnCenS.add(btnXoa = new JButton("Xóa Vật Liệu"));
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
        List<VatLieu> ls = new ArrayList<>();
        VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
        VatLieu_TableModel model = new VatLieu_TableModel(vatLieu_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtTen.setText(table.getValueAt(r,1).toString());
                    cbcLoai.setSelectedItem(table.getValueAt(r,2).toString());
                    txtXuatXu.setText(table.getValueAt(r,6).toString());
                    txtSoLuong.setText(table.getValueAt(r,7).toString());
                    cbcDonVi.setSelectedItem(table.getValueAt(r, 8).toString());
                    txtGia.setText(table.getValueAt(r,9).toString());
                    txtTrongLuong.setText(table.getValueAt(r, 5).toString());
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
        sc.setPreferredSize(new Dimension(850,250));

        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Vật Liệu"));

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if (vatLieu_dao.deleteVatLieu(txtMa.getText().trim())) {
                            JOptionPane.showMessageDialog(null, "Xóa thành công!");
                            clearText();
                            table.setModel(new VatLieu_TableModel(vatLieu_dao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn sửa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        VatLieu vatLieuS = new VatLieu(txtMa.getText(), txtTen.getText(), Float.parseFloat(txtTrongLuong.getText().trim()), cbcDonVi.getSelectedItem().toString(),
                                txtXuatXu.getText(), Integer.parseInt(txtSoLuong.getText().trim()), Double.parseDouble(txtGia.getText().trim()));
                        LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                        vatLieuS.setLoaiVatLieu(loaiVatLieu);
                        if (vatLieu_dao.updateVatLieu(vatLieuS)) {
                            JOptionPane.showMessageDialog(null,"Sửa thành công!");
                            table.setModel(new VatLieu_TableModel(vatLieu_dao.getLS()));
                            clearText();
                        }
                    }
                }
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtTen.getText().trim().equals("")){
                    VatLieu vatLieu = new VatLieu(txtMa.getText(), txtTen.getText(), Float.parseFloat(txtTrongLuong.getText().trim()), cbcDonVi.getSelectedItem().toString(),
                            txtXuatXu.getText(), Integer.parseInt(txtSoLuong.getText().trim()), Double.parseDouble(txtGia.getText().trim()));
                    LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                    vatLieu.setLoaiVatLieu(loaiVatLieu);
                    if(vatLieu_dao.addVatLieu(vatLieu)){
                        table.setModel(new VatLieu_TableModel(vatLieu_dao.getLS()));
                        clearText();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên vật liệu!");
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtXuatXu.setText("");
        cbcDonVi.setSelectedIndex(0);
        cbcLoai.setSelectedIndex(0);
        txtTen.requestFocus();
    }
}

