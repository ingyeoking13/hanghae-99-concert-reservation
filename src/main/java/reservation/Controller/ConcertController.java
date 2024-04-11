package reservation.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
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
    private TicketService ticketService;
    @Autowired
    private ConcertShowService concertShowService;
    @GetMapping("/concerts/{concert_id}")
    @ExceptionHandler(value = { TokenUnavailableException.class, WaitingException.class})
    @Tag(name="")
    public List<ConcertShow> getConcerts (
            @RequestHeader("token_id") String tokenId,
            @PathParam("concert_id") Long concertId) throws Exception {
        List<ConcertShow> result = new LinkedList<>();
        ticketService.poolingWaitingQueue("concert" , tokenId);

        List<ConcertShow> concertShows = concertShowService.getAvailableConcertShow(concertId);

        return concertShows;
    }
}
