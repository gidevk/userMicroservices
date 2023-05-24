package Controller;

import Entity.vo.GameRequestPayload;
import Service.ColourGameService;
import com.expriment.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class ColourGame {

    @Autowired
    ColourGameService colourGameService;

    @PostMapping(value="/play", produces = {MediaType.APPLICATION_JSON_VALUE})///personalDetails/savePersonalDetails
    public ResponseEntity<?> savePersonalDetails(@RequestBody GameRequestPayload gameRequestPayload){
        return colourGameService.gamePlayResult(gameRequestPayload);
    }
}
