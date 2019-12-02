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

    @PostMapping(path = "{game}")
    public LeaderBoard getLeaderBoard(@PathVariable("game") String game) {
        return leaderBoardService.getLeaderBoard(game);
    }

    @GetMapping
    public LeaderBoardDataBase getAllScores() {
        return leaderBoardService.getAllScores();
    }

    @PostMapping
    public void addLeaderBoard(@RequestBody LeaderBoard leaderBoard) {
        leaderBoardService.addLeaderBoard(leaderBoard);
    }
}
