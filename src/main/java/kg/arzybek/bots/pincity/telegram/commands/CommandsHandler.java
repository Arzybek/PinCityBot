package kg.arzybek.bots.pincity.telegram.commands;

import kg.arzybek.bots.pincity.utils.Consts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class CommandsHandler {
    public static final Map<String, BiFunction<Long, String, SendMessage>> COMMANDS = Map.of(
            "/start", StartCommand::apply
    );

    public static SendMessage handleCommands(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        var command = COMMANDS.get(messageText);

        if (command != null) {
            return command.apply(chatId, messageText);
        } else {
            return new SendMessage(String.valueOf(chatId), Consts.UNKNOWN_COMMAND);
        }
    }

}
