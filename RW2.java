package day0421;

import java.io.*;

public class RW2 {
	public static void main(String[] args) throws Exception {
		//(写入路径, 输出路径)
		getFile("F:\\Helloworld", "G:\\a");
	}

	public static void getFile(String file, String outpath) throws Exception {
		//循环遍历file的文件及文件夹
		for (String f : new File(file).list()) {
			File fil=new File(file+File.separator+f);
			System.out.println(file+File.separator+f);
			//如果是文件：
			if (fil.isFile()) {
				File ftt = new File(outpath);
				System.out.println("outpath:"+outpath);
				//判断该路径是否存在，如果不存在则创建文件夹
				if (!ftt.exists()) {
					ftt.mkdirs();
				}
				
				// 读写文件
				createFile(fil,outpath + File.separator + f);
			} 
			
			//如果是文件夹：
			else if (fil.isDirectory()) {
				System.out.println("outpath:"+outpath);
				System.out.println("dir:" +  File.separator + fil.getName());
				//为防止出现空文件夹而进行创建
				File fi=new File(outpath + File.separator + fil.getName());
				fi.mkdir();//如果没有空文件夹，那么这一步没用
				//递归调用
				getFile(file+File.separator+f, outpath + File.separator + f);
			}
		}
	}
	
	public static void createFile(File fil,String outpath) throws Exception {
		FileInputStream fis = new FileInputStream(fil);
		System.out.println("file:" + outpath);
		FileOutputStream fos = new FileOutputStream(outpath);
		int len = 0;
		byte b[] = new byte[1024];
		while ((len = fis.read(b)) != -1) {
			fos.write(b, 0, len);
		}
		fos.close();
		fis.close();
	}
}

