public class Main {
    public static void main(String[] args) {
        Integer[] data={8,14,24,34,35,56};
        int res=BinarySearch.searchR(data,34);
        int res2=BinarySearchNR.search(data,34);
        System.out.println(res);
        System.out.println(res2);
        System.out.println(Upper.upper(data,13));
        System.out.println(Ceil.upper_ceil(data,14));
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for(int i = 0; i <= 6; i ++) {
            System.out.print(Upper.upper(arr, i) + " ");
            System.out.print(Ceil.upper_ceil(arr,i)+" ");
            System.out.print(Ceil.lower_ceil(arr,i)+" ");
            System.out.print(Lower.lower(arr,i)+" ");
        }
        System.out.println();

        System.out.print("lower_floor : ");
        for(int i = 0; i <= 6; i ++)
            System.out.print(Floor.lower_floor(arr, i) + " ");
        System.out.println();

        System.out.print("upper_floor : ");
        for(int i = 0; i <= 6; i ++)
            System.out.print(Floor.upper_floor(arr, i) + " ");
        System.out.println();
    }
}
