package course.advance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class Buyer {

    @Getter
    private final String name;

    private boolean isServiced;

    public boolean isNotServiced() {
        return !isServiced;
    }
}
