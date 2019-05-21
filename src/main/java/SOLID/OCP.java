package SOLID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OCP {

    public static void main(String[] args) {
        Product bike = new Product("bike", Color.BLUE, Size.HUGE);
        Product car = new Product("car", Color.GREEN, Size.SMALL);
        Product train = new Product("train", Color.RED, Size.MEDIUM);
        Product truck = new Product("truck", Color.RED, Size.LARGE);

        List<Product> products = Arrays.asList(bike, car, train, truck);
        ProductFilter productFilter = new ProductFilter();
        productFilter.filterProductByColor(products, Color.BLUE).forEach(System.out::println);
        productFilter.filterProductBySize(products, Size.SMALL).forEach(System.out::println);
        productFilter.filterProductByNameAndSize(products, Size.MEDIUM, Color.RED).forEach(System.out::println);


        System.out.println("...Better OCP...");
        BetterFilter betterFilter = new BetterFilter();
        betterFilter.filter(products, new ColorSpecification(Color.RED)).forEach(System.out::println);
        betterFilter.filter(products, new SizeSpecification(Size.SMALL)).forEach(System.out::println);


        betterFilter.filter(products, new AndSpecification<>(new ColorSpecification(Color.RED), new SizeSpecification(Size.MEDIUM))).forEach(System.out::println);
    }
}

enum Color {
    RED, BLUE, GREEN
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}


@AllArgsConstructor
@Getter
@ToString
class Product {
    private String name;
    private Color color;
    private Size size;
}

/**
 * Not following Open closed Principle
 */
class ProductFilter {

    Stream<Product> filterProductByColor(List<Product> products, Color color) {
        return products.stream().filter(product -> product.getColor() == color);
    }

    Stream<Product> filterProductBySize(List<Product> products, Size size) {
        return products.stream().filter(product -> product.getSize() == size);
    }

    Stream<Product> filterProductByNameAndSize(List<Product> products, Size size, Color color) {
        return products.stream().filter(product -> product.getSize() == size && product.getColor() == color);
    }
}

/**
 * Following open closed principle
 */


interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> specification);
}


@AllArgsConstructor
class ColorSpecification implements Specification<Product> {

    private Color color;

    @Override
    public boolean isSatisfied(Product item) {
        return item.getColor() == color;
    }
}

@AllArgsConstructor
class SizeSpecification implements Specification<Product> {

    private Size size;

    @Override
    public boolean isSatisfied(Product item) {
        return item.getSize() == size;
    }
}

@AllArgsConstructor
class AndSpecification<T> implements Specification<T> {

    private Specification<T> first, second;

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(item -> specification.isSatisfied(item));
    }
}