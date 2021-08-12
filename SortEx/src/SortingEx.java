import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Elcim Onec
 *
 */
public class SortingEx {
	public static int randomFill() {
		Random rand = new Random();
		int randomNum = rand.nextInt();
		return (randomNum % 1000) + 1000;
	}

	public static void selection_sort(int[] anArray, int size) {
		for (int i = 0; i < anArray.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < anArray.length; j++) {
				if (anArray[j] < anArray[index]) {
					index = j;// searching for lowest index
				}
			}
			if (index != i) {
				int temp = anArray[index];
				anArray[index] = anArray[i];
				anArray[i] = temp;
			}
		}
	}

	public static void insertion_sort(int[] anArray, int size) {
		int j = 0;
		int temp = 0;
		for (int i = 1; i < anArray.length; i++) {
			j = i;
			temp = anArray[i];
			while ((j > 0) && (anArray[j - 1] > temp)) {
				anArray[j] = anArray[j - 1];
				j--;
			}
			anArray[j] = temp;
		}

	}

	public static void merge(int[] anArray, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;

		int L[] = new int[n1];
		int M[] = new int[n2];

		for (int i = 0; i < n1; i++)
			L[i] = anArray[p + i];
		for (int j = 0; j < n2; j++)
			M[j] = anArray[q + 1 + j];

		int i, j, k;
		i = 0;
		j = 0;
		k = p;

		while (i < n1 && j < n2) {
			if (L[i] <= M[j]) {
				anArray[k] = L[i];
				i++;
			} else {
				anArray[k] = M[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			anArray[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			anArray[k] = M[j];
			j++;
			k++;
		}
	}

	public static void merge_sort(int[] anArray, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;

			merge_sort(anArray, l, m);
			merge_sort(anArray, m + 1, r);
			merge(anArray, l, m, r);
		}
	}

	public static int partition(int[] anArray, int low, int high) {
		int pivot = anArray[high];
		int i = (low - 1);

		for (int j = low; j < high; j++) {
			if (anArray[j] <= pivot) {
				i++;
				int temp = anArray[i];
				anArray[i] = anArray[j];
				anArray[j] = temp;
			}
		}
		int temp = anArray[i + 1];
		anArray[i + 1] = anArray[high];
		anArray[high] = temp;
		return (i + 1);

	}

	public static void quick_sort(int[] anArray, int low, int high) {
		if (low < high) {

			// Select pivot position and put all the elements smaller than pivot on left and
			// greater than pivot on right
			int pi = partition(anArray, low, high);

			// Sort the elements on the left of pivot
			quick_sort(anArray, low, pi - 1);

			// Sort the elements on the right of pivot
			quick_sort(anArray, pi + 1, high);
		}

	}

	public static void main(String[] args) {
		System.out.print("Veri boyutu: ");
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		int anArray[] = new int[size];
		int choice = 1;

		if (args.length > 0) {
			try {
				size = Integer.parseInt(args[0]);
				choice = Integer.parseInt(args[1]);
				System.out.println(size + choice);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		}
		if (choice >= 1 && choice <= 4) {
			anArray = new int[size];
			for (int i = 0; i < anArray.length; i++) {
				anArray[i] = randomFill();
			}

			double startTime = System.nanoTime();

			System.out.print("Secim yapiniz (1= Selection sort  2= Insertion sort  3= Merge sort  4= Quick sort): ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Selection sort is selected\n");
				selection_sort(anArray, size);
			}
			if (choice == 2) {
				System.out.println("Insertion sort is selected\n");
				insertion_sort(anArray, size);
			}
			if (choice == 3) {
				System.out.println("Merge sort is selected\n");
				merge_sort(anArray, size, choice);
			}
			if (choice == 4) {
				System.out.println("Quick sort is selected\n");
				quick_sort(anArray, size, choice);
			}

			double endTime = System.nanoTime();

			double duration = (endTime - startTime) / 1000000; // divide by 1000000 to get milliseconds.
			System.out.println("it took: " + duration + " milliseconds");
		}
	}
}
