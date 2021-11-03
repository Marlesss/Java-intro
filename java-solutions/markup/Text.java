package markup;

public class Text implements TextValue {
    String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder line) {
        line.append(text);
    }

    @Override
    public void toHtml(StringBuilder line) {
        line.append(text);
    }

    @Override
    public void toBBCode(StringBuilder line) {
        line.append(text);
    }

}
