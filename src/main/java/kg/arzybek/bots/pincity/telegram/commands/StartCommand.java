package kg.arzybek.bots.pincity.telegram.commands;

import kg.arzybek.bots.pincity.utils.Consts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand {

    public static SendMessage apply(Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(Consts.START_MESSAGE);
        return sendMessage;
    }

}
