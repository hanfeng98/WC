import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WC {
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
	System.out.print("��������Ҫ���еĲ������������:");
	Scanner sc=new Scanner(System.in);
	String temp[]=sc.nextLine().split("\\s+");
	String option=temp[0];
	String filepath=temp[1];
	String filetype=temp[2].substring(temp[2].lastIndexOf(".")+1);
	String tongpei=temp[2].substring(0, temp[2].indexOf("."));
	File file=new File(filepath);
	
	
	try {
	sc.close();
	}catch (Exception e){
		e.printStackTrace();
	}; 
	
	
	Map<String,Integer> map=new HashMap<String,Integer>();
	map.put("-c",1);
	map.put("-w",2);
	map.put("-l",3);
	map.put("-s",4);
	map.put("-a",5);
	
	
	wordcount count=new wordcount();
    
	switch (map.get(option)) {
	case 1:count.charactercount(file);break;
	case 2:count.wordcount(file);break;
	case 3:count.linecount(file);break;
	case 4:count.rtcatalog(file,tongpei,filetype);break;
	case 5:count.CountSpecialLines(file);break;
	default:System.out.println("�����°���ʽ����"+"\n"+"[parameter] [file_name]");
					 			}
	}

}
