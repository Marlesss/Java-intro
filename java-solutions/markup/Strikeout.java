package markup;

import java.util.List;

public class Strikeout extends AbstractMarker {
    public Strikeout(List<TextValue> storage) {
        super(storage, "~", "<s>", "</s>", "[s]", "[/s]");
    }
}
