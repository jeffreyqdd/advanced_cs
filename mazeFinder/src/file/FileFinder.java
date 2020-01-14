package file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder
{
	public static ArrayList<String> findFile(String directory)
	{
		ArrayList<String> foundFiles = new ArrayList<>();
		
		try (Stream<Path> walk = Files.walk(Paths.get(directory)))
		{
			
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());
			
			result.forEach(foundFiles::add);
			
			return foundFiles;
		}
		catch(Exception e)
		{
			return foundFiles;
		}
	}
}
