package kg.arzybek.bots.pincity.telegram.commands;

import kg.arzybek.bots.pincity.data.CityEntity;
import kg.arzybek.bots.pincity.data.CityRepository;
import kg.arzybek.bots.pincity.utils.Consts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StartCommand {

    private final CityRepository repository;

    public SendMessage apply(Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(Consts.START_MESSAGE);
        List<CityEntity> allCities = repository.findAll();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        for(var city: allCities) {
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText(city.getName());
            inlineKeyboardButton1.setCallbackData(city.getId().toString());
            keyboardButtonsRow1.add(inlineKeyboardButton1);
        }
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

}
