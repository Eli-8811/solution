package com.core.solution.bussines;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.core.solution.access.AforeRepository;
import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.ExcelBody;
import com.core.solution.model.ExcelHeader;
import com.core.solution.model.ExcelItem;
import com.core.solution.model.entity.EntityConfigTable;
import com.core.solution.model.payload.ReqAforeUpdate;
import com.core.solution.model.payload.ReqConfigParent;
import com.core.solution.model.payload.ReqConfigTable;
import com.core.solution.model.payload.ReqTableValue;
import com.core.solution.utils.MessagesBussines;
import com.core.solution.utils.PartitionTable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AforeService {

	private final AforeRepository aforeRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<EntityConfigTable> getConfigTables() throws SolutionException {
		try {
			List<EntityConfigTable> listConfigTables = this.aforeRepository.getConfigTables();
			return listConfigTables;
		} catch (Exception e) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_GET_ALL_CONFIG_AUTO,
					new SolutionData(MessagesBussines.SUCCESS, MessagesBussines.TITLE_ERROR_GET_ALL_CONFIG_AUTO,
							MessagesBussines.MESSAGE_ERROR_GET_ALL_CONFIG_AUTO,
							MessagesBussines.CODE_ERROR_GET_ALL_CONFIG_AUTO));
		}
	}

	public void patchTable(ReqConfigParent reqConfigParent) throws SolutionException {

		Map<String, String> mapHeader = new HashMap<String, String>();

		ReqAforeUpdate reqAforeUpdate = reqConfigParent.getReqAforeUpdate();
		ReqConfigTable reqConfigTable = reqAforeUpdate.getReqConfigTable();
		String nameTable = reqConfigTable.getNameTable();

		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<String> columnNames = new ArrayList<String>();
		DataSource dataSource = jdbcTemplate.getDataSource();
		connection = DataSourceUtils.getConnection(dataSource);
		log.info(nameTable);

		try {

			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ".concat(nameTable));
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 0;
				while (i < columns.getColumnCount()) {
					i++;
					columnNames.add(columns.getColumnName(i));
				}
				// this.printValuesTable(columnNames, i, rs);
			}

			for (String header : columnNames) {
				log.info(header);
				mapHeader.put(header, header);
			}

			String columnId = columnNames.get(0);

			List<ReqTableValue> listReqTableValue = reqAforeUpdate.getListReqTableValue();

			for (ReqTableValue request : listReqTableValue) {

				String headerName = request.getColumnTable();
				String rowValue = request.getRowValue();
				Integer detailId = request.getDetailId();

				log.info(headerName);

				if (mapHeader.containsKey(headerName.trim())) {

					StringBuilder str = new StringBuilder("UPDATE ".concat(nameTable));
					str.append(" SET ".concat(headerName).concat(" = ?"));
					str.append(" WHERE ".concat(columnId).concat(" = ?"));
					String query = str.toString();
					Object[] params = { rowValue, detailId };

					jdbcTemplate.update(query, params);

				}

			}

		} catch (Exception e) {
			log.error(e.getCause() + " " + e.getMessage());
		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (Exception e) {
				log.error(e.getCause() + " " + e.getMessage());
			}

		}

	}

	public void getDownloadTemplate(ReqConfigParent reqConfigParent) throws SolutionException {
		ReqAforeUpdate reqAforeUpdate = reqConfigParent.getReqAforeUpdate();
		ReqConfigTable reqConfigTable = reqAforeUpdate.getReqConfigTable();
		String nameTable = reqConfigTable.getNameTable();
		Integer rowInit = reqConfigTable.getRowInit();
		Integer columnInit = reqConfigTable.getColumnInit();
		String nameFile = reqConfigTable.getNameFile();
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<String> columnNames = new ArrayList<String>();
		DataSource dataSource = jdbcTemplate.getDataSource();
		connection = DataSourceUtils.getConnection(dataSource);
		log.info(nameTable);
		Workbook workbook = new XSSFWorkbook();
		ExcelHeader excelHeader = new ExcelHeader();
		ExcelBody excelBody = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ".concat(nameTable));
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 0;
				while (i < columns.getColumnCount()) {
					i++;
					columnNames.add(columns.getColumnName(i));
				}
				excelBody = this.printValuesTable(columnNames, i, rs);
			}
			excelHeader.setRowNumber(rowInit);
			excelHeader.setColumnNumber(columnInit);
			excelHeader.setListHeaderName(columnNames);

			Sheet sheetValid = workbook.createSheet("validacion");
			this.createSheetDataValidate(workbook, excelHeader, excelBody, nameFile, sheetValid);
			
			Sheet sheetEdit = workbook.createSheet("datos");
			this.createSheetDataValidate(workbook, excelHeader, excelBody, nameFile, sheetEdit);

			/*
			 * SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
			 * ConditionalFormattingRule rule =
			 * sheetCF.createConditionalFormattingRule("AND(ISNUMBER($A1), $A1>5)");
			 * PatternFormatting fill = rule.createPatternFormatting();
			 * fill.setFillBackgroundColor(IndexedColors.YELLOW.index);
			 * fill.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
			 * ConditionalFormattingRule[] cfRules = new ConditionalFormattingRule[] { rule
			 * }; CellRangeAddress[] regions = new CellRangeAddress[] {
			 * CellRangeAddress.valueOf("B1:B1000") };
			 * sheetCF.addConditionalFormatting(regions, cfRules);
			 */
			
			FileOutputStream out = new FileOutputStream("C:\\Users\\AIT-USR\\Desktop\\output\\".concat(nameFile));
			workbook.write(out);
			out.close();
			workbook.close();

		} catch (Exception e) {
			log.error(e.getCause() + " " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				log.error(e.getCause() + " " + e.getMessage());
			}
		}
	}

	private void createSheetDataValidate(Workbook workbook, ExcelHeader excelHeader, ExcelBody excelBody,
			String nameFile, Sheet sheet) throws SolutionException {

		Integer rowInit = excelHeader.getRowNumber();
		Integer columnInit = excelHeader.getColumnNumber();
		List<String> listHeader = excelHeader.getListHeaderName();
		Row rowHeader = sheet.createRow(rowInit);
		for (String headerName : listHeader) {
			Cell cellHeader = rowHeader.createCell(columnInit);
			cellHeader.setCellValue(headerName);
			++columnInit;
		}
		String nameHeader = null;
		log.info("Header build...");
		for (int i = excelHeader.getRowNumber(); i < listHeader.size(); i++) {
			nameHeader = listHeader.get(i);
			log.info("Column {} ", String.valueOf(i).concat(" header ").concat(nameHeader));
		}
		List<ExcelItem> listItems = excelBody.getListItems();
		log.info("Body build...");
		List<List<ExcelItem>> partitions = PartitionTable.partition(listItems, listHeader.size());
		int aux = excelHeader.getRowNumber() + 1;

		XSSFDataFormat format = (XSSFDataFormat) workbook.createDataFormat();
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		style.setDataFormat(format.getFormat("Text"));

		for (List<ExcelItem> listItemPartition : partitions) {
			System.out.println("");
			Row rowBody = sheet.createRow(aux);
			for (int j = 0; j < listItemPartition.size(); j++) {
				ExcelItem item = listItemPartition.get(j);
				Cell cellBody = rowBody.createCell(j);
				cellBody.setCellStyle(style);
				cellBody.setCellValue(item.getValue());
				System.out.println("Column " + j + " write value " + item.getValue());
			}
			System.out.println("");
			++aux;
		}

	}

	private ExcelBody printValuesTable(List<String> columnNames, int i, ResultSet rs) throws SQLException {
		ExcelBody excelBody = new ExcelBody();
		List<ExcelItem> listCellValue = new ArrayList<ExcelItem>();
		while (rs.next()) {
			for (i = 0; i < columnNames.size(); i++) {
				ExcelItem excelItem = new ExcelItem();
				excelItem.setValue(rs.getString(columnNames.get(i)));
				listCellValue.add(excelItem);
			}
		}
		excelBody.setListItems(listCellValue);
		return excelBody;
	}

	/*
	 * String sql =
	 * "INSERT INTO public.config_autoservicio(name_table, fields, name_file, enabled, creation_at, modification_at) VALUES ('demo1', '1,2,3', 'demo.xlsx', true, NOW(), NOW())"
	 * ; int rows = jdbcTemplate.update(sql); if (rows > 0) {
	 * System.out.println("A new row has been inserted."); }
	 */

}
