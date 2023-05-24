package Service.Impl;

import DOA.WinRecordDAO;
import Entity.WinRecord;
import Entity.vo.GameResponse;
import Service.ColourGameService;
import DOA.ColourDAO;
import Entity.vo.GameRequestPayload;
import com.expriment.utils.audit.LoggerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class ColourGameServiceImpl implements ColourGameService {

    @Autowired
    ColourDAO colourDAO;

    @Autowired
    WinRecordDAO winRecordDAO;

    @Override
    public ResponseEntity<?> gamePlayResult(GameRequestPayload requestPayload){
        int periodNo;
        int hour,min;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int result;
        GameRequestPayload gameRequestPayload=new GameRequestPayload();
        GameResponse response=new GameResponse();
        try {
            calendar.setTime(date);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
            LoggerClass.appLayerLogger.info("Hour: {}", (60*hour+min));
            periodNo= (60*hour+min)/3;

            result = gameResult();

            gameRequestPayload.setPeriodNumber(periodNo);
            gameRequestPayload.setResultColourCode(result);

            float playGameResponse= playGame(gameRequestPayload);
            response.setBattingAmount(gameRequestPayload.getBattingAmount());
            response.setColourCode(gameRequestPayload.getColourCode());
            response.setPriseAmount(playGameResponse);
            response.setProjectCode(gameRequestPayload.getProjectCode());
            response.setCpId(gameRequestPayload.getCpId());
            response.setResultColourCode(gameRequestPayload.getResultColourCode());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private float playGame(GameRequestPayload requestPayload){
        float winPrice=0;
        WinRecord winRecord = new WinRecord();
//        GameRequestPayload response= new GameRequestPayload();

        try {
            LoggerClass.appLayerLogger.info("calling PlayGame for {} batting amount {} for project code {}"
                    ,requestPayload.getCpId(),requestPayload.getBattingAmount(),requestPayload.getProjectCode());
            winRecord.setCpId(requestPayload.getCpId());
            winRecord.setBattingAmount(requestPayload.getBattingAmount());
            winRecord.setPeriodNumber(requestPayload.getPeriodNumber());

            if (requestPayload.getResultColourCode() == requestPayload.getColourCode()){
                winPrice= 2*requestPayload.getBattingAmount()-((2*requestPayload.getBattingAmount()*5)/100);
                winRecord.setPrise_amount(winPrice);
//                response=1;
            }else{
                winPrice = -(requestPayload.getBattingAmount()-(requestPayload.getBattingAmount()*5/100));
                winRecord.setPrise_amount(winPrice);
//                response=2;
            }

            winRecordDAO.saveWinRecord(winRecord);

            LoggerClass.appLayerLogger.info("playGAme ended.");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return winPrice;

    }

    @Override
    public int gameResult(){
        Random random = new Random();
        return random.nextInt(10);
//       colourCode is like this. 0-> VR,,  1,37,9-Green
//        5-> VG,,, 2,4,6,8-> Red
//
    }
}
