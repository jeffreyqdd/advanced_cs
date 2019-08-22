package input;


import java.io.*;



public class Read {

	public static void main(String[] args) throws IOException {
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		try
		{
			fin = new FileInputStream("text.txt");
			
			int i;
			while((i = fin.read())!=-1) //not eof
			{
				int n = (int)i;
				
				System.out.println(n);
			}
		}
		catch(Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
			System.out.println("something went wrong");
		}
		finally {
		System.out.println("closing files");
        // releases system resources associated with this stream
        if(fin!=null)
           fin.close();
		}
	}

}
