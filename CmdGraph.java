package commandpromptgrapher;

public class CmdGraph {



    /** Graphs a provided function to the command prompt over a specified domain. It automatically determines the proper range scalling.
    *@param f The function to be graphed. Pass in a lambda express of the form "x -> f(x)" where f is defined over specified domain.
    *@param minX The start of the domain (inclusive).
    *@param maxX The end of the domain (exclusive).
    *@param xDim The number of character columns which will be in the graph.
    *@param yDim The number of character rows wwhich will be in the graph.
    */
    public static void graph(MathFunctions.mathFunction f, double minX, double maxX, int xDim, int yDim) {

        final int X_DIM = xDim;
        final int Y_DIM = yDim;
        final char CURVE_CHARACTER = '~';
        final char BLANK_CHARACTER = ' ';

        boolean[][] graphArray = getArray(f, minX, maxX, X_DIM, Y_DIM);

        //Drawing graph
        for (int i = 0; i < graphArray.length; i++) {


            for (int j = 0; j < graphArray[i].length; j++) {

                if (graphArray[i][j])
                    System.out.print(CURVE_CHARACTER);
                else
                    System.out.print(BLANK_CHARACTER);

            }

            System.out.println();

        }

    }
    /**Maps outputs of f over a selected domain to items in a list List is arranged like so:
     * {
     *     {f,f,f,...f},y = Y_DIM
     *     {f,f,f,...f}.
     *     {f,f,f,...f} y = 0
     * }   x=0       x=X_DIM
     *
     * false = no point; true = point.
     * */
    private static boolean[][] getArray(MathFunctions.mathFunction f, double minX, double maxX, int X_DIM, int Y_DIM) {

        final double CHANGE_X = maxX - minX;
        final int SAMPLE_POINTS = X_DIM;

        boolean[][] graphArray = new boolean[Y_DIM][X_DIM];


        //Determine the minimum and maximum of the function over its domain so the graph will have proper scalling.
        double fMax = f.evaluate(maxX);
        double fMin = f.evaluate(maxX);

        for (int i = 0; i < SAMPLE_POINTS; i++) {

            fMax = f.evaluate(minX + i * CHANGE_X/SAMPLE_POINTS) > fMax ? f.evaluate(minX + i * CHANGE_X/SAMPLE_POINTS) : fMax;

            fMin = f.evaluate(minX + i * CHANGE_X/SAMPLE_POINTS) < fMin ? f.evaluate(minX + i * CHANGE_X/SAMPLE_POINTS) : fMin;

        }

        //seting elements inside graphArray to true which will represent a spot on the graph.
        // 'map' is used to map values coming out of the function to valid array indices.
        for (int i = 0; i < X_DIM; i++) {

            graphArray[Y_DIM -1 - map(f.evaluate(minX + i * CHANGE_X / X_DIM), fMin, fMax, 0, Y_DIM-1)][i] = true;


        }

        return graphArray;


    }
    /**Maps value in range [lowInit, highInit] to [lowTo, highTo]*/
    private static int map(double val, double lowInit, double highInit, int lowTo, int highTo) {
        return (int) ((val - lowInit) * (highTo -lowTo) / (highInit - lowInit) + lowTo);
    }


}