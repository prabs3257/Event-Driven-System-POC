package com.prabs.account.cmd.api.commands;

import com.prabs.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id) {
        super(id);
    }
}
