package SOLID;

import lombok.*;

/**
 * Object of Parent class can be replaced with object of child class without any negative side effects
 */
public class LiskhovSubstitutionPrinciple {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 3);
        Demo.useIt(rectangle);

        Rectangle square = new Square(2);
        Demo.useIt(square);
    }

}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Rectangle {
    protected int width;
    protected int height;

    public int getArea() {
        return this.width * this.height;
    }
}


@NoArgsConstructor
class Square extends Rectangle {

    public Square(int size) {
        this.height = this.width = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

class Demo {
    static void useIt(Rectangle rectangle) {

        int width = rectangle.getWidth();
        rectangle.setHeight(10);

        System.out.println("Expected area = " + width * 10 + " actual output will be " + rectangle.getArea());
    }
}



