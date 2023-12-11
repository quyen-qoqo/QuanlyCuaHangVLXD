package table_model;

import entity.TaiKhoan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaiKhoan_TableModel extends AbstractTableModel {

    private List<TaiKhoan> ds;
    String [] headers = {"STT","Mã Tài Khoản", "Tên Đăng Nhập", "Mật Khẩu", "Mã Nhân Viên"};

    public TaiKhoan_TableModel(List<TaiKhoan> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
        return headers[column];
    }
    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TaiKhoan taiKhoan = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return taiKhoan.getMaTaiKhoan();
            case 2:
                return taiKhoan.getTenDangNhap();
            case 3:
                return taiKhoan.getMatKhau();
            case 4:
                return taiKhoan.getNhanVien().getMaNV();
            default:
                return taiKhoan;
        }
    }
}



