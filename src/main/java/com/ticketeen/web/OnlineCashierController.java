package com.ticketeen.web;

import com.ticketeen.web.json.OnlineCashierReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/online-cashier")
public class OnlineCashierController {

    private static final Logger log = LoggerFactory.getLogger(OnlineCashierController.class);

    private OnlineCashierService service;

    @Autowired
    public OnlineCashierController(OnlineCashierService service) {
        this.service = service;
    }

    @GetMapping("/{login}/{password}")
    public OnlineCashierReceipt[] getReceipts(
            @PathVariable String login, @PathVariable String password) {

        log.info(String.format("login %s password %s", login, password));
        return service.getReceipts(login, password);
    }
}
