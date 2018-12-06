import java.util.*;

public class test {
    public void sortInHand(ArrayList<MaJiang> list) {
        for (int i = 1; i < list.size(); ++i) {
            for (int j = i; j > 0; j--) {
                MaJiang m2 = list.get(j);
                String s2 = m2.getName();
                MaJiang m3 = list.get(j - 1);
                String s3 = m2.getName();
                if (s3 != s2) {
                    break;
                } else {
                    int i1 = m3.getNumber();
                    int i2 = m2.getNumber();
                    if (s3 == s2 && i1 >= i2)
                        Collections.swap(list, j - 1, j);
                }
            }
        }
        list.sort(Comparator.comparing(MaJiang::getName));
    }

//    private void swap(ArrayList<MaJiang> list, int i, int j) {
////        MaJiang temp = list.get(i);
////        MaJiang temp1 = list.get(j);
////        list.remove(i);
////        list.add(i, temp1);
////        list.remove(j);
////        list.add(j, temp);
//        Collections.swap(list, i, j);
//    }

    public static void main(String[] args){
        test t1 = new test();
        MaJiangList mjl = new MaJiangList();
        int n = mjl.getMaJiangList().size();
        Random r = new Random();
        int k = 0;
        while(k < 10){
            ArrayList<MaJiang> tempMJ = new ArrayList<>();
            for(int i = 0; i < 14; i++){
                int t = r.nextInt(n);
                tempMJ.add(mjl.getMaJiangList().get(t));
            }
            for(int j = 0; j < tempMJ.size(); j++){
                System.out.print(Integer.toString(tempMJ.get(j).getNumber()) + ":" + tempMJ.get(j).getName() + ", ");
            }
            System.out.println(".. After Sort:");
            t1.sortInHand(tempMJ);
            for(int j = 0; j < tempMJ.size(); j++){
                System.out.print(Integer.toString(tempMJ.get(j).getNumber()) + ":" + tempMJ.get(j).getName() + ", ");
            }
            System.out.println("..");
            k++;
        }

    }
}
