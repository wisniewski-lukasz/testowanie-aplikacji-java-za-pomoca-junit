package pl.rsokol;

public class Main {

    public static void main(final String[] params) {
        final ProductRating rating = new ProductRating();
        System.out.println("Ocena przed zmianą: " + rating.getScore());
        rating.setScore(5);
        System.out.println("Ocena po zmianie:   " + rating.getScore());

        QuadraticFunction qf = QuadraticFunction.of(-2.0, 3.0, -1.0);
        System.out.println("1. x1 = " + qf.getX1() + ", x2 = " + qf.getX2());
        qf = QuadraticFunction.of(4.0, 4.0, 1.0);
        System.out.println("2. x1 = " + qf.getX1() + ", x2 = " + qf.getX2());
        qf = QuadraticFunction.of(0.0, 4.0, 1.0);
        System.out.println("3. x1 = " + qf.getX1() + ", x2 = " + qf.getX2());
        qf = QuadraticFunction.of(1.0, 2.0, 4.0);
        System.out.println("4. x1 = " + qf.getX1() + ", x2 = " + qf.getX2());
    }

}
