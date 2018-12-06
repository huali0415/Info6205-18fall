import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class test1 {
    private static void huOrThrow(ArrayList<Integer> inHand){
        int[][] reference = {{1,2,3,4,5,6,7,8},
                             {2,2,3,4,5,6,7,8}};
        int n = reference.length;
        int index = 0;
//        int[] temp;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int j = 0; j < 8; j++){
//            System.out.print("10010010010010011111---------------------------------------------------------------------------------");

            for(int i = 0; i < n; i++){
                System.out.println(Integer.toString(j) + "                     121212121212121212121");
                int recorder = 0;
                int pointer = 0;
                while(pointer < 8){
                    int m1 = inHand.get(pointer);
                    int m2 = reference[i][pointer];
                    if(m1 == m2 && j != pointer){
                        recorder++;
                        //index = j;
                        //fw.write(recorder + "\n");
//                        fw.flush();
//                        fw.close();
                    }
                    pointer++;
                }
                System.out.println("Recorder: "+Integer.toString(recorder) + "");
                //p.setScore(Math.max(p.getScore(), recorder));
                // System.out.println(Integer.toString(i));
            }
            //temp.add(p.getScore());
        //}
        //int n1 = p.getThrowList().size();
//        int or = n1 - 14;
//        int i = or;
//        int tempI = 0;
//        while(i < n1){
//            if(tempI <= p.getThrowList().get(i)){
//                index = i;
//                tempI = p.getThrowList().get(i);
//            }
//            i++;
//        }
//        System.out.println(Integer.toString(index) + "                -------------------------");
//        return (index - or);
        }
    }
    public static void main(String[] args){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(2);
        temp.add(2);
        temp.add(2);
        temp.add(3);
        temp.add(4);
        temp.add(5);
        temp.add(6);
        huOrThrow(temp);


    }
}
