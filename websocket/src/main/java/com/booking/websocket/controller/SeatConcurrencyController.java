package com.booking.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SeatConcurrencyController {

    List<Integer> seatList = new ArrayList<>();

    @MessageMapping("seat_booking")
    @SendTo("/topic/seatid")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getMessages(String seatid){
        int seat = Integer.parseInt(seatid);
        if (seat>=0){
            for (int i=0; i<seatList.size();i++){
                if (seat == seatList.get(i)){
                    seatList.remove(i);
                    return seatList.toString();
                }
            }
            seatList.add(seat);
        }
        return seatList.toString();
    }

    @MessageMapping("disconnect")
    @SendTo("/topic/seatid")
    @CrossOrigin(origins = "http://localhost:3000")
    public String removeSeats(String seatid){
        String[] s = seatid.split(",");
        for (int i=0;i <s.length;i++){
            int seat = Integer.parseInt(s[i]);
            for(int j=0; j<seatList.size();j++){
                if (seat == seatList.get(j)){
                    seatList.remove(j);
                    break;
                }
            }
        }
        return seatList.toString();
    }

}