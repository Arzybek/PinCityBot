package kg.arzybek.bots.pincity.telegram.commands;

import kg.arzybek.bots.pincity.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CommandsHandler {

    private final StartCommand startCommand;

    private final Map<String, BiFunction<Long, String, SendMessage>> commands;

    public CommandsHandler(@Autowired StartCommand startCommand) {
        this.startCommand = startCommand;
        this.commands = Map.of(
                "/start", startCommand::apply
        );
    }

    public SendMessage handleCommands(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        var command = commands.get(messageText);

        if (command != null) {
            return command.apply(chatId, messageText);
        }
        else {
            return new SendMessage(String.valueOf(chatId), Consts.UNKNOWN_COMMAND);
        }
    }

}
