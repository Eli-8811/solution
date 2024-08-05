package com.core.solution.bussines;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.access.UserRepository;
import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.CellModel;
import com.core.solution.model.ExcelModel;
import com.core.solution.model.RowModel;
import com.core.solution.model.SheetModel;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.utils.MemoryUtil;
import com.core.solution.utils.MessagesBussines;
import com.monitorjbl.xlsx.StreamingReader;

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

	public String rewriteExcelReportUsers() throws SolutionException {

		MemoryUtil.showMemoryStats();
		long init = MemoryUtil.timeInit();
		log.info("Time init {} ", init);

		Integer line = 1;
		String base64 = null;

		Resource resource = resourceLoader.getResource("classpath:".concat("/excel/template_report_users.xlsx"));

		try (FileInputStream fileInputStream = new FileInputStream(resource.getFile().getAbsolutePath())) {

			Workbook workbook = new XSSFWorkbook(fileInputStream);

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

			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/MMM/yyyy HH:mm:ss"));
			cellStyleDate.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyleDate.setFillForegroundColor(grey);

			Sheet sheet = workbook.getSheetAt(0);

			List<EntityUser> listEntityUser = this.userRepository.getUsers();

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
			base64 = this.generateDataBase64(workbook);

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

		return base64;

	}

	private String generateDataBase64(Workbook workbook) throws SolutionException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_GENERATE_B64,
					new SolutionData(MessagesBussines.SUCCESS, MessagesBussines.TITLE_ERROR_GENERATE_B64,
							String.format(MessagesBussines.MESSAGE_ERROR_GENERATE_B64, "template.xlsx"),
							MessagesBussines.CODE_ERROR_GENERATE_B64),
					e);

		}
		return Base64.getEncoder().encodeToString(outputStream.toByteArray());
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

}
