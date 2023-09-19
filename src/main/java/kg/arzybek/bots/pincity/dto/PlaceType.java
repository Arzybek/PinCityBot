package kg.arzybek.bots.pincity.dto;

public enum PlaceType {
    KFC("КФС"),
    PIZZA_MIA("Пицца мия"),
    OTHER("Другие");

    public final String name;

    PlaceType(String name) {
        this.name = name;
    }
}
