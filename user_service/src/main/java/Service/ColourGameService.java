package Service;

import Entity.vo.GameRequestPayload;
import org.springframework.http.ResponseEntity;

public interface ColourGameService {

    ResponseEntity<?> gamePlayResult(GameRequestPayload requestPayload);

    int gameResult();
}
