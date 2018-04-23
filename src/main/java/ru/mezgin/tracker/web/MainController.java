package ru.mezgin.tracker.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.userdetails.User;
import ru.mezgin.tracker.util.WebUtils;

import java.security.Principal;

/**
 * The class MainController.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 19.04.2018
 */
@Controller
public class MainController {

    /**
     * The mapping for path "/" (GET).
     *
     * @return login page.
     */
    @RequestMapping("/")
    public String welcome() {
        return "loginPage";
    }

    /**
     * The mapping for path "/login" (GET).
     *
     * @return time work page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "timeWorkPage";
    }

    /**
     * The mapping for path "/logoutSuccessful" (GET).
     *
     * @return logout successful page.
     */
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage() {
        return "logoutSuccessfulPage";
    }

    /**
     * The mapping for path "/admins" (GET).
     *
     * @param model     model.
     * @param principal principal.
     * @return admin view page.
     */
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminView";
    }

    /**
     * The mapping for path "/timeWork" (GET).
     *
     * @param model     model.
     * @param principal principal.
     * @return time work page.
     */
    @RequestMapping(value = "/timeWork", method = RequestMethod.GET)
    public String timeWorkPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "timeWorkPage";
    }

    /**
     * The mapping for path "/403" (GET). Access denied.
     *
     * @param model     model.
     * @param principal principal.
     * @return 403 page.
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = loginedUser.getUsername();
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
}
