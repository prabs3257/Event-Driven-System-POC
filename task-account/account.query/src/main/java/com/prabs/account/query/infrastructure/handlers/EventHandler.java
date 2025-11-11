package com.prabs.account.query.infrastructure.handlers;

import com.prabs.account.common.events.AccountClosedEvent;
import com.prabs.account.common.events.AccountOpenedEvent;
import com.prabs.account.common.events.TaskAddedEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(TaskAddedEvent event);
    void on(AccountClosedEvent event);
}
