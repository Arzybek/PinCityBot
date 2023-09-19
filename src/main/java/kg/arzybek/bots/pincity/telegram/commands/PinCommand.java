package kg.arzybek.bots.pincity.telegram.commands;

import jakarta.transaction.Transactional;
import kg.arzybek.bots.pincity.data.ChatsPinsRepository;
import kg.arzybek.bots.pincity.data.PlacesRepository;
import kg.arzybek.bots.pincity.utils.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class PinCommand implements Command {

    private final ChatsPinsRepository chatsPinsRepository;

    private final PlacesRepository placesRepository;

    @Transactional
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();
        long userId = update.getMessage().getFrom().getId();
        Integer placeId = chatsPinsRepository.findByChatId(chatId);
        String pin = update.getMessage().getText().split(" ")[1];

        SendMessage sendMessage;
        if (placeId == null) {
            sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText(Consts.PIN_WRONG_ORDER);
        } else {
            sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText(Consts.PIN_ADDED_MSG);
            placesRepository.updatePin(placeId, pin, userId);
            chatsPinsRepository.deleteByChatId(chatId);
        }

        return sendMessage;
    }
}
