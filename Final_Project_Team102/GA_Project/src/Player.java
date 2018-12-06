import java.util.ArrayList;

public class Player {
    public int id;
    public String Name;
    public ArrayList<MaJiang> inHand;
    public int score;
    public ArrayList<Integer> throwList;

    public Player(){
        inHand = new ArrayList<MaJiang>();
        throwList = new ArrayList<>();
        score = 0;
    }
    public ArrayList<Integer> getThrowList(){

        return throwList;
    }

    public ArrayList<MaJiang> getInHand(){
        return inHand;
    }

    public MaJiang getInHandbyNumber(int num){
        for(MaJiang mj : inHand){
            if(mj.getNumber() == num)
                return mj;
        }
        return null;
    }

    public MaJiang getInHandbyName(String name){
        for(MaJiang mj : inHand){
            if(mj.getName() == name)
                return mj;
        }
        return null;
    }

    public MaJiang getInHandbyMJ(String name, int num){
        for(MaJiang mj : inHand){
            if(mj.getName() == name && mj.getNumber() == num)
                return mj;
        }
        return null;
    }

    public void addInHand(MaJiang mj){
        inHand.add(mj);
    }

    public void deleteInHand(MaJiang mj){
        inHand.remove(mj);
    }

    public int getScore(){
        return score;
    }

    public void setScore(int i){
        score = i;
    }
}
