/*You are given an integer array height of length n... There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i])... Find two lines that together with the x-axis form a container, such that the container contains the most water... Return the maximum amount of water a container can store... Notice that you may not slant the container...
 * Eg 1: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]      Output = 49
 * Eg 2: height = [1, 1]                           Output = 1
 */
import java.util.*;
public class MaximumWater
{
    public int MaxArea(int[] height)
    {   // Greedy Programming is used in this case... since we iterate over the array only once...
        int i = 0, j = height.length-1;       // Starting j with the last index...
        int dist = 0, min = 0, area = 0;       // Starting i with the first index...
        min = Math.min(height[i], height[j]);
        dist = j - i;
        area = dist * min;
        if(height.length == 2)     // There is only one case possible...
            return area;
        int loop = 1;
        do
        {
            if(i == height.length-2)
                break;
            if(Math.min(height[i+1], height[j]) * (j-(i+1)) >= area)
            {      // If new area is larger... when incrementing i...
                area = Math.min(height[i+1], height[j]) * (j-(i+1));

            }
            if(Math.min(height[i], height[j]) * (j-i) >= area)
            {
                area = Math.min(height[i], height[j]) * (j-i);
            }
            if(Math.min(height[i], height[j-1]) * (j-1-i) >= area)
            {     // If new area is larger... when decrementing j...
                area = Math.min(height[i], height[j-1]) * (j-1-i);
            }
            j--;    // Incrementing j...
            if(j <= 1)
            {
                j = height.length-1;
                i++;    // Incrementing i...
            }
        }while(loop == 1);
        return area;
    }
    public int OptimalMaxArea(int[] height)
    {   // Dynamic Programming is used...
        int i = 0, j = height.length-1;   // basic pointer initialization...
        int max = 0;
        while(i < j)
        {
            int width = j - i;     // Finding the area...
            int min = Math.min(height[i], height[j]);
            int area = min * width;
            max = Math.max(area, max);    // Getting the maximum area...
            if(height[i] < height[j])     // Incrementing i...
                i++;
            else if(height[i] > height[j])  // Decrementing j...
                j--;
            else
            {          // Else updating both pointers...
                i++;
                j--;
            }
        }
        return max;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int len, a;
        System.out.print("Enter length of the Array : ");
        len = sc.nextInt();
        int height[] = new int[len];
        for(int i = 0; i < len; i++)
        {
            System.out.print("Enter height of "+(i+1)+" bar : ");
            a = sc.nextInt();
            height[i] = a;
        }
        System.out.println("The Height Array formed is : ");
        for(int i = 0; i < len; i++)
            System.out.print(height[i]+", ");
        System.out.println();
        MaximumWater maximum = new MaximumWater();     // Object creation...
        System.out.println("Dynamic Programming Method !! ");
        System.out.println("The Maximum Water is : "+maximum.MaxArea(height));
        System.out.println("Optimal Solution Method !!");
        System.out.println("The Maximum Water is : "+maximum.OptimalMaxArea(height));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n) space...
// Optimal Time Complexity  - 0(n) time...
// Optimal Space Complexity - O(1) space...

/* DEDUCTIONS:-
 * 1. The Area will depend upon the distance and the minimum of the two edges...
 * 2. The increase in area can only be done when the minimum of the edges is greater or the distance...
 * 3. Since we have to iterate over each edge at-least once the maximizing of edges is only the limiting case...
 */