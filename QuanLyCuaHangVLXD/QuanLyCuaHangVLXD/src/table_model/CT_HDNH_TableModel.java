package table_model;

import entity.CT_HoaDonNhapHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CT_HDNH_TableModel extends AbstractTableModel {

    private List<CT_HoaDonNhapHang> ds;
    String[] headers = {"STT", "Tên Vật Liệu", "Loại Vật Liệu", "Xuất Xứ", "Số Lượng", "Đơn Vị Tính",
            "Đơn Giá", "Thành Tiền"};

    public CT_HDNH_TableModel(List<CT_HoaDonNhapHang> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
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
        CT_HoaDonNhapHang ct = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return ct.getVatLieu().getTenVatLieu();
            case 2:
                return ct.getVatLieu().getLoaiVatLieu().getTenLoai();
            case 3:
                return ct.getVatLieu().getXuatXu();
            case 4:
                return ct.getSoLuong();
            case 5:
                return ct.getVatLieu().getDonViTinh();
            case 6:
                return ct.getDonGia();
            case 7:
                return ct.getVatLieu().getGiaBan() * ct.getSoLuong();
            default:
                return ct;
        }
    }
}