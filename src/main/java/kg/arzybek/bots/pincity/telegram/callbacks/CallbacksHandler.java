package kg.arzybek.bots.pincity.telegram.callbacks;

import kg.arzybek.bots.pincity.data.ChatCitiesRepository;
import kg.arzybek.bots.pincity.data.ChatsCitiesEntity;
import kg.arzybek.bots.pincity.data.CityRepository;
import kg.arzybek.bots.pincity.utils.Consts;
import kg.arzybek.bots.pincity.utils.JsonHandler;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.aspectj.weaver.ast.Call;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CallbacksHandler {

    private final ChatCitiesRepository chatCitiesRepository;

    private final CityRepository cityRepository;

    public SendMessage handleCallbacks(Update update) {
        List<String> list = JsonHandler.toList(update.getCallbackQuery().getData());
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        SendMessage answer = new SendMessage();
        if (list.isEmpty()) {
            answer = new SendMessage(String.valueOf(chatId), Consts.ERROR);
        }

        Callback callback = Callback.builder().callbackType(CallbackType.valueOf(list.get(0))).data(list.get(1)).build();

        switch (callback.getCallbackType()) {
            case CITY_CHOOSE:
                UUID cityId = UUID.fromString(callback.getData());
                chatCitiesRepository.merge(new ChatsCitiesEntity(chatId, UUID.fromString(callback.getData())));
                answer = new SendMessage(String.valueOf(chatId), String.format(Consts.CHOSE_MESSAGE, cityRepository.getName(cityId)));
                break;
        }

        return answer;
    }

}
