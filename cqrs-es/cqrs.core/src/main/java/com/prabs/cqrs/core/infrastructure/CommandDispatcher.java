package com.prabs.cqrs.core.infrastructure;

import com.prabs.cqrs.core.commands.BaseCommand;
import com.prabs.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
