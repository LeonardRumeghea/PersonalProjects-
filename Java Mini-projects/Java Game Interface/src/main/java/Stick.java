import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.awt.*;
import java.io.Serializable;

@XmlSeeAlso(Rock.class)
@XmlRootElement
public class Stick implements Serializable {

    private Rock firstNode;
    private Rock secondNode;

    public Stick(Rock firstNode, Rock secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public Stick() {}

    public Rock getFirstNode() {
        return firstNode;
    }

    public Rock getSecondNode() {
        return secondNode;
    }

    public void setFirstNode(Rock firstNode) {
        this.firstNode = firstNode;
    }

    public void setSecondNode(Rock secondNode) {
        this.secondNode = secondNode;
    }

    public void draw(Graphics g, Color color, BasicStroke basicStroke) {
        int x1 = this.getFirstNode().getX() + (this.getFirstNode().getSize() / 2);
        int y1 = this.getFirstNode().getY() + (this.getFirstNode().getSize() / 2);
        int x2 = this.getSecondNode().getX() + (this.getSecondNode().getSize() / 2);
        int y2 = this.getSecondNode().getY() + (this.getSecondNode().getSize() / 2);

        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(color);
        graphics.setStroke(basicStroke);
        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "Stick{" +
                "firstNode=" + firstNode +
                ", secondNode=" + secondNode +
                '}';
    }
}
