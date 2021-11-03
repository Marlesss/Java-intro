package markup;

import java.util.List;

public class Strong extends AbstractMarker {
    public Strong(List<TextValue> storage) {
        super(storage, "__", "<strong>", "</strong>", "[b]", "[/b]");
    }
}
