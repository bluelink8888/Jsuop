import java.io.FileWriter;
import java.io.IOException;


public class FileOutput {

/*	
 * 	
 * 	輸入字串寫出txt檔
 * 
 * */	
	
	public void stringParseFile(String fileName, String input){
		try {
			// 寫出路徑固定再document下
			FileWriter fw = new FileWriter("/Users/YuWeiHung/Documents/MRT/" + fileName +".txt",true);
			fw.write(input);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		FileOutput fo = new FileOutput();
		fo.stringParseFile("321", "TEST321");
	}

}
