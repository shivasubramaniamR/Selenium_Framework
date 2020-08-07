package testScripts;

import java.io.File;

public class Path {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"screenshots"+File.separator ;
		System.out.println(path.replace("\\", "/"));

	}

}
