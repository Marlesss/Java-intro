package markup;

import java.util.List;

public class Paragraph implements TextValue {
    protected List<TextValue> storage;

    public Paragraph(List<TextValue> storage) {
        this.storage = storage;
    }

    @Override
    public void toMarkdown(StringBuilder line) {
        for (TextValue textValue : storage) {
            textValue.toMarkdown(line);
        }
    }

    @Override
    public void toHtml(StringBuilder line) {
        for (TextValue textValue : storage) {
            textValue.toHtml(line);
        }
    }

    @Override
    public void toBBCode(StringBuilder line) {
        for (TextValue textValue : storage) {
            textValue.toBBCode(line);
        }
    }
}