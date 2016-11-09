package dc.material.service.impl;


import net.sf.json.JSONObject;

@Service("material_PmAllocateCinemaServiceImpl")
public class PmAllocateCinemaServiceImpl implements PmAllocateCinemaService {
	
	/**
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void export(HttpServletRequest request,HttpServletResponse response,PmAllocateCinemaSearchVO svo) throws Exception {
		List<HashMap<String, String>> dataList = dao.listReport(svo);
		JSONObject headMap = createHead();
		
		PmAllocateMovieSearchVO pamsvo = new PmAllocateMovieSearchVO();
		BeanUtils.copyProperties(svo, pamsvo);
		List<PmAllocateMovieVO> materialList = movieDao.listByCodes(pamsvo);
		for(PmAllocateMovieVO pamvo:materialList){
			addColumn(pamvo,headMap);
			for(int i = 0;i<pamvo.getPM_MATERIAL_NUM();i++){
				dataList.get(i).put(pamvo.getPM_MATERIAL_CODE(), "1");
			}
		}
		
		request.setAttribute("reportName", "物料分配");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		request.setAttribute("reportShowDate", sdf.format(new Date()));// 月份

		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>(dataList.size());
		for(HashMap<String, String> map :dataList){
			list.add(map);
		}
		
		ExcelDownUtil.exportExcel(response, request, headMap, list, "2003");
	}

	/**
	 * 基础的表头
	 * @return
	 */
	private JSONObject createHead(){
		JSONObject headMap = new JSONObject();
		
		JSONObject colunm1 = new JSONObject();
		colunm1.put("desc", "编码");
		colunm1.put("clazz", "class java.lang.String");
		headMap.put("CCA_CINEMA_CODE", colunm1);
		
		JSONObject colunm2 = new JSONObject();
		colunm2.put("desc", "影城名称");
		colunm2.put("clazz", "class java.lang.String");
		headMap.put("LOC_NAME", colunm2);
		
		JSONObject colunm3 = new JSONObject();
		colunm3.put("desc", "票房");
		colunm3.put("clazz", "class java.lang.Double");
		headMap.put("CCA_EARN", colunm3);
		
		return headMap;
	}
	/**
	 * 动态增加列
	 * @param pamvo
	 * @param headMap
	 */
	private void addColumn(PmAllocateMovieVO pamvo,JSONObject headMap){
		JSONObject colunm = new JSONObject();
		colunm.put("desc", pamvo.getPM_MATERIAL_DESC());
		colunm.put("clazz", "class java.lang.String");
		headMap.put(pamvo.getPM_MATERIAL_CODE(), colunm);
	}
}
