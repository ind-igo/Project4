import java.util.Random;
import java.util.Scanner;

public class Maze extends DisjSets
{
	private int NumElements;
	private int NumInSameSet;
	private String[] aMaze;

	public Maze(int x, int y)
	{
		super(x * y);

		setNumElements(x * y);
		setNumInSameSet(x);
		setaMaze(new String[x * y]);
	}

	public void print()
	{
		for (int k = 0; k < getNumInSameSet(); k++)
			System.out.print(" _");
		System.out.println();
		System.out.print("|");

		for (int i = 0; i < getNumElements(); i++)
		{
			System.out.print(getaMaze()[i]);
			if (i % getNumInSameSet() == getNumInSameSet() - 1)
			{
				System.out.println();
				String bar = (i != getNumElements() - 1) ? "|" : "";
				System.out.print(bar);
			}
		}
	}

	public static void main(String[] args)
	{
		int numCol = 0, numRow = 0;
		Scanner keyboard = new Scanner(System.in);

		do
		{
			System.out.println("Please enter numbers less than or equal to 100.");

			System.out.print("Enter number of rows: ");
			numRow = keyboard.nextInt();

			System.out.print("Enter number of columns: ");
			numCol = keyboard.nextInt();

		} while (numRow > 100 || numCol > 100);

		keyboard.close();

		Maze maze = new Maze(numRow, numCol);

		// Populate
		for (int i = 0; i < maze.getNumElements(); i++)
			maze.getaMaze()[i] = "_|";

		// MAIN LOOP
		while (maze.find(0) != maze.find(maze.getNumElements() - 1))
		{
			int pos = new Random().nextInt(maze.getNumElements());

			maze.breakWall(pos);
		}

		for (int k = 1; k < maze.getNumElements(); k++)
			if (maze.find(0) != maze.find(k))
				maze.breakWall(k);

		System.out.println();

		maze.print();

	}

	private void breakWall(int pos)
	{
		int set1, set2;
		int dir = new Random().nextInt(2);

		if (pos != getNumElements() - 1)
		{

			if (dir == 0) // R
			{
				if (pos % getNumInSameSet() == getNumInSameSet() - 1)
					breakWall(pos);
				else
				{
					set1 = find(pos);
					set2 = find(pos + 1);

					if (set1 != set2)
					{
						union(set1, set2);
						getaMaze()[pos] = getaMaze()[pos].replace("|", " ");
					}
				}
			}

			else if (dir == 1) // D
			{
				if (pos + getNumInSameSet() > getNumElements() - 1)
					breakWall(pos);
				else
				{
					set1 = find(pos);
					set2 = find(pos + getNumInSameSet());

					if (set1 != set2)
					{
						union(set1, set2);
						getaMaze()[pos] = getaMaze()[pos].replace("_", " ");
					}
				}
			}
		}
	}

	public int getNumElements()
	{
		return NumElements;
	}

	public void setNumElements(int numElements)
	{
		NumElements = numElements;
	}

	public int getNumInSameSet()
	{
		return NumInSameSet;
	}

	public void setNumInSameSet(int numInSameSet)
	{
		NumInSameSet = numInSameSet;
	}

	public String[] getaMaze()
	{
		return aMaze;
	}

	public void setaMaze(String[] aMaze)
	{
		this.aMaze = aMaze;
	}
}