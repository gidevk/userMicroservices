package com.expriment.utils;

import com.expriment.entity.vo.NameMatchKarzaRequest;
import com.expriment.utils.audit.entity.vo.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MouseMover {
   /* public static void main(String[] args) {
        try {
            // Create an instance of the Robot class
            Robot robot = new Robot();

            // Timer to schedule the task
            Timer timer = new Timer();

            // Schedule the task to run every 4.5 minutes (270000 milliseconds)
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Get current mouse position
                    java.awt.Point location = MouseInfo.getPointerInfo().getLocation();
                    int x = (int) location.getX();
                    int y = (int) location.getY();
                    System.out.println("x "+x + " y "+y);
                    // Move the mouse cursor by a small amount and then back
                    // This time, move by 100 pixels which should be more noticeable
                    robot.mouseMove(x + 100, y + 100);
                    robot.mouseMove(x, y);

                    System.out.println("Mouse moved to prevent idle state");
                }
            }, 0, 2700); // Delay = 0, Period = 270000 ms (4.5 minutes)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Random random = new Random();
            int delay = 270; // delay in seconds
            DateFormat dateFormat_current = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            while (true) {
                Date date=new Date();
                String systemDate = dateFormat_current.format(date);

                // Get screen dimensions
                int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

                // Generate random coordinates
                int x = random.nextInt(screenWidth);
                int y = random.nextInt(screenHeight);
                System.out.println("Time is "+systemDate+ "   >>>>>>  x "+x +" Y "+y);
                // Move mouse to random coordinates
                robot.mouseMove(x, y);

               /* NameMatchKarzaRequest nameMatchKarzaRequest = new NameMatchKarzaRequest();
                nameMatchKarzaRequest.setCustomerHash("customerHash");
                nameMatchKarzaRequest.setName("indradev");
                nameMatchKarzaRequest.setPlLeadId("234938");

                ErrorResponse error = new ErrorResponse();
                error.setErrorCode("499");
                error.setErrorMessage("hello");
                error.setStatus("Success");

                nameMatchKarzaRequest.setError(error);
                // Create an ObjectMapper instance
                ObjectMapper objectMapper = new ObjectMapper();

                // Convert the Map to a JSON string
                String jsonString = objectMapper.writeValueAsString(nameMatchKarzaRequest);
                System.out.println(nameMatchKarzaRequest.toString());
                System.out.println(jsonString);


*/
                // Sleep for the specified delay
                TimeUnit.SECONDS.sleep(delay);
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
