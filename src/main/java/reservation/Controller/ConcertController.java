package reservation.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Show;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConcertController {
    @GetMapping("/concerts")
    public List<Show> getConcerts() {
        List<Show> result = new ArrayList<>();

        Show testShow = Show.builder()
                    .name("test")
                    .dateTime(LocalDateTime.MAX).point(1000).build();
        result.add(testShow);
        return result;
    }
}
