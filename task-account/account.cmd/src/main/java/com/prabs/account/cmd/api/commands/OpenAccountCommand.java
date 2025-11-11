package com.prabs.account.cmd.api.commands;

import com.prabs.account.common.dto.AccountType;
import com.prabs.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
}