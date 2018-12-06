import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Play {

    public MaJiangList maJiangList;
    public Player p1;
    public Player p2;
    public Player p3;
    public Player p4;
    public ArrayList<MaJiang> onTable;
    public int size;
    public int[] temp;
    public GetHu getHu;

    public Play()throws IOException{
        System.out.print("1111111111");
        maJiangList = new MaJiangList();
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();
        p4 = new Player();
        onTable = new ArrayList<MaJiang>();
        getHu = new GetHu();
        getHand();
        start();
    }

    public void getHand(){
        //System.out.print("222222222");
        Random rand = new Random();
        int size = maJiangList.getMaJiangList().size();
        for(int i = 0; i < 4; i++){
            //System.out.print("3333333333");
            if(i == 0){
//                for(int j = 0; j < 13; j++){
//                    //System.out.print("4444444444");
//                    int temp = rand.nextInt(size);
//                    MaJiang mj = maJiangList.getMaJiangList().get(temp);
//                    p1.inHand.add(mj);
//                    maJiangList.getMaJiangList().remove(mj);
//                    size = maJiangList.getMaJiangList().size();
//                }
                MaJiang mB1 = new MaJiang(); mB1.setNumber(1); mB1.setName("bing"); p1.inHand.add(mB1);
                MaJiang mB12 = new MaJiang(); mB12.setNumber(1); mB12.setName("bing");p1.inHand.add(mB12);
                MaJiang mB13 = new MaJiang(); mB13.setNumber(1); mB13.setName("bing");p1.inHand.add(mB13);
                MaJiang mB14 = new MaJiang(); mB14.setNumber(1); mB14.setName("bing");p1.inHand.add(mB14);
                MaJiang mB2 = new MaJiang(); mB2.setNumber(2); mB2.setName("bing");p1.inHand.add(mB2);
                MaJiang mB22 = new MaJiang(); mB22.setNumber(2); mB22.setName("bing");p1.inHand.add(mB22);
                MaJiang mB23 = new MaJiang(); mB23.setNumber(2); mB23.setName("bing");p1.inHand.add(mB23);
                MaJiang mB24 = new MaJiang(); mB24.setNumber(2); mB24.setName("bing");p1.inHand.add(mB24);
                MaJiang mB3 = new MaJiang(); mB3.setNumber(3); mB3.setName("bing");p1.inHand.add(mB3);
                MaJiang mB32 = new MaJiang(); mB32.setNumber(3); mB32.setName("bing");p1.inHand.add(mB32);
                MaJiang mB33 = new MaJiang(); mB33.setNumber(3); mB33.setName("bing");p1.inHand.add(mB33);
                MaJiang mB34 = new MaJiang(); mB34.setNumber(3); mB34.setName("bing");p1.inHand.add(mB34);
                MaJiang mB4 = new MaJiang(); mB4.setNumber(4); mB4.setName("bing");p1.inHand.add(mB4);
//                MaJiang mB42 = new MaJiang(); mB42.setNumber(4); mB42.setName("bing");p1.inHand.add(mB42);

            }

            else if(i == 1){
                for(int j = 0; j < 13; j++){
                    System.out.print("55555555555");
                    int temp = rand.nextInt(size);
                    MaJiang mj = maJiangList.getMaJiangList().get(temp);
                    p2.inHand.add(mj);
                    maJiangList.getMaJiangList().remove(mj);
                    size = maJiangList.getMaJiangList().size();
                }
            }

            else if(i == 2){
                for(int j = 0; j < 13; j++){
                    System.out.print("6666666666");
                    int temp = rand.nextInt(size);
                    MaJiang mj = maJiangList.getMaJiangList().get(temp);
                    p3.inHand.add(mj);
                    maJiangList.getMaJiangList().remove(mj);
                    size = maJiangList.getMaJiangList().size();
                }
            }

            else if(i == 3){
                for(int j = 0; j < 13; j++){
                    System.out.print("7777777777");
                    int temp = rand.nextInt(size);
                    MaJiang mj = maJiangList.getMaJiangList().get(temp);
                    p4.inHand.add(mj);
                    maJiangList.getMaJiangList().remove(mj);
                    size = maJiangList.getMaJiangList().size();
                }
            }
        }
    }

    public void start()throws IOException{
        Random rand = new Random();
        int first = 0;
        boolean condition = true;
        size =  maJiangList.getMaJiangList().size();
        int position = 0;
        while(condition && size > 0){
            size = maJiangList.getMaJiangList().size();
            int temp = rand.nextInt(size);
            MaJiang mj = maJiangList.getMaJiangList().get(temp);
            maJiangList.getMaJiangList().remove(mj);

            if(first == 0){
                int k = rand.nextInt(2);
                if(onTable.size() != 0){
                    if(k == 0)
                        p1.inHand.add(position, mj);
                    else{
                        p1.inHand.add(position, onTable.get(onTable.size() - 1));
                        onTable.remove(onTable.size() - 1);
                    }

                }else
                    p1.inHand.add(position, mj);
                File file = new File("Output Result.txt");
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                sortInHand(p1.inHand);

                if(whetherHu(p1, getHu) == false){
                    position = huOrThrow(p1.inHand, p1, getHu, fw);
                    MaJiang lose = p1.inHand.get(position);

                    onTable.add(lose);
                    p1.deleteInHand(lose);
                    first++;
                }else{
                    condition = false;
                    System.out.println("Congratulations!! Player1 is Win!!!!");
                    break;

                }
                continue;
            }

            else if(first == 1){

                int k = rand.nextInt(14);
//                if(onTable != null){
//                    if(k == 0)
//                        p2.inHand.add(mj);
//                    else{
//                        p2.inHand.add(onTable.get(onTable.size() - 1));
//                        onTable.remove(onTable.size() - 1);
//                    }
//                }else
                p2.inHand.add(mj);
//                sortInHand(p2.inHand);
                if(whetherHu(p2, getHu) == false){
                    MaJiang lose = p2.inHand.get(k);
                    onTable.add(lose);
                    p2.deleteInHand(lose);
                    first++;
                }else{
                    condition = false;
                    System.out.println("Congratulations!! Player2 is Win!!!!");
                    break;
                }
                continue;
            }

            else if(first == 2){
                int k = rand.nextInt(14);
//                if(onTable != null){
//                    if(k == 0)
//                        p3.inHand.add(mj);
//                    else{
//                        p3.inHand.add(onTable.get(onTable.size() - 1));
//                        onTable.remove(onTable.size() - 1);
//                    }
//                }else
                p3.inHand.add(mj);
//                sortInHand(p3.inHand);
                if(whetherHu(p3, getHu) == false){
                    MaJiang lose = p3.inHand.get(k);
                    onTable.add(lose);
                    p3.deleteInHand(lose);
                    first++;
                }else{
                    condition = false;
                    System.out.println("Congratulations!! Player3 is Win!!!!");
                    break;
                }
                continue;
            }

            else{
                int k = rand.nextInt(14);
//                if(onTable != null){
//                    if(k == 0)
//                        p4.inHand.add(mj);
//                    else{
//                        p4.inHand.add(onTable.get(onTable.size() - 1));
//                        onTable.remove(onTable.size() - 1);
//                    }
//                }else
                p4.inHand.add(mj);
//                sortInHand(p4.inHand);
                if(whetherHu(p4, getHu) == false){
                    MaJiang lose = p4.inHand.get(k);
                    onTable.add(lose);
                    p4.deleteInHand(lose);
                    first = 0;
                }else{
                    condition = false;
                    System.out.println("Congratulations!! Player1 is Win!!!!");
                    break;
                }
                continue;
            }
        }
    }

    public boolean whetherHu(Player p, GetHu getHu){
        System.out.print("99999999999");
        MaJiang[][] reference = getHu.finalResArray;
        ArrayList<MaJiang> inHand = p.inHand;
        int n = reference.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 14; j++){
                MaJiang mj1 = reference[i][j];
                MaJiang mj2 = inHand.get(j);
                if(mj1 != mj2)
                    return false;
            }

        }
        return true;
    }

    private int huOrThrow(ArrayList<MaJiang> inHand, Player p, GetHu getHu, FileWriter fw) throws IOException {
        MaJiang[][] reference = getHu.finalResArray;
        int n = reference.length;
        int index = 0;
        ArrayList<Integer> temp = p.getThrowList();
        for(int j = 0; j < 14; j++){
            for(int i = 0; i < n; i++){
                int recorder = 0;
                int pointer = 0;
                while(pointer < 14){
                    MaJiang m1 = inHand.get(pointer);
                    MaJiang m2 = reference[i][pointer];
                    if(m1.getName().equals(m2.getName()) && m1.getNumber() == m2.getNumber() && pointer!=j){
                        recorder++;
                    }
                    pointer++;
                }
                System.out.println("Number of MaJiang: " + Integer.toString(j) + "-- " + "Recorder: "+Integer.toString(recorder) + "");
                if(recorder != 0){
                    String newLine = System.getProperty("line.separator");
                    fw.write("Number of MaJiang: " + Integer.toString(j) + "-- " + "Recorder: "+Integer.toString(recorder) + newLine);
                }
                p.setScore(Math.max(p.getScore(), recorder));
            }
            temp.add(p.getScore());
        }
        int n1 = p.getThrowList().size();
        int or = n1 - 14;
        int i = or;
        int tempI = 0;
        while(i < n1){
            if(tempI <= p.getThrowList().get(i)){
                index = i;
                tempI = p.getThrowList().get(i);
            }
            i++;
        }
        return (index - or);
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

    public static void main(String[] args) throws IOException{
        Play p = new Play();

    }



}
