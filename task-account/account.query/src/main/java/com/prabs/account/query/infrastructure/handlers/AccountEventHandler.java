package com.prabs.account.query.infrastructure.handlers;

import com.prabs.account.common.events.AccountClosedEvent;
import com.prabs.account.common.events.AccountOpenedEvent;
import com.prabs.account.common.events.TaskAddedEvent;
import com.prabs.account.query.domain.AccountRepository;
import com.prabs.account.query.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var account = Account.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .tasks(new ArrayList<>())
                .build();
        accountRepository.save(account);
    }

    @Override
    public void on(TaskAddedEvent event) {
        var account = accountRepository.findById(event.getId());
        if (account.isEmpty()) {
            return;
        }
        var currentTasks = account.get().getTasks();
        List<String> latestTasks = Stream.concat(currentTasks.stream(), Stream.of(event.getTask()))
                        .collect(Collectors.toList());
        account.get().setTasks(latestTasks);
        accountRepository.save(account.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
