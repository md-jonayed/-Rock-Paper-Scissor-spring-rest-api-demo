package com.example.Spring_Boot_REST_API_Demo.Settling.Scores;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ScoreController {

    static ScoreEntity scoreEntity = new ScoreEntity(0,0,0);
    @GetMapping("/health-check")
    public String healthCheck(){
        return "ALL functionalities are working fine";
    }

    @PostMapping("/scores/{changeStateAmount}")
    public ScoreEntity changeState(@PathVariable String changeStateAmount){
        if(changeStateAmount.equalsIgnoreCase("wins")){
            scoreEntity.setWins(scoreEntity.getWins()+1);
        }
        else if(changeStateAmount.equalsIgnoreCase("losses")){
            scoreEntity.setLosses(scoreEntity.getLosses()+1);
        }
        else if(changeStateAmount.equalsIgnoreCase("ties")){
            scoreEntity.setTies(scoreEntity.getTies()+1);
        }
        return scoreEntity;
    }

    @GetMapping("/scores")
    public ScoreEntity getScoreEntity(){
        return scoreEntity;
    }

    @GetMapping("/scores/{state}")
    public int getState(@PathVariable String state){
        if(state.equalsIgnoreCase("wins")){
            return scoreEntity.getWins();
        }
        else if(state.equalsIgnoreCase("losses")){
            return scoreEntity.getLosses();
        }
        else if(state.equalsIgnoreCase("ties")){
            return scoreEntity.getTies();
        }
        return 0;
    }

    @PatchMapping("/scores/{state}")
    public ScoreEntity updateSingleEntity(@RequestParam (name = "new-value") int newValue,
                                          @PathVariable String state){
//        this is how we give request parameter inside postman
        //        localhost:8080/scores/ties?new-value=35
        if(state.equalsIgnoreCase("wins")){
            scoreEntity.setWins(newValue);
        }
        else if(state.equalsIgnoreCase("losses")){
            scoreEntity.setLosses(newValue);
        }
        else if(state.equalsIgnoreCase("ties")){
            scoreEntity.setTies(newValue);
        }
        return scoreEntity;
    }

    @PutMapping("/scores")
    public ScoreEntity replaceScores(@RequestBody ScoreEntity newScore){
        // @RequestBody is converting the json into an object and it can do vice versa
        scoreEntity=newScore;
        return scoreEntity;
    }

    @DeleteMapping("/scores")
    public String  deleteScore(){
        scoreEntity=null;
        return "null";
    }
//    @GetMapping("/scores/wins")
//    public int getWins(){
//        return scoreEntity.getWins();
//    }
//
//
//    @GetMapping("/scores/losses")
//    public int getLosses(){
//        return scoreEntity.getLosses();
//    }
//
//    @GetMapping("/scores/ties")
//    public int getTies(){
//        return scoreEntity.getTies();
//    }
}
