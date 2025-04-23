import java.util.ArrayList;
import java.util.List;

public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {

        for(int i = 0 ; i < values.length ; i++)
        {
            for(int j = i + 1 ; j < values.length ; j++)
            {
                if(values[i]*values[j] == target)
                {
                    return new int []{i , j};
                }
            }
        }
        return new int[]{};
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {

        if (rows == 0 || cols == 0 || values == null)
        {
            return new int[0];
        }

        int[] result = new int[rows * cols];
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        int direction = 0; // Right : 0, Down : 1, Left : 2, Up : 3
        int index = 0;

        while (top <= bottom && left <= right)
        {
            if (direction == 0) // Right
            {
                for (int i = left; i <= right; i++)
                {
                    result[index++] = values[top][i];
                }
                top++;
            }
            else if (direction == 1) // Down
            {
                for(int i = top; i <= bottom; i++)
                {
                    result[index++] = values[i][right];
                }
                right--;
            }
            else if (direction == 2) // Left
            {
                for (int i = right; i >= left; i--)
                {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }
            else if (direction == 3) // Up
            {
                for (int i = bottom; i >= top; i--)
                {
                    result[index++] = values[i][left];
                }
                left++;
            }
            direction = (direction + 1) % 4;
        }

        return result;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array5
    */
    public int[][] intPartitions(int n) {
        List<List<Integer>> result = new ArrayList<>();
        generatePartitions(n, n, new ArrayList<>(), result);
        int[][] partitions = new int[result.size()][];
        for (int i = 0; i < result.size(); i++)
        {
            List<Integer> partition = result.get(i);
            partitions[i] = partition.stream().mapToInt(Integer::intValue).toArray();
        }
        return partitions;
    }


    private void generatePartitions(int remaining, int maxNum, List<Integer> current, List<List<Integer>> result)
    {
        if (remaining == 0)
        {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = Math.min(remaining, maxNum); i >= 1; i--)
        {
            current.add(i);
            generatePartitions(remaining - i, i, current, result);
            current.removeLast();
        }
    }

    public static void main(String[] args) {
        // you can test your code here
    }
}