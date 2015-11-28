import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze
{
	//User enters number of columns and rows, at least 20x20
	public static void createMaze(int x, int y)
	{
		//Todo: fix later
		if (x < 20 || y < 20)
		{
			System.out.println("Parameters must be above 20.");
			return;
		}
		
		int NumElements = x*y;
		int NumInSameSet = x;
		
		DisjSets maze = new DisjSets(NumElements);
		
		// Create list of possible positions
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < NumElements; i++)
			list.add(i);
		
		while (list.size() > 0)
		{
			int pos = list.get(new Random().nextInt(list.size()));
			System.out.println(pos + "\n");
		}

//        for( int i = 0; i < NumElements; i++ )
//        {
//            System.out.printf( "%03d * ", maze.find( i ) );
//            if( i % NumInSameSet == NumInSameSet - 1 )
//                System.out.println( );
//        }
        System.out.println( );
		
	}
}
