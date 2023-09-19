package kg.arzybek.bots.pincity.telegram.callbacks;

import kg.arzybek.bots.pincity.data.PlacesRepository;
import kg.arzybek.bots.pincity.dto.PinState;
import kg.arzybek.bots.pincity.utils.Consts;
import kg.arzybek.bots.pincity.utils.JsonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PinReviewCallback implements CallbackHandler {

    private final PlacesRepository placesRepository;

    public SendMessage apply(Callback callback, Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long userId = update.getCallbackQuery().getFrom().getId();
        SendMessage answer = new SendMessage();
        Integer addressId = Integer.valueOf(callback.getData());
        if (callback.getCallbackType() == CallbackType.PIN_OK) {
            placesRepository.updateState(PinState.CORRECT, addressId, userId);
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_CORRECT_BYE);
        } else if (callback.getCallbackType() == CallbackType.PIN_WRONG) {
            placesRepository.updateState(PinState.OUTDATED, addressId, userId);
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_INCORRECT_MSG);
            AddressChooseCallback.addPinActionsKeyboard(answer, addressId);
        }

        return answer;
    }

    private void addActionsKeyboard(SendMessage answer, Integer addressId, Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(Consts.YES);
        String jsonCallback = JsonHandler.toJson(List.of(CallbackType.PIN_OK, addressId));
        inlineKeyboardButton.setCallbackData(jsonCallback);
        keyboardButtonsRow.add(inlineKeyboardButton);

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(Consts.NO);
        String jsonCallback1 = JsonHandler.toJson(List.of(CallbackType.PIN_WRONG, addressId));
        inlineKeyboardButton1.setCallbackData(jsonCallback1);
        keyboardButtonsRow.add(inlineKeyboardButton1);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        answer.setReplyMarkup(inlineKeyboardMarkup);
    }

}
