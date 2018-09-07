package component;

import elements.BaseElement;
import elements.LinkElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AbstractPage;
import utils.CollectionUtils;

import java.util.List;
import java.util.Properties;

public class TableManager extends AbstractPage {
    private By tableLocator;
    private static final By ROW_LOCATOR = By.tagName("tr");
    private static final By CELL_LOCATOR = By.tagName("td");

    public TableManager(By locator) {
        this.tableLocator = locator;
    }

    private WebElement getRow(int index) {
        logDebug("Getting row " + index);
        return getRows().get(--index);
    }

    private List<WebElement> getRows() {
        WebElement table = driver.findElement(tableLocator);
        List<WebElement> rows = table.findElements(ROW_LOCATOR);
        return rows;
    }

    public WebElement getCell(WebElement row, int index) {
        if (getCells(row).size() >= index) {
            return getCells(row).get(--index);
        } else {
            logDebug("Row doesn't exist with index " + index);
            return null;
        }
    }

    public WebElement getCell(int iRow, int iCol) {

        WebElement row = getRow(iRow);

        return getCell(row, iCol);
    }

    public String getCellText(int rowIndex, int colIndex) {
        logDebug("Getting cell text with index" + colIndex);

        WebElement row = getRow(rowIndex);
        return getCellText(getCell(row, colIndex));
    }

    public String getCellText(WebElement row, int index) {
        logDebug("Getting cell text with index " + index);
        return getCellText(getCell(row, index));
    }

    public BaseElement getCellLink(int iRow, int iCol, By locator)
    {
        WebElement cell =  getCell(iRow, iCol);
        return new LinkElement(locator, "cell", cell);
    }

    private List<WebElement> getCells(WebElement row) {
        List<WebElement> cells = row.findElements(CELL_LOCATOR);
        return cells;
    }

    public String getCellText(WebElement cell) {
        if (cell == null) {
            logDebug("Cell doesn't exist");
            return null;
        }
        String cellText = cell.getText().trim();
        logDebug("Cell text: " + cellText);
        return cellText;
    }

    public WebElement findRowWithCellText(int cellIndex, String text) {

        int index = findRowIndexWithCellText(cellIndex, text);

        if (index < 1) return null;

        return getRow(index);
    }

    public int findRowIndexWithCellText(int cellIndex, String text) {

        logDebug("Поиск строки с текстом " + text + " в колонке " + cellIndex);
        boolean notFound;
        List<WebElement> rows = getRows();

        for (int i = 0; i < rows.size(); i++) {

            notFound = false;
            logDebug("Проверка строки " + (i + 1));

            String cellText = getCellText(rows.get(i), cellIndex);
            logDebug("Получено содержимое ячейки (" + (i + 1) + ", " + cellIndex + "), значение:" + cellText);

            if(cellText != null){

                if (!cellText.equalsIgnoreCase(text)) {
                    notFound = true;
                }else{
                    notFound=false;
                }

            } else {
                notFound = true;
            }

            if (!notFound) {
                return i + 1;
            }
        }

        return -1;
    }

    public int getIndexOfRow(RowCondition condition, int startFromRow) {

        Object[] cellIndexes = condition.getAllConditions().keySet().toArray();
        Object[] cellValues = condition.getAllConditions().values().toArray();
        List<WebElement> rows = getRows();
        boolean notFound;

        for (int i = startFromRow - 1; i < rows.size(); i++) {

            notFound = false;
            logDebug("Проверка строки " + (i + 1));

            for (int k = 0; k < cellIndexes.length; k++) {

                String cellText = getCellText(rows.get(i), Integer.valueOf(cellIndexes[k].toString()));
                logDebug("Получено содержимое ячейки (" + i + ", " + Integer.valueOf(cellIndexes[k].toString()) + "), значение:" + cellText);

                if (cellText != null) {

                    if (cellValues[k] instanceof String[]) {
                        if (!CollectionUtils.contains((String[]) cellValues[k], cellText)) {
                            notFound = true;
                        }
                    } else {
                        if (!cellText.equalsIgnoreCase(cellValues[k].toString())) {
                            notFound = true;
                        }
                    }

                } else {
                    notFound = true;
                }

                if (notFound) break;
            }

            if (!notFound) {
                return i + 1;
            }

        }

        logDebug("Строка не найдена");

        return -1;
    }

    public int getIndexOfRow(RowCondition condition) {
        return getIndexOfRow(condition, 1);
    }

    public static RowCondition createCondition() {
        return new RowCondition();
    }

    public static class RowCondition {

        private Properties cells = new Properties();

        public void addCondition(int cellIndex, String cellValue) {
            cells.put(cellIndex, cellValue);
        }

        public void addCondition(int cellIndex, String[] cellValues) {
            cells.put(cellIndex, cellValues);
        }

        public Properties getAllConditions() {
            return cells;
        }

        public String toString() {
            return cells.toString();
        }
    }
}
