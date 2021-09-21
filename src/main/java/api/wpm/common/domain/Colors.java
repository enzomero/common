package api.wpm.common.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Colors {
    W(17),
    B(19),
    R(20),
    U(18),
    G(21),
    C(22),
    L(23);

    @Getter
    private final int colorId;

    public static final List<Integer> ids = Arrays.stream(Colors.values())
            .map(Colors::getColorId)
            .collect(Collectors.toList());

    Colors(int colorId) {
        this.colorId = colorId;
    }
}
