package Practice;

class sortSearch {
	public static void main(String[] args) {
		int[] arr = { 80, 40, 60, 10, 70, 50, 20, 30 };

		// System.out.println(arr[binarySearch(arr, 10)]);
		selectionSort(arr);
		// bubbleSort(arr);
		// for(int ele:arr)
		// System.out.print(ele+", ");

	}

	public static void selectionSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			int minIndex = i;

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;

			for (int ele : arr)
				System.out.print(ele + ", ");
			System.out.println();
		}
	}

	public static int binarySearch(int[] arr, int ele) {

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int midlen = left + (right - left) / 2;
			if (arr[midlen] == ele)
				return midlen;
			if (arr[midlen] < ele)
				left = midlen + 1;
			else
				right = midlen - 1;
		}

		return -1;

	}

	public static void bubbleSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 0; j < arr.length - i - 1; j++) {

				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			for (int ele : arr)
				System.out.print(ele + ", ");
			System.out.println();

		}
	}

}
