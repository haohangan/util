package dc.common.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dc.common.data.SessionInfo;
import dc.common.util.ConfigUtil;
import dc.common.util.DateTimeUtil;
import dc.common.util.ReportUtil;
import dc.common.util.ZipCompressorByAnt;


public class ExcelDownUtil {
	
	/**
	 * 下载excel文件
	 * @param response
	 * @param request reportName:报表名称;reportShowDate:查询日期 
	 * @param columns 表头
	 * @param list    详细列表
	 * @param version excel版本:2003或2007
	 */
	public static void exportExcel(HttpServletResponse response, HttpServletRequest request,Map<String,Object> columns,List<Map<String,String>> list,String version) throws Exception{
		
		//创建excel文件
		String filePath = startExcelDownload(request,columns,list,version);
		//下载excel文件
		endExcelDownload(response, request, filePath, version);
	}
	
	/**
	 *  开始创建excel
	 * @param request reportName:报表名称;reportShowDate:查询日期 
	 * @param columns 表头
	 * @param list    详细列表
	 * @param version  excel版本:2003或2007
	 * @return
	 * @throws Exception
	 */
	private  static String startExcelDownload(HttpServletRequest request,Map<String,Object> columns,List<Map<String,String>> list,String version) throws Exception{
		
		String reportName = ReportUtil.getReportName(request);
		String path = request.getSession().getServletContext().getRealPath("upload");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String userName = sessionInfo.getName();
		String ReprotShowDate = ReportUtil.getReprotShowDate(request);
		
		File file=new File(path);  
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(path+"/",reportName);
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(path+"/"+reportName+"/",userName);
		if(!file.exists()){
			file.mkdirs();
		}
		String tempPath = path+"/"+reportName+"/"+userName+"/"+String.valueOf(new Date().getTime());//临时文件夹
		file=new File(tempPath);
		if(!file.exists()){
			file.mkdirs();
		}
		if(list.size()==0){
			list.add(new HashMap<String,String>());
		}
		
		//添加行号
		Map tempMap = new LinkedHashMap();tempMap.putAll(columns);columns.clear();
		columns.put("rowNum", "行号");
		Iterator<Map.Entry<String,String>> ittemp = tempMap.entrySet().iterator();
		while (ittemp.hasNext()) {
			Map.Entry<String,String> entry = ittemp.next();
			columns.put(entry.getKey(), entry.getValue());
		}
		for(int i=0;i<list.size();i++){
			Map<String,String> map = list.get(i);
			map.put("rowNum", String.valueOf(i));
		}
				
		List<File> fileList = new ArrayList<File>();
		String showDate = ReprotShowDate;
		try {
			//每个excel文件一个sheet页,存储50000行数据，效果最佳
			List<List<Map<String,String>>> splitList = null;
			if(version.equals("2003")){
				splitList = splitList(list, 50000);
			}else{
				splitList = splitList(list, 1000000);
			}
			int i=1;
			for(List<Map<String,String>> fieldDatalist: splitList) {
				System.out.println("写入页数"+i);
				
				String fileName = "";
				Workbook workBook = null;
				String fileType = "";
				if(version.equals("2003")){
					workBook = new HSSFWorkbook();
					fileType = ".xls";
				}else{
					workBook = new SXSSFWorkbook(100);
					fileType = ".xlsx";
				}
				
				//查询条件表头
				Map<String,String> filterMap=new HashMap<String,String>();
				filterMap.put("reportDate","打印时间:"+DateTimeUtil.formatDate(new Date()));
				filterMap.put("reportName", reportName);
				filterMap.put("filterDate", "日期:"+showDate);
				
				//给Excel单元格写值
				expordExcel(fieldDatalist, columns, workBook,filterMap);
				
				//创建excel文件
				if(!showDate.equals("null")){
					if(splitList.size()==1){
						fileName = reportName+"("+showDate+")"+fileType;
					}else{
						fileName = reportName+"("+showDate+")"+i+fileType;
					}
				}
				else{
					if(splitList.size()==1){
						fileName = reportName+fileType;
					}else{
						fileName = reportName+i+fileType;
					}
				}
				
				file=new File(tempPath,fileName);
				if(!file.exists()){
					file.createNewFile();
				}
				fileList.add(file);//用于删除
				OutputStream os=new FileOutputStream(file);
				workBook.write(os);//生成excel文件
				os.close();
				
				//产生一个数据文件
				if(splitList.size()==1){
					return tempPath+"/"+fileName;
				}
					
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		String zipName = reportName+"("+showDate+").zip";
		ZipCompressorByAnt zca = new ZipCompressorByAnt(tempPath+"/"+zipName);
		zca.compress(tempPath); 
		
		//删除压缩过的文件
		for(File delfile : fileList){
			if(delfile.exists()){
				delfile.delete();
			}
		}
		
		return tempPath+"/"+zipName;
		
	}
	
   
   /**
    * 下载excel
    * @param response
    * @param request
    * @param filePath 文件磁盘存放路径
    * @param version 2003或2007
    */
	private static void endExcelDownload(HttpServletResponse response, HttpServletRequest request,String filePath,String version) throws Exception {
		
		OutputStream outputStream =null;
		BufferedInputStream inputStream=null;
		BufferedOutputStream out=null;
		String name="";
		try {
			if(filePath.contains(".zip")){
				name=java.net.URLDecoder.decode(filePath,"UTF-8");
				String[] tempArray = name.split("\\/");
				String downfileName = tempArray[tempArray.length-1];//下载的文件名
				response.setContentType("APPLICATION/OCTET-STREAM");  
				response.setHeader("Content-Disposition","attachment; filename="+ new String((downfileName).getBytes("GBK"), "ISO8859_1"));
			}else{
				version=version==null?"2003":version;
				name=java.net.URLDecoder.decode(filePath,"UTF-8");
				String[] tempArray = name.split("\\/");
				String downfileName = tempArray[tempArray.length-1];//下载的文件名
				//导出类型
				response.setContentType("application/vnd.ms-excel");
				if(version.equals("2003")){
					response.setHeader( "Content-Disposition", ("attachment; filename=" 
							+ new String((downfileName).getBytes("GBK"), "ISO8859_1")));
				}else{
					response.setHeader( "Content-Disposition", ("attachment; filename=" 
							+ new String((downfileName).getBytes("GBK"), "ISO8859_1")));
				}
			}
			outputStream =response.getOutputStream();
			FileInputStream fis = new FileInputStream(name);
			inputStream = new BufferedInputStream(fis);
			out = new BufferedOutputStream(outputStream);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				if(inputStream!=null){
					inputStream.close();
				}if(outputStream!=null){
					outputStream.close();
				}if(out!=null){
					out.close();
				}
				File file=new File(name);
				if(file.exists()){
					file.delete();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	/**
	 * 创建sheet ，写入sheet表头和第一页数据
	 * @param fieldData 写入的数据
	 * @param fieldName 数据对应的表头
	 * @param workBook  excel写入对象
	 * @param filterArray 查询条件
	 * @return
	 * @throws IOException
	 */
	private static Object[] expordExcel(List<Map<String,String>> fieldData,Map<String,Object> fieldName,Workbook workBook,Map<String,String> filterMap) throws IOException {
		List<String> fieldKey=new ArrayList<String>();
		List<String> fieldClazz=new ArrayList<String>();
		Sheet sheet = workBook.createSheet("sheet");//使用workbook创建sheet
		 //poi特点：先要创建sheet，通过sheet创建row，第1行
		//打印日期
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,fieldName.size()-1));
		CellStyle style0 = workBook.createCellStyle();
		style0.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		Cell cell0 = sheet.createRow(0).createCell(0);
		cell0.setCellStyle(style0);
		cell0.setCellValue(filterMap.get("reportDate"));
		
		//报表
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,fieldName.size()-1));
		CellStyle style1 = workBook.createCellStyle();
		Font font1 = workBook.createFont();
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		short color1 = HSSFColor.BLACK.index;
		font1.setColor(color1);
		font1.setFontHeightInPoints((short) 16);
		style1.setFont(font1);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Cell cell1 = sheet.createRow(1).createCell(0);
		cell1.setCellStyle(style1);
		cell1.setCellValue(filterMap.get("reportName"));
		
		
		//过滤日期
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,fieldName.size()-1));
		CellStyle style2 = workBook.createCellStyle();
		Font font2 = workBook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		short color2 = HSSFColor.BLACK.index;
		font2.setColor(color2);
		style2.setFont(font2);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Cell cell2 = sheet.createRow(2).createCell(0);
		cell2.setCellStyle(style2);
		cell2.setCellValue(filterMap.get("filterDate"));
		
		
	//	Row blankRow = sheet.createRow(1);
		
		Row headRow = sheet.createRow(4);
		Iterator<Map.Entry<String,Object>> it = fieldName.entrySet().iterator();
		
		CellStyle style = workBook.createCellStyle();
		//背景色
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		//获取字体对象
		Font font = workBook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		short color = HSSFColor.WHITE.index;
		font.setColor(color);
		//将字体的样式添加到CellStyle中
		style.setFont(font);
		
		for (int j = 0;it.hasNext(); j++) {//循环excel的标题
			Map.Entry<String,Object> entry = it.next();
			Cell cell = headRow.createCell(j);//poi特点：先要创建row，通过row创建cell
			//添加样式
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定导出的类型
			//设置列（有数据的列）的宽度
			sheet.setColumnWidth(j, 4000);
			//设置样式,使用workBook获取操作单元格的样式
			
			fieldKey.add(entry.getKey());
			cell.setCellStyle(style);
			if(entry.getValue()!= null){
				if (entry.getValue() instanceof JSONObject) {
					JSONObject jsonObject = (JSONObject) entry.getValue();
					fieldClazz.add(jsonObject.getString("clazz"));
					cell.setCellValue(jsonObject.getString("desc"));//向excel的单元格中设置
				} else {
					fieldClazz.add("String");
					cell.setCellValue((String)entry.getValue());//向excel的单元格中设置
				}
			}else{
				fieldClazz.add("");
				cell.setCellValue("-");
			}
		}
		String classStr;
		
		CellStyle style21 = workBook.createCellStyle();
		//背景色
		style21.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style21.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style21.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		
		//设置自定义数值格式
		CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        
		for (int i= 0; i < fieldData.size(); i++) {
			Row row = sheet.createRow(i+5);// 创建row，+1，从excel的第二行开始写入值
			// 将数据内容放入excel单元格
			Map<String,String> rowMap = (Map<String,String>) fieldData.get(i);
			for (int n = 0; n < fieldKey.size(); n++) {
				Cell cell = row.createCell(n);// 创建cell
				if (rowMap.get(fieldKey.get(n)) != null) {
					classStr = fieldClazz.get(n);
					if (classStr.contains("int") || classStr.contains("Integer") ||
							classStr.contains("double") || classStr.contains("Double") ||
							classStr.contains("float") || classStr.contains("Float")) {
						cell.setCellValue(Double.valueOf(String.valueOf(rowMap.get(fieldKey.get(n)))));
						
				        cell.setCellStyle(cellStyle);
					} else {
						cell.setCellValue(String.valueOf(rowMap.get(fieldKey.get(n))));
					}
				} else {
					cell.setCellValue("");
				}
				//最后一行统计
				//if(i==fieldData.size()-1){
				//第一行统计
				if(i==0){
					//设置样式,使用workBook获取操作单元格的样式
					cell.setCellStyle(style21);
				}
			}
		}
		return new Object[]{fieldKey,sheet};
	}
	
	
	/**
	 *  分割List
	 * @param list  待分割的list
	 * @param pageSize 每段list的大小
	 * @return List<<List<T>> 
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
	        
	        int listSize = list.size();                                          //list的大小
	        int page = (listSize + (pageSize-1))/ pageSize;                      //页数
	        
	        List<List<T>> listArray = new ArrayList<List<T>>();              	 //创建list数组 ,用来保存分割后的list
	        for(int i=0;i<page;i++) {                                            //按照数组大小遍历
	            List<T> subList = new ArrayList<T>();                            //数组每一位放入一个分割后的list
	            for(int j=0;j<listSize;j++) {                                    //遍历待分割的list
	                int pageIndex = ( (j + 1) + (pageSize-1) ) / pageSize;   	 //当前记录的页码(第几页)
	                if(pageIndex == (i + 1)) {                                   //当前记录的页码等于要放入的页码时
	                    subList.add(list.get(j));                                //放入list中的元素到分割后的list(subList)
	                }
	                
	                if( (j + 1) == ((j + 1) * pageSize) ) {                      //当放满一页时退出当前循环
	                    break;
	                }
	            }
	            listArray.add(subList);                                          //将分割后的list放入对应的数组的位中
	        }
	        return listArray;
	    }

	/**
	 * @param List fieldKey, sheet头数据
	 * @param List fieldData,写入的数据
	 * @param int k, 第K写入
	 * @param Sheet sheet 工作表
	 * 为Excel的单元格写值
	 * */
	private static void carateSheet(List<String> fieldKey,List<Map<String,String>> fieldData,int k, Sheet sheet)throws IOException {
		for (int i= 0; i < fieldData.size(); i++) {
			Row row = sheet.createRow(k+i+2);// 创建row，+1
			// 将数据内容放入excel单元格
			Map<String,String> rowMap = (Map<String,String>) fieldData.get(i);
			for (int n = 0; n < fieldKey.size(); n++) {
				Cell cell = row.createCell(n);// 创建cell
				if (rowMap.get(fieldKey.get(n)) != null) {
					cell.setCellValue(String.valueOf(rowMap.get(fieldKey.get(n))));
				} else {
					cell.setCellValue("");
				}
			}
		}
	}
	
	
	public static void main(String args[]) throws Exception{
		Workbook workBook = null;
		Sheet sheet=null;
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();//存每页数据
		List<String> fieldKey=new ArrayList<String>();//Excel表头的数据
		int maxNum = 10000;
		String version = "2007";
		if(version.equals("2003")){
			workBook = new HSSFWorkbook();
		}else{
			workBook = new XSSFWorkbook();
		}
		
		
		Map<String,Object> columns = getColumnsMap();
		Map<String,String> filterMap=new HashMap<String,String>();
		filterMap.put("date","日期：2013-12-05");
		
		//添加行号
		Map tempMap = new LinkedHashMap();tempMap.putAll(columns);columns.clear();
		columns.put("rowNum", "行号");
		Iterator<Map.Entry<String,String>> ittemp = tempMap.entrySet().iterator();
		while (ittemp.hasNext()) {
			Map.Entry<String,String> entry = ittemp.next();
			columns.put(entry.getKey(), entry.getValue());
		}
				
				
		//给Excel单元格写值
		for (int i = 0; i < maxNum;i+=4000) {
			list.clear();
			int end = i+4000;
			if(end>maxNum)end=maxNum;
			list = getData(i,end);
			for(int k=0;k<list.size();k++){
				Map<String,String> map = list.get(k);
				map.put("rowNum", String.valueOf(k));
			}
			if(i==0){
				Object[] result=expordExcel(list, columns, workBook,filterMap);
				fieldKey=(List) result[0];
				sheet=(Sheet) result[1];
			}else{
				carateSheet(fieldKey, list, i, sheet);
			}
		}
		
		String path = "c:/upload";
		String name = version.equals("2003")?"test.xls":"test.xlsx";
		File file=new File(path);  
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(path+"/",name);
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream os=new FileOutputStream(file);
		workBook.write(os);//生成excel文件
		
		
	}
	
	private static List<Map<String,String>> getData(int start,int end){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
		for (int i=start;i<end;i++){
			Map<String,String> datamap = new LinkedHashMap<String,String>();
			datamap.put("a", "11");
			datamap.put("b", "22");
			datamap.put("c", "33");
			datamap.put("d", "44");
			datamap.put("e", "55");
			list.add(datamap);
		}
		
		
		return list;
		
	}
	
	private static Map<String,Object> getColumnsMap(){
		
		Map<String,Object> datamap = new LinkedHashMap<String,Object>();
		datamap.put("a", "测试a");
		datamap.put("b", "测试b");
		datamap.put("c", "测试c");
		datamap.put("d", "测试d");
		datamap.put("e", "测试e");
		
		return datamap;
	}

}
