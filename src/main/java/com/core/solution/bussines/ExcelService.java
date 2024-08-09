package com.core.solution.bussines;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

import com.core.solution.access.UserRepository;
import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.CellModel;
import com.core.solution.model.ExcelModel;
import com.core.solution.model.RowModel;
import com.core.solution.model.SheetModel;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.response.ResponseFile;
import com.core.solution.utils.MemoryUtil;
import com.core.solution.utils.MessagesBussines;
import com.core.solution.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExcelService {

	private final ResourceLoader resourceLoader;

	private final UserRepository userRepository;

	public ExcelModel uploadBigFile(MultipartFile file) {
		MemoryUtil.showMemoryStats();
		long init = MemoryUtil.timeInit();
		log.info("Time init {} ", init);
		InputStream is = null;
		ExcelModel excelModel = new ExcelModel();
		try {
			is = file.getInputStream();
			Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(8192).open(is);
			List<SheetModel> listSheetModel = new ArrayList<>();
			for (Sheet sheet : wb) {
				List<RowModel> listRowModel = new ArrayList<>();
				SheetModel sheetModel = new SheetModel();
				sheetModel.setSheetName(sheet.getSheetName());
				listSheetModel.add(sheetModel);
				log.info("Process sheet {} ", sheetModel.getSheetName());
				for (Row row : sheet) {
					List<CellModel> listCellModel = new ArrayList<>();
					RowModel rowModel = new RowModel();
					rowModel.setRowNumber(row.getRowNum());
					listRowModel.add(rowModel);
					for (Cell cell : row) {
						CellModel cellModel = new CellModel();
						cellModel.setCellValue(cell.getStringCellValue());
						listCellModel.add(cellModel);
					}
					rowModel.setListCellModel(listCellModel);
				}
				sheetModel.setListRowModel(listRowModel);
			}
			excelModel.setNameFile(file.getOriginalFilename());
			excelModel.setListSheetModel(listSheetModel);
			MemoryUtil.showMemoryStats();
			long end = MemoryUtil.timeEnd(init);
			log.info("Time end {} ", end);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelModel;
	}

	public ResponseFile rewriteExcelReportUsers(String datetimeInit, String datetimeEnd)
			throws SolutionException, ParseException {

		MemoryUtil.showMemoryStats();
		long init = MemoryUtil.timeInit();
		log.info("Time init {} ", init);

		Integer line = 1;
		Resource resource = resourceLoader.getResource("classpath:".concat(Constants.REPORT_RANGE_DATE_USERS));
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY_MM_DD_HH_MM_SS);
		ResponseFile responseFile = new ResponseFile();

		Date dateStart = sdf.parse(datetimeInit);
		Date dateEnd = sdf.parse(datetimeEnd);

		try (FileInputStream fis = new FileInputStream(resource.getFile().getAbsolutePath())) {

			Workbook workbook = new XSSFWorkbook(fis);

			DataFormat format = workbook.createDataFormat();
			CreationHelper createHelper = workbook.getCreationHelper();
			IndexedColorMap colorMap = ((XSSFWorkbook) workbook).getStylesSource().getIndexedColors();
			XSSFColor grey = new XSSFColor(new java.awt.Color(242, 242, 242), colorMap);

			XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
			XSSFCellStyle cellStyleDate = (XSSFCellStyle) workbook.createCellStyle();
			XSSFCellStyle cellStyleNumber = (XSSFCellStyle) workbook.createCellStyle();

			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyle.setFillForegroundColor(grey);

			cellStyleNumber.setDataFormat(format.getFormat("#,##0"));
			cellStyleNumber.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyleNumber.setFillForegroundColor(grey);

			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/MM/dd HH:mm:ss"));
			cellStyleDate.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyleDate.setFillForegroundColor(grey);

			Sheet sheet = workbook.getSheetAt(0);

			List<EntityUser> listEntityUser = this.userRepository.getUsersByRangeDate(dateStart, dateEnd);

			for (int j = 0; j < listEntityUser.size(); j++) {

				Row row = sheet.createRow(line);

				Cell cellUserId = row.createCell(0);
				Cell cellName = row.createCell(1);
				Cell cellLastname = row.createCell(2);
				Cell cellUsername = row.createCell(3);
				Cell cellEmail = row.createCell(4);
				Cell cellAge = row.createCell(5);
				Cell cellPhone = row.createCell(6);
				Cell cellEnabled = row.createCell(7);
				Cell cellCreationAt = row.createCell(8);
				Cell cellModificationat = row.createCell(9);
				Cell cellFormula = row.createCell(10);

				cellUserId.setCellStyle(cellStyleNumber);
				cellName.setCellStyle(cellStyle);
				cellLastname.setCellStyle(cellStyle);
				cellEmail.setCellStyle(cellStyle);
				cellUsername.setCellStyle(cellStyle);
				cellAge.setCellStyle(cellStyleNumber);
				cellPhone.setCellStyle(cellStyle);
				cellEnabled.setCellStyle(cellStyle);
				cellCreationAt.setCellStyle(cellStyleDate);
				cellModificationat.setCellStyle(cellStyleDate);

				cellUserId.setCellValue(listEntityUser.get(j).getUserId());
				cellName.setCellValue(listEntityUser.get(j).getName());
				cellLastname.setCellValue(listEntityUser.get(j).getLastname());
				cellUsername.setCellValue(listEntityUser.get(j).getUsername());
				cellEmail.setCellValue(listEntityUser.get(j).getEmail());
				cellAge.setCellValue(listEntityUser.get(j).getAge());
				cellPhone.setCellValue(listEntityUser.get(j).getPhone());
				cellEnabled.setCellValue(listEntityUser.get(j).getEnabled());
				cellCreationAt.setCellValue(listEntityUser.get(j).getCreationAt());
				cellModificationat.setCellValue(listEntityUser.get(j).getModificationAt());

				line++;

				cellFormula.setCellType(CellType.FORMULA);
				cellFormula.setCellFormula(
						"SUM(".concat("F").concat(line.toString()).concat(",F").concat(line.toString()).concat(")"));

			}

			this.evaluateFormula(workbook);
			responseFile = this.generateDataBase64(workbook);
			responseFile.setFileName(resource.getFile().getName());
			responseFile.setAbsolutePath(resource.getFile().getAbsolutePath());

		} catch (IOException e) {

			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_PROCESS_EXCEL_NORMAL,
					new SolutionData(MessagesBussines.SUCCESS, MessagesBussines.TITLE_ERROR_PROCESS_EXCEL_NORMAL,
							MessagesBussines.MESSAGE_ERROR_PROCESS_EXCEL_NORMAL,
							MessagesBussines.CODE_ERROR_PROCESS_EXCEL_NORMAL),
					e);

		}

		MemoryUtil.showMemoryStats();
		long end = MemoryUtil.timeEnd(init);
		log.info("Time end {} ", end);

		return responseFile;

	}

	public void uploadFileComisionAfore(MultipartFile file) {

		Workbook wb = null;
		Map<Integer, String> mapHeader = null;

		try {

			byte[] xlsBytes = file.getInputStream().readAllBytes();
			wb = WorkbookFactory.create(new ByteArrayInputStream(xlsBytes));

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {

				String name = wb.getSheetName(i);
				Sheet sheet = wb.getSheet(name);

				mapHeader = this.getCompositionHeader(sheet);

				for (Map.Entry<Integer, String> entryHeader : mapHeader.entrySet()) {
					log.info(entryHeader.getValue());
					this.getCompositionBody(sheet, entryHeader.getKey());
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getCompositionBody(Sheet sheet, int column) {
		int rowInit = 1;
		for (Row row : sheet) {			
			if(row.getRowNum() > rowInit) {			
				Cell cell = this.getCell(row, column);
				Object value = this.getCellValue(cell);
				log.info("Process row {} ", String.valueOf(row.getRowNum()).concat(" column ").concat(String.valueOf(column)).concat(" cell ").concat(String.valueOf(value)));	
			}
        }
	}


	private Map<Integer, String> getCompositionHeader(Sheet sheet) {
		int rowInit = 1;
		int columnInit = 0;
		Row row = sheet.getRow(rowInit);
		Cell cell = null;
		String compositionHeader = null;
		int position = (row.getLastCellNum() - 1);
		Map<Integer, String> map = new HashMap<>();
		for (int startColumn = columnInit; startColumn <= position; startColumn++) {
			cell = row.getCell(startColumn);
			compositionHeader = cell.getStringCellValue();
			log.info("Process column ".concat(String.valueOf(startColumn)).concat(" value ".concat(compositionHeader)));
			map.put(startColumn, compositionHeader);
		}
		return map;
	}

	private void evaluateFormula(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		for (Row r : sheet) {
			for (Cell c : r) {
				if (c.getCellType() == CellType.FORMULA) {
					evaluator.evaluateFormulaCell(c);
				}
			}
		}
	}
	
	private Cell getCell(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);
		if ( cell != null ) {
			return cell;
		}
		return row.createCell(cellNum);
	}
	
	private Object getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			switch (evaluator.evaluateInCell(cell).getCellType()) {
			case STRING:
				return evaluator.evaluateInCell(cell).getStringCellValue();
			case NUMERIC:
				return evaluator.evaluateInCell(cell).getNumericCellValue();
			case BOOLEAN:
				return evaluator.evaluateInCell(cell).getBooleanCellValue();
			default:
				return null;
			}
		case ERROR:
			return "Error: " + cell.getErrorCellValue();
		default:
			return null;
		}
	}

	private ResponseFile generateDataBase64(Workbook workbook) throws SolutionException {
		String base64 = null;
		ResponseFile responseFile = new ResponseFile();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
			responseFile.setBase64(base64);
			double value = (double) outputStream.size() / 1024;
			responseFile.setFileZize(String.valueOf(value).concat(" KB"));
		} catch (IOException e) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_GENERATE_B64,
					new SolutionData(MessagesBussines.SUCCESS, MessagesBussines.TITLE_ERROR_GENERATE_B64,
							String.format(MessagesBussines.MESSAGE_ERROR_GENERATE_B64, "template.xlsx"),
							MessagesBussines.CODE_ERROR_GENERATE_B64),
					e);

		}
		return responseFile;
	}

}
