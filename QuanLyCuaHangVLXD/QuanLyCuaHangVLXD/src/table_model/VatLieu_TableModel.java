package table_model;

import entity.VatLieu;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VatLieu_TableModel extends AbstractTableModel {

    private List<VatLieu> ds;
    String[] headers = {"Mã Vật Liệu", "Tên Vật Liệu", "Loại Vật Liệu", "Trọng lượng", "Xuất Xứ", "Số Lượng", "Đơn Vị Tính",
            "Đơn Giá"};

    public VatLieu_TableModel(List<VatLieu> ds) {
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
        VatLieu t = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getMaVatLieu();
            case 1:
                return t.getTenVatLieu();
            case 2:
                return t.getLoaiVatLieu().getTenLoai();
            case 3:
                return t.getTrongLuong();
            case 4:
                return t.getXuatXu();
            case 5:
                return t.getSoLuong();
            case 6:
                return t.getDonViTinh();
            case 7:
                return t.getGiaBan();
            default:
                return t;
        }
    }
}