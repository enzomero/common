package api.wpm.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CsvCard {

    private enum Csv {
        QUANTITY,
        NAME,
        SIMPLE_NAME,
        SET,
        CARD_NUMBER,
        SET_CODE,
        PRINTING,
        CONDITION,
        LANGUAGE,
        RARITY,
        PRODUCT_ID,
        SKU;
    }

    private final List<String> data;

    private final String quantity;

    private final String name;

    private final String originName;

    private final String setCode;

    private final String cardNumber;

    private final String printing;

    private final String language;

    private final String rarity;

    private final String productId;

    public CsvCard(String csvLine) {
        this.data = Arrays.stream(csvLine.replaceAll(",\\s", " ")
                .split(","))
                .collect(Collectors.toList());
        this.quantity = data.get(Csv.QUANTITY.ordinal());
        this.originName = data.get(Csv.NAME.ordinal())
                .replace("\"", "");
        this.name = data.get(Csv.SIMPLE_NAME.ordinal()).replace("\"", "").replace("\'", "");
        this.setCode = parseSetCode();
        this.cardNumber = data.get(Csv.CARD_NUMBER.ordinal());
        this.printing = data.get(Csv.PRINTING.ordinal())
                .equalsIgnoreCase("Normal") ? "n" : "f";
        this.language = setLanguage();
        this.rarity = data.get(Csv.RARITY.ordinal());
        this.productId = data.get(Csv.PRODUCT_ID.ordinal());
    }

    private String parseSetCode() {
        String s = data.get(Csv.SET_CODE.ordinal()).toLowerCase();

        switch (s){
            case "gr1":
                return "gk1";
            case "ac2":
                return "e01";
            case "tlp":
                return "plist";
            default:
                return s;
        }
    }

    private String setLanguage() {
        final String s = data.get(Csv.LANGUAGE.ordinal());
        if (s.equalsIgnoreCase("German")){
            return "de";
        }
        return s.substring(0, 2).toLowerCase();
    }
}
