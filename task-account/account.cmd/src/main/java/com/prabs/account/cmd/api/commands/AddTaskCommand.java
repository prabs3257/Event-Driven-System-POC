package com.prabs.account.cmd.api.commands;

import com.prabs.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class AddTaskCommand extends BaseCommand {
    private String task;
}
