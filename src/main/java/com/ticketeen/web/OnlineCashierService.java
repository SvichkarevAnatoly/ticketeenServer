package com.ticketeen.web;

import com.ticketeen.web.json.OnlineCashierReceipt;

public interface OnlineCashierService {
    OnlineCashierReceipt[] getReceipts(String login, String password);
}
