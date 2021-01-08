package commandpromptgrapher;

public class MathFunctions {

    /**Indicates that a class has some evaluatable function. Although not checked by the interface, the evaluatable
    * function must be defined over any used domain.
    */
    public interface mathFunction {

        /**Evaluates some function over any domain that will be called. It should be a pure math function.
        * @param x The input to a pure math function
         */
        double evaluate(double x);

    }

    /**Calcuates n! for 0 <= n < 12
     * @param n The operand of the factorial*/
    public static int factorial(int n) {

        if (n == 1)
            return 1;
        else if (n == 0)
            return 1;
        else if (n > 12)
            return -1;
        else if (n > 1)
            return n * factorial(n-1);
        else
            return -1;

    }
    /**Calcuates n! for 0 <= n <= 20
     * @param n The operand of the factorial.
     * @param s Indicates that the code is calling the long varient of the method. */
    public static long factorial(long n, Object s) {

        if (n == 1)
            return 1;
        else if (n == 0)
            return 1;
        else if (n > 1000)
            return -1;
        else if (n > 1)
            return n * factorial(n-1, 1);
        else
            return -1;

    }

    /**Approximates the gamma function with respect to 'z'. Instead of calculating the entire integral,
     * it calculates on the bounds of 0 to 1000.
     * @param z The operand of the function. z must be >= 1.
     * @param n The number of sample points per domain value in the integral.
     * @return The valoue of the gamma function evaluated at z.
     * */
    public static double gamma(double z, int n) {

        mathFunction gammaPrime =  x -> Math.pow(x,z-1) * Math.pow(Math.E, -x);

        double val = riemann(gammaPrime, 0, 1000, (int)(1000*n));


        return Math.round(z) - z == 0 ? Math.round(val) : val;


    }



    /**Evaluates the gamma function with respect to 'z'. Instead of calculating the intire integral,
     * it calculates on the bounds of 0 to 1000. For the integral, it takes 100 sample points per domain value.
     * @param z The operand of the function.
     */
    public static double gamma(double z) {

        mathFunction gammaPrime =  x -> Math.pow(x,z-1) * Math.pow(Math.E, -x);

        double val = riemann(gammaPrime, 0, 1000, 100000);

        return Math.round(z) - z == 0 ? Math.round(val) : val;
    }

    /**Uses the riemann method aproximation integrals. Left hand rectangles are used.
     * @param f The fuction to be integrated.
     * @param a The starting point of integration
     * @param b The ending point of integration
     * @param n number of rectangles used to approximate the integral. The higher 'n', the more accurate the approximation.
     * @return The approximate value of the integral.*/
    public static double riemann(mathFunction f, double a, double b, int n) {

        double sum = 0;

        double change = (b-a)/n;

        for (int i = 0; i < n; i++) {

            sum += (f.evaluate(a + i * change) * change);

        }

        return sum;

    }


}
