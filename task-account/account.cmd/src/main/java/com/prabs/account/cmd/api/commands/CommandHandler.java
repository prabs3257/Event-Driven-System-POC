package com.prabs.account.cmd.api.commands;

public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(AddTaskCommand command);
    void handle(CloseAccountCommand command);
}
