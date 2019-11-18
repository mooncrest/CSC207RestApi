package CSC207.CSC207RestApi.api.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.service.Leaderboard.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/leaderboard/testing")
@RestController
public class LeaderboardTester {
    private final LeaderBoardService leaderBoardService;

    @Autowired
    public LeaderboardTester(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @PutMapping(path = "{game}")
    public void addPerson(@PathVariable("game") String game, @RequestBody Score score) {
        leaderBoardService.insertScore(score, game);
    }

    @GetMapping(path = "{game}")
    public LeaderBoard getLeaderBoard(@PathVariable("game") String game) {
        return leaderBoardService.getLeaderBoard(game);
    }

    @GetMapping
    public List<LeaderBoard> getAllScores() {
        return leaderBoardService.getAllScores();
    }

    @PostMapping
    public void addLeaderBoard(@RequestBody LeaderBoard leaderBoard) {
        leaderBoardService.addLeaderBoard(leaderBoard);
    }
}
