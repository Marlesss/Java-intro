package markup;

public interface TextValue {
    void toMarkdown(StringBuilder line);
    void toHtml(StringBuilder line);
    void toBBCode(StringBuilder line);
}
