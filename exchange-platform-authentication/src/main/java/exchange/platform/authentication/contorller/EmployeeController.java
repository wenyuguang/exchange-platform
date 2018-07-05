package exchange.platform.authentication.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:56:11</p>
 * @author Tony
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @GetMapping("/greeting")
    public String greeting() {
        return "Hello,World!";
    }

}
