package markup;

import java.util.List;

public class Emphasis extends AbstractMarker {
    public Emphasis(List<TextValue> storage) {
        super(storage, "*", "em", "i");
    }
}
