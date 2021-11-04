package markup;

import java.util.List;

public abstract class AbstractMarker extends Paragraph {
    // :NOTE: имена переменных
    protected String marker;
    protected String html_open_tag;
    protected String html_close_tag;
    protected String BBC_open_tag;
    protected String BBC_close_tag;

    protected AbstractMarker(List<TextValue> storage, String marker, String html_open_tag,
                             String html_close_tag, String BBC_open_tag, String BBC_close_tag) {
        super(storage);
        this.marker = marker;
        this.html_open_tag = html_open_tag;
        this.html_close_tag = html_close_tag;
        this.BBC_open_tag = BBC_open_tag;
        this.BBC_close_tag = BBC_close_tag;
    }

    @Override
    public void toMarkdown(StringBuilder line) {
        line.append(marker);
        super.toMarkdown(line);
        line.append(marker);
    }

    @Override
    public void toHtml(StringBuilder line) {
        line.append(html_open_tag);
        super.toHtml(line);
        line.append(html_close_tag);
    }

    @Override
    public void toBBCode(StringBuilder line) {
        line.append(BBC_open_tag);
        super.toBBCode(line);
        line.append(BBC_close_tag);
    }
}
