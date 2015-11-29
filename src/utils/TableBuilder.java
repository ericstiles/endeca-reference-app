package utils;
 
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
 
public class TableBuilder
{
	public TableBuilder(){}
	public TableBuilder(int spacing){
		this.spacing = returnSpaceString(spacing);
	}
	String spacing = "";
    List<String[]> rows = new LinkedList<String[]>();
 
    public void addRow(String... cols)
    {
        rows.add(cols);
    }
 
    private int[] colWidths()
    {
        int cols = -1;
 
        for(String[] row : rows)
            cols = Math.max(cols, row.length);
 
        int[] widths = new int[cols];
 
        for(String[] row : rows) {
            for(int colNum = 0; colNum < row.length; colNum++) {
                widths[colNum] =
                    Math.max(
                        widths[colNum],
                        StringUtils.length(row[colNum]));
            }
        }
 
        return widths;
    }
 
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
 
        int[] colWidths = colWidths();

        buf.append(spacing);      
        border(colWidths, buf);
 
        for(String[] row : rows) {
        	buf.append(spacing);
        	buf.append("| ");
            for(int colNum = 0; colNum < row.length; colNum++) {
                buf.append(
                    StringUtils.rightPad(
                        StringUtils.defaultString(
                            row[colNum]), colWidths[colNum]));
                if (colNum < (row.length -1)) buf.append(" | ");
            }
        	buf.append(" |");
            buf.append('\n');
        }
        buf.append(spacing);
        border(colWidths, buf);
        return buf.toString();
    }
	private void border(int[] colWidths, StringBuilder buf) {
		//buf.append("--");
		for (int i = 0; i < colWidths.length; i++) {
			buf.append(returnCharString(colWidths[i], '-'));
			//for separator
			//if (i > 0){
				buf.append("---");
			//}
		}
		buf.append("-");
		buf.append('\n');
		
	}
	public static String returnSpaceString(int space){
		String spacing = "";
		for (int i = 0; i < space; i++){
			spacing = spacing + " ";
		}
		return spacing;
	}
	public static String returnCharString(int space, char c){
		String spacing = "";
		for (int i = 0; i < space; i++){
			spacing = spacing + c;
		}
		return spacing;
	}
}