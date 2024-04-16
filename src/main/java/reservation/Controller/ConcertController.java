package reservation.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reservation.DTO.BaseResponse;
import reservation.DTO.ConcertShow;
import reservation.Service.ConcertShowService;
import reservation.DTO.Exception.TokenUnavailableException;
import reservation.DTO.Exception.WaitingException;
import reservation.Service.TicketService;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ConcertController {
    @Autowired
    private ConcertShowService concertShowService;
    @GetMapping("/concerts/{concert_id}")
    public List<ConcertShow> getConcerts (@PathVariable("concert_id") long concertId) {
        List<ConcertShow> concertShows = concertShowService.getAvailableConcertShow(concertId);
        return concertShows;
    }
}
