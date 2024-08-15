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

import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.core.solution.access.AforeRepository;
import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityConfigTable;
import com.core.solution.model.payload.ReqAforeUpdate;
import com.core.solution.model.payload.ReqConfigParent;
import com.core.solution.model.payload.ReqConfigTable;
import com.core.solution.model.payload.ReqTableValue;
import com.core.solution.utils.MessagesBussines;

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
				this.printValuesTable(columnNames, i, rs);
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

		Workbook workbook = new XSSFWorkbook();
		
		this.createSheetDataValidate(workbook);

	}

	private void createSheetDataValidate(Workbook workbook) throws SolutionException {
		
		Sheet sheet = workbook.createSheet("sheet");
		SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
		ConditionalFormattingRule rule = sheetCF.createConditionalFormattingRule("AND(ISNUMBER($A1), $A1>5)");
		PatternFormatting fill = rule.createPatternFormatting();
		fill.setFillBackgroundColor(IndexedColors.YELLOW.index);
		fill.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
		ConditionalFormattingRule[] cfRules = new ConditionalFormattingRule[] { rule };

		CellRangeAddress[] regions = new CellRangeAddress[] { CellRangeAddress.valueOf("B1:B1000") };

		sheetCF.addConditionalFormatting(regions, cfRules);

		try {
			
			FileOutputStream out = new FileOutputStream("C:\\Users\\AIT-USR\\Desktop\\output\\ConditionalFormatting.xlsx");
			workbook.write(out);
			out.close();
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void printValuesTable(List<String> columnNames, int i, ResultSet rs) throws SQLException {
		while (rs.next()) {
			for (i = 0; i < columnNames.size(); i++) {
				System.out.print(rs.getString(columnNames.get(i)) + "\t");
			}
			System.out.print("\n");
		}
	}

}
