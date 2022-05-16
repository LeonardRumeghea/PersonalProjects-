import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;


@XmlRootElement
@XmlSeeAlso(MyColorClass.class)
public class Rock implements Serializable {

    private MyColorClass color;
    private int x;
    private int y;
    private int rowNumber;
    private int colNumber;
    private int size = 20;

    public Rock(int x, int y, int rowNumber, int colNumber) {
        this.x = x;
        this.y = y;
        this.color = new MyColorClass(Color.GRAY);
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    public Rock() {}

    public Shape getShape() {
        return new Ellipse2D.Double(this.x, this.y, this.size, this.size);
    }

    public MyColorClass getColor() {
        return this.color;
    }

    public void setColor(@NotNull Color color) {
        this.color.setRed(color.getRed());
        this.color.setGreen(color.getGreen());
        this.color.setBlue(color.getBlue());
    }

    public void setColor(MyColorClass color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()));
        g.draw(this.getShape());
    }

    public void draw(Graphics2D g, Color color) {
        g.setColor(color);
        g.draw(this.getShape());
    }

    public void fill(Graphics2D g) {
        g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()));
        g.fill(this.getShape());
    }

    public void fill(Graphics2D g, Color color) {
        g.setColor(color);
        g.fill(this.getShape());
    }

    @Override
    public String toString() {
        return "Rock{" +
                "shape=" + getShape() +
                ", color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}
