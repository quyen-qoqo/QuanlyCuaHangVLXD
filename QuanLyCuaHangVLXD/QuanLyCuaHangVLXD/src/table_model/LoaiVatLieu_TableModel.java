package table_model;

import entity.LoaiVatLieu;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LoaiVatLieu_TableModel extends AbstractTableModel {

    private List<LoaiVatLieu> ds;
    String [] headers = {"STT","Mã Loại", "Tên Loại Vật Liệu"};

    public LoaiVatLieu_TableModel(List<LoaiVatLieu> ds){
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
        LoaiVatLieu lt = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return lt.getMaLoai();
            case 2:
                return lt.getTenLoai();
            default:
                return lt;
        }
    }
}

