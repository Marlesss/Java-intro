package markup;

import java.util.List;

public abstract class AbstractMarker extends Paragraph {
    protected String marker;
    protected String html_tag;
    protected String BBC_tag;

    protected AbstractMarker(List<TextValue> storage, String marker, String html_tag, String BBC_tag) {
        super(storage);
        this.marker = marker;
        this.html_tag = html_tag;
        this.BBC_tag = BBC_tag;
    }

    @Override
    public void toMarkdown(StringBuilder line) {
        line.append(marker);
        super.toMarkdown(line);
        line.append(marker);
    }

    @Override
    public void toHtml(StringBuilder line) {
        line.append("<").append(html_tag).append(">");
        super.toHtml(line);
        line.append("</").append(html_tag).append(">");
    }

    @Override
    public void toBBCode(StringBuilder line) {
        line.append("[").append(html_tag).append("]");
        super.toBBCode(line);
        line.append("[/").append(html_tag).append("]");
    }
}
