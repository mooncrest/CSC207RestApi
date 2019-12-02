package CSC207.CSC207RestApi.api.Leaderboard;

import CSC207.CSC207RestApi.model.*;
import CSC207.CSC207RestApi.service.Leaderboard.JsonLeaderBoardService;
import CSC207.CSC207RestApi.service.Leaderboard.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/leaderboard/testing")
@RestController
public class LeaderboardTester {

    private final LeaderBoardService leaderBoardService;

    @Autowired
    public LeaderboardTester(@Qualifier("jsonLeaderService") LeaderBoardService leaderBoardService ) {
        this.leaderBoardService = leaderBoardService;
    }

    /**
     * gets the leaderboard
     * @param game the name of the leaderboard
     * @return the leaderboard if it exists
     */
    @PostMapping(path = "{game}")
    public LeaderBoard getLeaderBoard(@PathVariable("game") String game) {
        return leaderBoardService.getLeaderBoard(game);
    }

    /**
     * gets all the leaderboards used by admins only
     * @return the whole entire leaderboard data base
     */
    @GetMapping
    public LeaderBoardDataBase getAllScores() {
        return leaderBoardService.getAllScores();
    }

    /**
     * adds a leaderboard to the database used by admins only
     * @param leaderBoard the leaderboard to be added
     */
    @PostMapping
    public void addLeaderBoard(@RequestBody LeaderBoard leaderBoard) {
        leaderBoardService.addLeaderBoard(leaderBoard);
    }
}
