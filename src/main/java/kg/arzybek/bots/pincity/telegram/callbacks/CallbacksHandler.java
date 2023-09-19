package kg.arzybek.bots.pincity.telegram.callbacks;

import kg.arzybek.bots.pincity.data.ChatCitiesRepository;
import kg.arzybek.bots.pincity.data.ChatsCitiesEntity;
import kg.arzybek.bots.pincity.data.CityRepository;
import kg.arzybek.bots.pincity.data.PlacesRepository;
import kg.arzybek.bots.pincity.dto.PlaceType;
import kg.arzybek.bots.pincity.telegram.commands.StartCommand;
import kg.arzybek.bots.pincity.utils.Consts;
import kg.arzybek.bots.pincity.utils.JsonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class CallbacksHandler {

    private final Map<CallbackType, BiFunction<Callback, Long, SendMessage>> callbacks;

    public CallbacksHandler(@Autowired TypeChooseCallback typeChooseCallback,
                            @Autowired CityChooseCallback cityChooseCallback,
                            @Autowired AddressChooseCallback addressChooseCallback) {
        this.callbacks = Map.of(CallbackType.TYPE_CHOOSE, typeChooseCallback::apply,
                CallbackType.CITY_CHOOSE, cityChooseCallback::apply,
                CallbackType.ADDRESS_CHOOSE, addressChooseCallback::apply
        );
    }

    public SendMessage handleCallbacks(Update update) {
        List<String> list = JsonHandler.toList(update.getCallbackQuery().getData());
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        SendMessage answer;
        if (list.isEmpty()) {
            answer = new SendMessage(String.valueOf(chatId), Consts.ERROR);
        } else {
            Callback callback = Callback.builder().callbackType(CallbackType.valueOf(list.get(0))).data(list.get(1)).build();
            BiFunction<Callback, Long, SendMessage> callbackBiFunction = callbacks.get(callback.getCallbackType());
            answer = callbackBiFunction.apply(callback, chatId);
        }

        return answer;
    }

}
