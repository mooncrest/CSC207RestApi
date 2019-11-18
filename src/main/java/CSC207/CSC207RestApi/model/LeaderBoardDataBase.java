package CSC207.CSC207RestApi.model;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardDataBase {
    private List<LeaderBoard> leaderBoards= new ArrayList<>();


    public void addLeaderBoard(LeaderBoard leaderBoard) {
        leaderBoards.add(leaderBoard);
    }

    public List<LeaderBoard> getLeaderBoards() {
        return leaderBoards;
    }

    public void setLeaderBoards(List<LeaderBoard> leaderBoards) {
        this.leaderBoards = leaderBoards;
    }
}
