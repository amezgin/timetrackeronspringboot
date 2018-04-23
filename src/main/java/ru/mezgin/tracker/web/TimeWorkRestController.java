package ru.mezgin.tracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mezgin.tracker.model.Status;
import ru.mezgin.tracker.repository.StatusRepository;
import ru.mezgin.tracker.repository.UserRepository;
import ru.mezgin.tracker.util.CalculationTimeWorked;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.ZoneOffset.UTC;

/**
 * The class TimeWorkRestController.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 19.04.2018
 */
@RestController
public class TimeWorkRestController {

    /**
     * Status repository.
     */
    private StatusRepository statusRepository;

    /**
     * User repository.
     */
    private UserRepository userRepository;

    /**
     * The constructor.
     *
     * @param statusRepository status repository.
     * @param userRepository   user repository.
     */
    @Autowired
    public TimeWorkRestController(StatusRepository statusRepository, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    /**
     * The mapping for path "/timeWork/{action}" (GET).
     *
     * @param action    user action.
     * @param principal principal.
     * @return json result.
     */
    @RequestMapping(value = "/timeWork/{action}", method = RequestMethod.GET)
    public String timeWork(Principal principal, @PathVariable("action") String action) {
        StringBuilder responce = new StringBuilder();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Integer userId = this.userRepository.getByLogin(loginedUser.getUsername()).getId();
        Status lastActionUser = null;
        List<Status> allTime = null;
        lastActionUser = this.statusRepository.getLastActionUser(userId);

        switch (action) {
            case "come":
                if (lastActionUser == null) {
                    this.statusRepository.save(new Status(null, "Пришёл", LocalDateTime.now(), true, false), userId);
                } else if (lastActionUser.isEndWorkDay()) {
                    this.statusRepository.save(new Status(null, "Пришёл", LocalDateTime.now(), true, false), userId);
                } else if (lastActionUser.getName().equals("Ушёл")) {
                    this.statusRepository.save(new Status(null, "Пришёл", LocalDateTime.now(), false, false), userId);
                }
                responce.append("{\"");
                responce.append(loginedUser.getUsername());
                responce.append("\":\"come\"}");
                break;
            case "out":
                if (lastActionUser != null && lastActionUser.getName().equals("Пришёл")) {
                    this.statusRepository.save(new Status(null, "Ушёл", LocalDateTime.now(), false, false), userId);
                    responce.append("{\"");
                    responce.append(loginedUser.getUsername());
                    responce.append("\":\"out\"}");
                }
                break;
            case "worked":
                if (lastActionUser != null && !lastActionUser.getName().equals("Отработал")) {
                    this.statusRepository.save(new Status(null, "Отработал", LocalDateTime.now(), false, true), userId);
                    allTime = this.statusRepository.getBetween(this.statusRepository.getLastStartWorkDay(userId).getDateTime(),
                            this.statusRepository.getLastEndWorkDay(userId).getDateTime(), userId);
                    Long timeWorked = CalculationTimeWorked.calculate(allTime);
                    LocalDateTime time = Instant.ofEpochMilli(timeWorked).atZone(UTC).toLocalDateTime();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
                    responce.append("{\"timework\":\"");
                    responce.append(format.format(time));
                    responce.append("\"}");
                }
                break;
            default:
                break;
        }
        return responce.toString();
    }
}
