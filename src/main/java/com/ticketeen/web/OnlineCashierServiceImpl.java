package com.ticketeen.web;

import com.ticketeen.web.json.OnlineCashierReceipt;
import com.ticketeen.web.json.OnlineCashierReceiptsLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OnlineCashierServiceImpl implements OnlineCashierService {

    private static final Logger log = LoggerFactory.getLogger(OnlineCashierServiceImpl.class);

    private static final String ROOT_URL = "http://proverkacheka.nalog.ru:8888";
    private final static String GET_URLS = "/v1/extract?sendToEmail=0&fileType=json";

    private final RestTemplate restTemplate;

    @Autowired
    public OnlineCashierServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OnlineCashierReceipt[] getReceipts(String login, String password) {
        // basic authorization for next requests
        restTemplate.getInterceptors()
                .add(new BasicAuthorizationInterceptor(login, password));

        // get link with receipts
        OnlineCashierReceiptsLink receiptsLink = restTemplate.getForObject(
                ROOT_URL + GET_URLS, OnlineCashierReceiptsLink.class);
        log.info("Received link " + receiptsLink.getUrl());

        // get receipts
        return restTemplate.getForObject(
                ROOT_URL + receiptsLink.getUrl(), OnlineCashierReceipt[].class);
    }
}
