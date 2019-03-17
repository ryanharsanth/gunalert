package org.apache.maven.maven;
import java.io.File;
import java.util.*;
public class Client {
	public static void main(String[] args){
		File file = new File("C:/Users/barbi_000/Documents/Test");
		if(file.isDirectory()){
			String[] fileList = file.list();
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 0; i < fileList.length; i++){
				list.add(fileList[i]);
				System.out.println(list.get(i));
			}
			while(list.size()>0){
				File nextFile = new File(file.list()[0]);
				System.out.println(nextFile);
				nextFile.delete();
				list.remove(0);
			}
		}
	}
}
