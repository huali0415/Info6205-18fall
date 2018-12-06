import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

//import MaJiangList maJL;
//
public class GetHu {
//
    public ArrayList<ArrayList<MaJiang>> finalRes;
    public MaJiang[][] finalResArray;
//    public ArrayList<ArrayList<MaJiang>> getHu;
    public GetHu(){
//        getMJSinge(maJL.maJiangList);
      // MaJiangList maJL = new MaJiangList();
        MaJiangList maJiangList = new MaJiangList();
        ArrayList<MaJiang> temp = maJiangList.getMaJiangList();
        ArrayList<MaJiang> results = getMJSinge(temp);
        ArrayList<ArrayList<MaJiang[]>> results1 = getHu(results);
        finalRes = finalRes(results1);
        finalResArray = listToArray(finalRes);
    }
    public ArrayList<MaJiang> getMJL(MaJiangList maJL){
        return maJL.getMaJiangList();
    }

    public ArrayList<MaJiang> getMJSinge(ArrayList<MaJiang> maJL){
        ArrayList<MaJiang> result = new ArrayList<>();
        int n = maJL.size();
        for(int i = 0; i < n; i += 4){
            MaJiang temp = maJL.get(i);
            result.add(temp);
        }
        return result;
    }

    public ArrayList<ArrayList<MaJiang[]>> getHu(ArrayList<MaJiang> maJiangSingle){
        ArrayList<ArrayList<MaJiang[]>> getHu = new ArrayList<ArrayList<MaJiang[]>>();
        int n = maJiangSingle.size();
        for(int i = 0; i < n-7; i += 9){
            ArrayList<MaJiang[]> mJArray = new ArrayList<>();
            for(int j = i; j < i + 9; j++){
                if(j < i + 7){
                    MaJiang[] temp1 = new MaJiang[3];
                    temp1[0] = maJiangSingle.get(j);
                    temp1[1] = maJiangSingle.get(j);
                    temp1[2] = maJiangSingle.get(j);
                    mJArray.add(temp1);
                    MaJiang[] temp2 = new MaJiang[3];
                    temp2[0] = maJiangSingle.get(j);
                    temp2[1] = maJiangSingle.get(j + 1);
                    temp2[2] = maJiangSingle.get(j + 2);
                    mJArray.add(temp2);
                }else{
                    MaJiang[] temp31 = new MaJiang[3];
                    temp31[0] = maJiangSingle.get(j);
                    temp31[1] = maJiangSingle.get(j);
                    temp31[2] = maJiangSingle.get(j);
                    mJArray.add(temp31);
                }
            }
            getHu.add(mJArray);
        }
        ArrayList<MaJiang[]> mJArray1 = new ArrayList<>();
        for(int k = n-1; k >= n - 7; k--){
            MaJiang[] temp3 = new MaJiang[2];
            temp3[0] = maJiangSingle.get(k);
            temp3[1] = maJiangSingle.get(k);
            mJArray1.add(temp3);
        }
        getHu.add(mJArray1);

        return getHu;
    }

    public ArrayList<MaJiang[]> extendMJL(ArrayList<MaJiang[]> mJArray){
        ArrayList<MaJiang[]> temp = new ArrayList<>();
        int n = mJArray.size();
        for(int i = 0; i < n; i++){
            MaJiang[] original = mJArray.get(i);
            temp.add(original);
            if(i < 48){
                if(original[0].getNumber() != original[1].getNumber() &&
                        original[0].getNumber() != original[2].getNumber()){
                    MaJiang[] ex1 = swap(original,1,2, 0);
                    temp.add(ex1);
                    MaJiang[] ex2 = swap(original,0,1, 2);
                    temp.add(ex2);
                    MaJiang[] ex3 = swap(original,0,2, 1);
                    temp.add(ex3);
                    MaJiang[] ex4 = swap(swap(original,0,1, 2), 1,2, 0);
                    temp.add(ex4);
                    MaJiang[] ex5 = swap(swap(original,0,2, 1), 1,2, 0);
                    temp.add(ex5);
                }
            }else {
                MaJiang[] original1 = mJArray.get(i);
                temp.add(original);
            }
        }
        return temp;
    }

    public MaJiang[] swap(MaJiang[] mJ, int i, int j, int k){
        MaJiang[] temp = new MaJiang[3];
        temp[k] = mJ[k];
        temp[i] = mJ[j];
        temp[j] = mJ[i];
        return temp;
    }

    public ArrayList<ArrayList<MaJiang>> finalRes(ArrayList<ArrayList<MaJiang[]>> temp){
        ArrayList<ArrayList<MaJiang>> finalRes = new ArrayList<ArrayList<MaJiang>>();
        ArrayList<MaJiang[]> tiao = temp.get(0);
        ArrayList<MaJiang[]> bing = temp.get(1);
        ArrayList<MaJiang[]> wan = temp.get(2);
        ArrayList<MaJiang[]> zi = temp.get(3);
        for(int i = 0; i < 3; i++){
            if(i == 0)
                arrayToAL(tiao, bing, wan, zi, finalRes);
            else if(i == 1)
                arrayToAL(bing, tiao, wan, zi, finalRes);
            else
                arrayToAL(wan, bing, tiao, zi, finalRes);

        }
        return finalRes;
    }

    public void arrayToAL(ArrayList<MaJiang[]> tiao, ArrayList<MaJiang[]> bing,
                          ArrayList<MaJiang[]> wan, ArrayList<MaJiang[]> zi,
                          ArrayList<ArrayList<MaJiang>> finalRes){
        for(int t = 0; t < 16; t++){
            for(int t2 = t + 1; t2 < 16; t2++){
                for(int b = 0; b < 16; b++){
                    for(int w = 0; w < 16; w++){
                        for(int z = 0; z < 7; z++){
                            ArrayList<MaJiang> tempMJ = new ArrayList<>();
                            tempMJ.add(tiao.get(t)[0]);
                            tempMJ.add(tiao.get(t)[1]);
                            tempMJ.add(tiao.get(t)[2]);
                            tempMJ.add(tiao.get(t2)[0]);
                            tempMJ.add(tiao.get(t2)[1]);
                            tempMJ.add(tiao.get(t2)[2]);
                            tempMJ.add(bing.get(b)[0]);
                            tempMJ.add(bing.get(b)[1]);
                            tempMJ.add(bing.get(b)[2]);
                            tempMJ.add(wan.get(w)[0]);
                            tempMJ.add(wan.get(w)[1]);
                            tempMJ.add(wan.get(w)[2]);
                            tempMJ.add(zi.get(z)[0]);
                            tempMJ.add(zi.get(z)[1]);
                            sortInHand(tempMJ);
                            finalRes.add(tempMJ);
                        }
                    }
                }
            }
        }
    }

    public void sortInHand(ArrayList<MaJiang> list){
        for(int i = 1; i < list.size(); ++i){
            for(int j = i; j > 0; j--){
                MaJiang m2 = list.get(j);
                String s2 = m2.getName();
                MaJiang m3 = list.get(j - 1);
                String s3 = m2.getName();
                if(s3 != s2){
                    break;
                }
                else{
                    int i1 = m3.getNumber();
                    int i2 = m2.getNumber();
                    if(s3 == s2 && i1 >= i2 )
                        Collections.swap(list, j - 1, j) ;
                }
            }
        }
        list.sort(Comparator.comparing(MaJiang::getName));
    }
    public MaJiang[][] listToArray(ArrayList<ArrayList<MaJiang>> finalRes){
        finalResArray = new MaJiang[finalRes.size()][14];
        for(int i = 0; i < finalRes.size(); i++){
            for(int j = 0; j < 14; j++){
                finalResArray[i][j] = finalRes.get(i).get(j);

            }
        }
        return finalResArray;
    }
    public static void main(String[] args) {
        GetHu gh = new GetHu();
        MaJiang[][] k = gh.finalResArray;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 14; j++) {
                MaJiang mj = k[i][j];
                System.out.print(Integer.toString(mj.getNumber()) + ":" + mj.getName() + ", ");
            }
            System.out.println("-");
        }
    }
////
//        MaJiangList maJiangList = new MaJiangList();
//        ArrayList<MaJiang> temp = maJiangList.getMaJiangList();
//        ArrayList<MaJiang> results = gh.getMJSinge(temp);
//        ArrayList<ArrayList<MaJiang[]>> results1 = gh.getHu(results);
////        System.out.println(Integer.toString(results1.size()));
////        System.out.println(results1.get(0).get(0)[0].getName());
////        System.out.println(results1.get(1).get(0)[0].getName());
////        System.out.println(results1.get(2).get(0)[0].getName());
////        System.out.println(results1.get(3).get(0)[0].getName());
////        System.out.println(Integer.toString(results1.size()));
//        gh.finalRes = gh.finalRes(results1);
////        ArrayList<MaJiang[]> result = gh.extendMJL(results1);
////        for(MaJiang[] mj : result){
////            if(mj.length == 3){
////                String value1 = Integer.toString(mj[0].getNumber());
////                String name1 = mj[0].getName();
////                String value2 = Integer.toString(mj[1].getNumber());
////                String name2 = mj[1].getName();
////                String value3 = Integer.toString(mj[2].getNumber());
////                String name3 = mj[2].getName();
////                System.out.println("[ " + value1 + "+" + name1 + ", " + value2 + "+" + name2 + ", " + value3 + "+" + name3 + " ]");
////            }else{
////                String value1 = Integer.toString(mj[0].getNumber());
////                String name1 = mj[0].getName();
////                String value2 = Integer.toString(mj[1].getNumber());
////                String name2 = mj[1].getName();
////                System.out.println("[ " + value1 + "+" + name1 + ", " + value2 + "+" + name2 + " ]");
////            }
////
////        }
////        int size = gh.finalRes.size();
////        //System.out.println(Integer.toString(size));
////        Random rand = new Random();
////
////        for(int i = 0; i < 10; i++){
////            int t = rand.nextInt(size);
////            ArrayList<MaJiang> temp1 = gh.finalRes.get(t);
////
////            String value1 = Integer.toString(temp1.get(0).getNumber());
////            String name1 = temp1.get(0).getName();
////            String value2 = Integer.toString(temp1.get(1).getNumber());
////            String name2= temp1.get(1).getName();
////            String value3 = Integer.toString(temp1.get(2).getNumber());
////            String name3= temp1.get(2).getName();
////            String value4 = Integer.toString(temp1.get(3).getNumber());
////            String name4= temp1.get(3).getName();
////            String value5 = Integer.toString(temp1.get(4).getNumber());
////            String name5= temp1.get(4).getName();
////            String value6 = Integer.toString(temp1.get(5).getNumber());
////            String name6= temp1.get(5).getName();
////            String value7= Integer.toString(temp1.get(6).getNumber());
////            String name7= temp1.get(6).getName();
////            String value8 = Integer.toString(temp1.get(7).getNumber());
////            String name8= temp1.get(7).getName();
////            String value9 = Integer.toString(temp1.get(8).getNumber());
////            String name9= temp1.get(8).getName();
////            String value10 = Integer.toString(temp1.get(9).getNumber());
////            String name10 = temp1.get(9).getName();
////            String value11 = Integer.toString(temp1.get(10).getNumber());
////            String name11 = temp1.get(10).getName();
////            String value12 = Integer.toString(temp1.get(11).getNumber());
////            String name12 = temp1.get(11).getName();
////            String value13 = Integer.toString(temp1.get(12).getNumber());
////            String name13 = temp1.get(12).getName();
////            String value14 = Integer.toString(temp1.get(13).getNumber());
////            String name14 = temp1.get(13).getName();
////            System.out.println(
////                    "[ " + value1 + ":" + name1 + ", " + value2 + ":" + name2 + ", " + value3 + ":" + name3 + ", "
////                           + value4 + ":" + name4 + ", " + value5 + ":" + name5 + ", " + value6 + ":" + name6 + ", "
////                           + value7 + ":" + name7 + ", " + value8 + ":" + name8 + ", " + value9 + ":" + name9 + ", "
////                           + value10 + ":" + name10 + ", " + value11 + ":" + name11 + ", " + value12 + ":" + name12 + ", "
////                           + value13 + ":" + name13 +", "+ value14 + ":" + name14+
////                    " ]");
////        }
//
//    }
}
