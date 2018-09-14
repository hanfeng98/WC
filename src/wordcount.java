import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wordcount {
	
	void charactercount(File file) {
	String str=null;
	char temp[]=null;
	int characternum=0;
	try {
		InputStreamReader ir =new InputStreamReader(new FileInputStream(file));
		BufferedReader BF=new BufferedReader(ir);
		while((str=BF.readLine())!=null) {
		temp=str.toCharArray();
		characternum+=temp.length;
		}
		ir.close();
		BF.close();
		System.out.println("�ַ���:"+characternum);
	} catch (FileNotFoundException e) {
		// TODO �Զ����ɵ� catch ��
		System.out.println("�޷��ҵ�ָ�����ļ�");
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("�ļ���ȡʧ��");
	}
	
	}
	
	void wordcount(File file) {
		String str=null;
		int wordnum=0;
		try {
			InputStreamReader ir =new InputStreamReader(new FileInputStream(file));
			BufferedReader BF=new BufferedReader(ir);
			while((str=BF.readLine())!=null) {
			wordnum+=str.split("\\W").length;
			}
			ir.close();
			BF.close();
			System.out.println("������:"+wordnum);
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("�޷��ҵ�ָ�����ļ�");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("�ļ���ȡʧ��");
		}
	}
	
	void linecount(File file) {

		int linenum=0;
		try {
			InputStreamReader ir =new InputStreamReader(new FileInputStream(file));
			BufferedReader BF=new BufferedReader(ir);
			while(BF.readLine()!=null) {
			linenum++;
			}
			ir.close();
			BF.close();
			System.out.println("������:"+linenum);
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("�޷��ҵ�ָ�����ļ�");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("�ļ���ȡʧ��");
		}
	}
	
	void rtcatalog(File filepath,String filename,String str) {
		File listfile[]=filepath.listFiles();
		String tongpei=null;
		if(filename==null) {
		tongpei=".*";
		}else if(filename.contains("*")) {
		tongpei=filename.replaceAll("\\*",".*");
		}else if(filename.contains("?")) {
		tongpei=filename.replaceAll("\\?",".?");
		} 
		Pattern p=Pattern.compile(tongpei);
			
		for(File file:listfile) {
		if(file.isDirectory()) {
			rtcatalog(file,filename,str);
		}else if(file.exists()){
			Matcher m=p.matcher(file.getName());
			if(file.getName().endsWith(str)&&m.find()) {
			System.out.println("�ļ�����Ŀ¼��"+file.getAbsolutePath());
			charactercount(file);
			wordcount(file);
			linecount(file);
			CountSpecialLines(file);
			}
		}else if(!file.exists()) {
			System.out.println("�޷��ҵ�ָ����·��Ŀ¼��ָ�����͵��ļ�");
		}
		}
	}
	
	void CountSpecialLines(File file) {
	int blanklines=0;
	int commentlines=0;
	int codelines=0;
	String temp=null;
	String blanklinesregex ="\\s*\\W?\\s*";	
	String bclbeginregex="\\s*.?\\s*/\\*.*";
	String bclendregex="\\s*.?\\s*\\*/.*";
	String clregex="\\W?//.*";
	boolean blcflag=false;
	try {
		InputStreamReader ir =new InputStreamReader(new FileInputStream(file));
		BufferedReader BF=new BufferedReader(ir);
		while((temp=BF.readLine())!=null) {
		if(temp.matches(blanklinesregex)&&(blcflag==false)) {
		blanklines++;
		}else if(temp.matches(bclbeginregex)&&temp.matches(bclendregex)) {
		commentlines++;
		blcflag=false;
		}else if(temp.matches(clregex)) {
		commentlines++;	
		}else if(temp.matches(bclbeginregex)||blcflag==true) {
		commentlines++;
		blcflag=true;	
		}else if((temp.matches(bclendregex))&&(blcflag==true)) {
		commentlines++;
		blcflag=false;	
		}else if(blcflag==false){
		codelines++;
		}
		}
		ir.close();
		BF.close();
	} catch (FileNotFoundException e) {
		// TODO �Զ����ɵ� catch ��
		System.out.println("�޷��ҵ�ָ�����ļ�");
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("�ļ���ȡʧ��");
	}
	System.out.println("  ����  ��"+blanklines);
	System.out.println("�����У�"+codelines);
	System.out.println("ע���У�"+commentlines);
	}


}
