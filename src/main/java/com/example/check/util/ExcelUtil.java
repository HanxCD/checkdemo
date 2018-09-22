package com.example.check.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;

public class ExcelUtil {

	private static final String TEMPLATE_PATH = "/template/";
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	
	/**
	 * 得到Workbook对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(MultipartFile file) throws IOException{
		//这样写  excel 能兼容03和07
		InputStream is = file.getInputStream();
		Workbook hssfWorkbook = null; 
		try { 
		    hssfWorkbook = new HSSFWorkbook(is); 
		} catch (Exception ex) {
		    is =file.getInputStream();
		    hssfWorkbook = new XSSFWorkbook(is); 
		}
		return hssfWorkbook;
	}
	
	public static Workbook getWorkBook(File file) throws IOException{
		//这样写  excel 能兼容03和07
		FileInputStream fis = new FileInputStream(file);
		Workbook hssfWorkbook = null; 
		try { 
			hssfWorkbook = new HSSFWorkbook(fis);
		} catch (Exception ex) {
			fis = new FileInputStream(file);
		    hssfWorkbook = new XSSFWorkbook(fis); 
		}
		return hssfWorkbook;
	}
	
	public static <T> List<T> importExcel(String filePath, ImportParams params, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}

		List<T> list = null;

		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass,
					params);
		} catch (Exception e) {
			logger.error("模板不能为空 " + e);
		}

		return list;
	}


	public static <T> List<T> importExcel(String filePath, Integer titleRows,Integer headerRows, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;

		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass,
					params);
		} catch (Exception e) {
			logger.error("模板不能为空 " + e);
		}

		return list;
	}

	public static <T> List<T> importExcel(MultipartFile file,Integer titleRows, Integer headerRows, Class<T> pojoClass) {
		if (file == null) {
			logger.info("file为空");
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;

		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(),
					pojoClass, params);
		} catch (Exception e) {
			logger.error("excel文件不能为空 " + e);
		}

		return list;
	}

	public static void exportExcel(List<?> list, String title,String sheetName, Class<?> pojoClass, String fileName,boolean isCreateHeader, HttpServletResponse response) {
		ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
	}

	private static void defaultExport(List<?> list, Class<?> pojoClass,	String fileName, HttpServletResponse response,ExportParams exportParams) {
		Workbook wb = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if(wb != null){
			downLoadExcel(fileName, response, wb);
		}
	}
	
	public static void exportExcel(Collection<? extends Map<?, ?>> dataSet, String title, String sheetName, List<ExcelExportEntity> headerlist,String fileName, HttpServletResponse response){
		ExportParams exportParams = new ExportParams(title, sheetName);
		Workbook wb =  ExcelExportUtil.exportExcel(exportParams, headerlist, dataSet);
		if(wb !=null ){
			downLoadExcel(fileName, response, wb);
		}
    }
	
	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }
	
	public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }
	
	private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }
	
	private static void downLoadExcel(String fileName,HttpServletResponse response, Workbook wb) {
		
		try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(response.getOutputStream());
        } catch (IOException e) {
        	logger.error("excel文件不能为空 " + e);
        }
	}

	public void downloadExcelTemplate(String fileName,HttpServletResponse response,HttpServletRequest request) {
        try {
            //获取文件的路径
            String excelPath = request.getSession().getServletContext().getRealPath(TEMPLATE_PATH+fileName+".xlsx");
            // 读到流中
            InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 循环取出流中的数据
            byte[] b = new byte[200];
            int len;

            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	
        }
    }
	
}
