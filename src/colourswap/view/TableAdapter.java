package colourswap.view;

import colourswap.model.Game;
import colourswap.model.GameUpdateListener;
import colourswap.model.shapes.Shape;

import javax.swing.table.AbstractTableModel;

/**
 * An adapter which allows a Game model to be rendered using a JTable Swing
 * component. TableAdapter implements (indirectly) Swing's TableModel
 * interface and observes updates from the Game model.
 */
public class TableAdapter extends AbstractTableModel implements GameUpdateListener {

    /* Ordered column header names. */
    private static String[] columnNames = { "name", "x", "y", "colour", "score" };

    /* The adaptee Game model */
    private Game _adaptee;

    /**
     * Constructs a TableAdapter object.
     *
     * @param game the Game model to adapt
     */
    public TableAdapter(Game game) {
        _adaptee = game;
        _adaptee.addGameListener(this);
    }

    /**
     * Returns the number of columns in the TableAdapter.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the number of rows, i.e. the number of Shape objects.
     */
    @Override
    public int getRowCount() {
        return _adaptee.shapes().size();
    }

    /**
     * Returns the value at a specified row and column.
     *
     * @param row the index of the shape
     * @param col the index of the attribute
     */
    @Override
    public Object getValueAt(int row, int col) {
        Shape shape = _adaptee.shapes().get(row);
        Object value = null;

        switch (col) {
            case 0: // name
                value = shape.getClass().getSimpleName();
                break;
            case 1: // x
                value = shape.getX();
                break;
            case 2: // y
                value = shape.getY();
                break;
            case 3: // colour
                value = shape.getColour();
                break;
            case 4: // score
                value = shape.getScore();
                break;
        }
        return value;
    }

    /**
     * Returns the column name of a particular column.
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    /**
     * Notifies the table when the Game model changes.
     **/
    @Override
    public void gameStateUpdated() {
        fireTableDataChanged();
    }
}
