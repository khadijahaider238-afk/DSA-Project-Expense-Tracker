public class Utility {


    public static void mergeSort(Expense[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }


    private static void merge(Expense[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Expense[] leftArr  = new Expense[n1];
        Expense[] rightArr = new Expense[n2];

        for (int i = 0; i < n1; i++) leftArr[i]  = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].getAmount() <= rightArr[j].getAmount()) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
    public static int binarySearch(Expense[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].getId() == target) {
                return mid;
            } else if (arr[mid].getId() < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static void printTableHeader() {
        System.out.println("| ID   | Title                | Amount         | Category     | Date         |");

    }
    public static void printTableFooter() {
        System.out.println("+------+----------------------+----------------+--------------+--------------+");
    }
}
