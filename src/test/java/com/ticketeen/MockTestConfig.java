package com.ticketeen;

import com.ticketeen.web.OnlineCashierService;
import com.ticketeen.web.OnlineCashierServiceImpl;
import com.ticketeen.web.json.Document;
import com.ticketeen.web.json.OnlineCashierReceipt;
import com.ticketeen.web.json.Receipt;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                // Exclude the default message service
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = OnlineCashierServiceImpl.class),
                // Exclude the default boot application or it's
                // @ComponentScan will pull in the default message service
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class)
        }
)
public class MockTestConfig {

    @Bean
        // Define our own test message service
    OnlineCashierService mockOnlineCashierService() {
        return (login, password) -> {
            final OnlineCashierReceipt receipt = new OnlineCashierReceipt();
            final Document document = new Document();
            receipt.setDocument(document);
            final Receipt receipt1 = new Receipt();
            document.setReceipt(receipt1);
            receipt1.setUser("Ашан");
            return new OnlineCashierReceipt[]{receipt};
        };
    }
}
