package kg.arzybek.bots.pincity.telegram.callbacks;

import kg.arzybek.bots.pincity.data.ChatsPinsEntity;
import kg.arzybek.bots.pincity.data.ChatsPinsRepository;
import kg.arzybek.bots.pincity.data.PlacesRepository;
import kg.arzybek.bots.pincity.dto.PinState;
import kg.arzybek.bots.pincity.utils.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class PinActionCallback implements CallbackHandler {

    private final PlacesRepository placesRepository;

    private final ChatsPinsRepository chatsPinsRepository;

    public SendMessage apply(Callback callback, Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long userId = update.getCallbackQuery().getFrom().getId();
        SendMessage answer = new SendMessage();
        Integer addressId = Integer.valueOf(callback.getData());
        if (callback.getCallbackType() == CallbackType.PIN_DONT_ADD) {
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_DONT_ADD_BYE);
        } else if (callback.getCallbackType() == CallbackType.PIN_ADD) {
            placesRepository.updateState(PinState.OUTDATED, addressId, userId);
            chatsPinsRepository.merge(new ChatsPinsEntity(chatId, addressId));
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_ADD_MSG);
        }

        return answer;
    }

}
