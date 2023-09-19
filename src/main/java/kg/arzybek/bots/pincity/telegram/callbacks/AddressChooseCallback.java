package kg.arzybek.bots.pincity.telegram.callbacks;

import kg.arzybek.bots.pincity.data.PlacesRepository;
import kg.arzybek.bots.pincity.utils.Consts;
import kg.arzybek.bots.pincity.utils.JsonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AddressChooseCallback {

    private final PlacesRepository placesRepository;

    public SendMessage apply(Callback callback, Long chatId) {
        SendMessage answer;
        Integer addressId = Integer.valueOf(callback.getData());
        String pin = placesRepository.findPin(addressId);
        if (pin == null) {
            answer = new SendMessage(String.valueOf(chatId), Consts.ERROR);
        } else {
            answer = new SendMessage(String.valueOf(chatId), String.format(Consts.CHOSE_ADDRESS_PIN, pin));
            addActionsKeyboard(answer, addressId, chatId);
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
