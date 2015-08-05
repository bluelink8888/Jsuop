import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.jsoup.*;

public class Demo {

	public void trace(String imputurl,String s2elect,String submit ) throws Exception {
		
		FileOutput fo = new FileOutput(); // 寫出文字檔使用
		
		// 需要輸入的資料 使用map存放
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("s2elect", s2elect);
		datas.put("submit", submit);
		Document metDoc = Jsoup.connect(imputurl).data(datas).post();
		Element body = metDoc.body(); // 取得網頁的<body>.....</body>
		Element tr = metDoc.getElementsByTag("tr").get(2); // 取得票價等資訊所在的tr
		Elements trs = tr.getElementsByTag("tr"); // 取得該tr下所有的細項資訊
		Elements ths = trs.get(1).getElementsByTag("th"); // 取得起迄站的th資料
		StringBuffer sb = new StringBuffer(); // 用於暫存每列資料
		String result = ""; // 整個頁面的結果
		for (int i = 2; i < trs.size(); i++) {
			Elements tds = trs.get(i).getElementsByTag("td");
			
			// 組成想要的資料格式
			// 格式為 起站 : value, 迄站 : value,..... \r\n 起站 : value ...and so on!
			for (int j = 0; j < tds.size(); j++) {
				if (j != 1) {
					sb.append(ths.get(j).text() + ":"
							+ tds.get(j).text());
					if(j!=tds.size()-1){
						sb.append(",");
					}
				}
			}
			sb.append("\r\n"); // 每列資料換行
			
		}
		result = sb.toString(); // 把 sb 轉成 String 
		sb.delete(0, sb.length()); // 清空StringBuffer
		if(!result.isEmpty()){
			
			fo.stringParseFile(s2elect, result); // 將查詢結果輸出
		}
	}

	public static void main(String[] args) throws Exception {
		Demo demo = new Demo();
		for(int i =0; i<300;i++){
			String s2elect = "";
			if(i<100){
				s2elect = "0" + Integer.toString(i);
			}
			else{
				s2elect = Integer.toString(i);
			}
			demo.trace("http://web.metro.taipei/c/TicketALLresult.asp",s2elect,"確定");
		}
	}

}
