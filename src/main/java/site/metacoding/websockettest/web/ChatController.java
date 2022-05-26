package site.metacoding.websockettest.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.websockettest.web.dto.UserDto;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final HttpSession session;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        String username = (String) session.getAttribute("principal");
        model.addAttribute("username", username);
        return "chat";
    }

    @PostMapping("/join-chat")
    public ResponseEntity<?> joinChat(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        if (username == null || username == "") {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        session.setAttribute("principal", username);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
