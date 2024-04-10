package reservation.Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reservation.Controller.DTO.BaseResponse;
import reservation.Controller.DTO.Show;
import reservation.Service.ConcertShowService;
import reservation.Service.DTO.ConcertShow;
import reservation.Service.Exception.TokenUnavailableException;
import reservation.Service.Exception.WaitingException;
import reservation.Service.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ConcertController {
    private TicketService ticketService;
    private ConcertShowService concertShowService;
    @GetMapping("/concerts/{concert_id}")
    public BaseResponse<List<Show>> getConcerts(
            @RequestHeader("token_id") String tokenId,
            @PathParam("concert_id") Long concertId) {
        List<Show> result = new LinkedList<>();
        try {
            ticketService.poolingWaitingQueue("concert" , tokenId);
        }
        catch(TokenUnavailableException e) {
            return new BaseResponse<>(404, "유효하지 않은 토큰입니다.", result);
        }
        catch(WaitingException e){
            return new BaseResponse<>(404, "아직 대기열에 있습니다. " + e.getWaitingNumber() , result);
        }

        List<ConcertShow> concertShows = concertShowService.getAvailableConcertShow(concertId);
        concertShows.stream().forEach(
                (concertShow) -> result.add(
                        Show.builder()
                                .name(concertShow.getName())
                                .dateTime(concertShow.getDatetime())
                                .build()
                )
        );

        return new BaseResponse<>(
                200,
                "",
                result
        );
    }
}
