public class QuickortingTest{

    public static void main(String[] args){

        System.out.println("Starting proper quick sorting funciton");
        QuicksortInPlace.quickSort(new int[]{1,3,5,3,9,0,10,35,32,2,5,83,8,57,2,49}, 0,15);
        System.out.println("End");

        System.out.println("\nStarting improper quick sorting funciton");

        QuickSortInPlaceFailure.quickSort(new int[]{1,3,5,3,9,0,10,35,32,2,5,83,8,57,2,49}, 100,15);
        System.out.println("End");

    }
}
