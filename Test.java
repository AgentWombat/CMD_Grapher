import commandpromptgrapher.CmdGraph;

/**
	Tests the CmdGraph Class by graphing a sin curve from -pi to +pi.
*/
public class Test {

    public static void main(String[] args) {
        System.out.println();
        CmdGraph.graph(x -> Math.sin(2*x), -Math.PI, Math.PI, 150,30);
  

    }

    

}