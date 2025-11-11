package com.prabs.account.cmd.domain;

import com.prabs.account.cmd.api.commands.OpenAccountCommand;
import com.prabs.account.common.events.AccountClosedEvent;
import com.prabs.account.common.events.AccountOpenedEvent;
import com.prabs.account.common.events.TaskAddedEvent;
import com.prabs.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private List<String> tasks;

    public List<String> getTasks() {
        return this.tasks;
    }

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                    .id(command.getId())
                    .accountHolder(command.getAccountHolder())
                    .createdDate(new Date())
                    .accountType(command.getAccountType())
                    .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.tasks = new ArrayList<>();;
    }

    public void addTask(String task) {
        if (!this.active) {
            throw new IllegalStateException("Task cannot be added into a closed account!");
        }

        raiseEvent(TaskAddedEvent.builder()
                    .id(this.id)
                    .task(task)
                    .build());
    }

    public void apply(TaskAddedEvent event) {
        this.id = event.getId();
        List<String> latestTasks = Stream.concat(this.tasks.stream(), Stream.of(event.getTask()))
                .collect(Collectors.toList());
        this.tasks =  latestTasks;
    }



    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("The account has already been closed!");
        }
        raiseEvent(AccountClosedEvent.builder()
                    .id(this.id)
                    .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
